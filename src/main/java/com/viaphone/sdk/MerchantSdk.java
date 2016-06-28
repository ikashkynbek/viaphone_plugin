package com.viaphone.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.PurchaseStatus;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.customer.PurchaseAuthReq;
import com.viaphone.sdk.model.customer.PurchaseAuthResp;
import com.viaphone.sdk.model.merchant.*;
import com.viaphone.sdk.utils.ChirpApi;
import com.viaphone.sdk.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.HttpClient.postRequest;


public class MerchantSdk {

    private static String DEFAULT_HOST = "http://viaphoneplatform-dev.us-west-2.elasticbeanstalk.com";
    private final String accessToken;
    private final String accessTokenClient;
    private final String createPurchase;
    private final String lookupPurchase;
    private final String purchaseStatus;
    private final String authPayment;

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private Gson gson;
    private ResultListener resultListener;
    private ChirpApi chirpApi;
    private boolean shutdownThread = false;
    public static final String SIMPLE_DT_FOMRATE = "yyyy-MM-dd hh:mm:ss";

    public MerchantSdk(String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        this(DEFAULT_HOST, clientId, clientSecret, resultListener);
    }

    public MerchantSdk(String host, String clientId, String clientSecret, ResultListener resultListener) throws Exception {
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
        gson = new GsonBuilder().setDateFormat(SIMPLE_DT_FOMRATE).create();
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public void playChirp(String token) throws Exception {
        chirpApi = new ChirpApi();
        chirpApi.start(token);
    }

    private void stopChirp() {
        chirpApi.stopSound();
    }

    public CreateResp createPurchase(List<ProductItem> items) throws IOException {
        long ref = Utils.nextRef();
        double amount = 0;
        for (ProductItem item : items) {
            amount += item.getPrice() * item.getQty();
        }
        CreateResp resp = (CreateResp) sendRequest(createPurchase, new CreateReq(amount, items));
        if (resp != null && resultListener != null) {
            executeTask(resp.getPurchaseId());
        }
        return resp;
    }

    public PurchaseStatusResp getPurchaseStatus(long purchaseId) throws IOException {
        return (PurchaseStatusResp) sendRequest(purchaseStatus, new PurchaseStatusReq(purchaseId));
    }

    public LookupResp lookupPurchase(long purchaseId) throws IOException {
        return (LookupResp) sendRequest(lookupPurchase, new LookupReq(purchaseId));
    }

/*    //todo remove on production
    public PurchaseAuthResp authPurchase(String phone, String code) throws IOException {
        OauthToken token = getClientAccessToken(phone, "123");
        if (token != null) {
            return (PurchaseAuthResp) sendRequest(authPayment, token.getAccess_token(), new PurchaseAuthReq(code));
        }
        return null;
    }*/

    private Object sendRequest(String url, Object obj) throws IOException {
        return sendRequest(url, token.getAccess_token(), obj);
    }

    private Object sendRequest(String url, String token, Object obj) throws IOException {
        String result = postRequest(url, token, gson.toJson(obj));
        if (obj instanceof CreateReq) {
            return gson.fromJson(result, CreateResp.class);
        } else if (obj instanceof PurchaseStatusReq) {
            return gson.fromJson(result, PurchaseStatusResp.class);
        } else if (obj instanceof LookupReq) {
            return gson.fromJson(result, LookupResp.class);
        } else if (obj instanceof PurchaseAuthReq) {
            return gson.fromJson(result, PurchaseAuthResp.class);
        } else {
            return result;
        }
    }

    private OauthToken getAccessToken() throws IOException {
        String url = String.format(accessToken, clientId, clientSecret);
        return gson.fromJson(getRequestJson(url), OauthToken.class);
    }

    private OauthToken getClientAccessToken(String username, String password) throws IOException {
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
                        if (resp.getStatus() == Response.Status.OK) {
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
            } catch (Exception e) {
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
