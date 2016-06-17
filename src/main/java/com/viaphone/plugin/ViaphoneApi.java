package com.viaphone.plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viaphone.plugin.model.*;
import com.viaphone.plugin.utils.ChirpApi;
import com.viaphone.plugin.utils.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.viaphone.plugin.HttpClient.getRequestJson;
import static com.viaphone.plugin.HttpClient.postRequest;


public class ViaphoneApi {

    private static String DEFAULT_HOST = "http://viaphoneplatform-dev.us-west-2.elasticbeanstalk.com";
    private final String accessToken;
    private final String accessTokenClient;
    private final String createPurchase;
    private final String lookupPurchase;
    private final String purchaseStatus;
    private  final String authPayment;

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private Gson gson;
    private ResultListener resultListener;
    private ChirpApi chirpApi;
    private boolean shutdownThread = false;

    public ViaphoneApi(String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        this(DEFAULT_HOST, clientId, clientSecret, resultListener);
    }

    public ViaphoneApi(String host, String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        String apiRoot = host + "/api/merchant";

        this.accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
        this.accessTokenClient = accessToken + "&username=%s&password=%s";
        this.createPurchase = apiRoot + "/create-purchase";
        this.lookupPurchase = apiRoot + "/lookup-purchase";
        this.purchaseStatus = apiRoot + "/purchase-status";
        this.authPayment = host + "/api/customer/authorize-purchase";

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.resultListener = resultListener;
        gson = new GsonBuilder().create();
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public void playChirp(String token) throws Exception {
        chirpApi = new ChirpApi();
        chirpApi.start(token);
    }

    public void stopChirp() {
        chirpApi.stopSound();
    }

    public CreateResp createPurchase(List<Product> items) {
        long ref = Utils.nextRef();
        double amount = 0;
        for (Product item : items) {
            amount += item.getPrice() * item.getQty();
        }
        CreateResp resp = (CreateResp) sendRequest(createPurchase, new CreateReq(ref, amount, items));
        if (resp != null && resultListener != null) {
            executeTask(resp.getPurchaseId());
        }
        return resp;
    }

    private PurchaseStatusResp getPurchaseStatus(long purchaseId) {
        return (PurchaseStatusResp) sendRequest(purchaseStatus, new PurchaseStatusReq(Utils.nextRef(), purchaseId));
    }

    private LookupResp lookupPurchase(long purchaseId) {
        return (LookupResp) sendRequest(lookupPurchase, new LookupReq(Utils.nextRef(), purchaseId));
    }

    //todo remove on production
    public AuthPurchaseResp authPurchase(String phone, String code) {
        OauthToken token = getClientAccessToken(phone, "123");
        if (token != null) {
            return (AuthPurchaseResp) sendRequest(authPayment, token.getAccess_token(), new AuthPurchaseReq(Utils.nextRef(), code));
        }
        return null;
    }

    private Object sendRequest(String url, Object obj) {
        return sendRequest(url, token.getAccess_token(), obj);
    }

    private Object sendRequest(String url, String token, Object obj) {
        String result = postRequest(url, token, gson.toJson(obj));
        try {
            if (obj instanceof CreateReq) {
                return gson.fromJson(result, CreateResp.class);
            } else if (obj instanceof PurchaseStatusReq) {
                return gson.fromJson(result, PurchaseStatusResp.class);
            } else if (obj instanceof LookupReq) {
                return gson.fromJson(result, LookupResp.class);
            } else if (obj instanceof AuthPurchaseReq) {
                return gson.fromJson(result, AuthPurchaseResp.class);
            } else {
                return result;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private OauthToken getAccessToken() {
        String url = String.format(accessToken, clientId, clientSecret);
        return gson.fromJson(getRequestJson(url), OauthToken.class);
    }

    private OauthToken getClientAccessToken(String username, String password) {
        String url = String.format(accessTokenClient, "mobileapp", "secret", username, password);
        return gson.fromJson(getRequestJson(url), OauthToken.class);
    }

    private void executeTask(Long purchaseId) {
        Runnable runnable = () -> {
            try {
                boolean lookuped = false;
                while (!shutdownThread) {
                    PurchaseStatusResp resp = getPurchaseStatus(purchaseId);
                    if (resp != null) {
                        if (resp.getStatus().equals(PurchaseStatusResp.Status.OK)) {
                            PurchaseStatus status = resp.getPurchaseStatus();
                            if (status == PurchaseStatus.AUTHORIZED && !lookuped) {
                                stopChirp();
                                LookupResp lookupResp = lookupPurchase(purchaseId);
                                resultListener.onAuthorized(lookupResp.getDiscountPrice());
                                lookuped = true;
                            } else if (status == PurchaseStatus.COMPLETED) {
                                resultListener.onConfirmed(status);
                                shutdownThread = true;
                            } else if (status == PurchaseStatus.CANCELED) {
                                resultListener.onCancel(status);
                                shutdownThread = true;
                            }
                        } else {
                            resultListener.onError(resp.getStatus());
                            shutdownThread = true;
                        }
                    } else {
                        resultListener.onError(null);
                        shutdownThread = true;
                    }
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void stopExecutor() {
        System.out.println("Stop executor");
        shutdownThread = true;
    }
}
