package com.viaphone.plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viaphone.plugin.model.*;
import com.viaphone.plugin.utils.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.viaphone.plugin.HttpClient.getRequestJson;
import static com.viaphone.plugin.HttpClient.postRequest;


public class ViaphoneApi {

    //    public static String HOST = "http://default-environment-xt3p4dpnej.elasticbeanstalk.com";
    public static String HOST = "http://ikashkynbek.com";
    public static final String ACCESS_TOKEN = HOST + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
    public static final String ACCESS_TOKEN_CLIENT = ACCESS_TOKEN + "&username=%s&password=%s";
    public static final String API_ROOT = HOST + "/api/merchant";
    public static final String CREATE_CUSTOMER = API_ROOT + "/create-customer";
    public static final String CREATE_PURCHASE = API_ROOT + "/create-purchase-token";
    public static final String LOOKUP_PURCHASE = API_ROOT + "/lookup-purchase";
    public static final String PURCHASE_STATUS = API_ROOT + "/purchase-status";
    public static final String AUTH_PAYMENT = HOST + "/api/customer/authorize-payment";

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private Gson gson;
    private ResultListener resultListener;
    private boolean shutdownThread = false;

    public ViaphoneApi(String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        new ViaphoneApi(HOST, clientId, clientSecret, resultListener);
    }

    public ViaphoneApi(String host, String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        HOST = host;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.resultListener = resultListener;
        gson = new GsonBuilder().create();
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public CreateResp createPurchase(List<Product> items) {
        long ref = Utils.nextRef();
        double amount = 0;
        for (Product item : items) {
            amount += item.getPrice() * item.getQty();
        }
        CreateResp resp = (CreateResp) sendRequest(CREATE_PURCHASE, new CreateReq(ref, amount, items));
        if (resp != null && resultListener != null) {
            executeTask(resp.getPurchaseId());
        }
        return resp;
    }

    public PurchaseStatusResp getPurchaseStatus(long purchaseId) {
        return (PurchaseStatusResp) sendRequest(PURCHASE_STATUS, new PurchaseStatusReq(Utils.nextRef(), purchaseId));
    }

    public LookupResp lookupPurchase(long purchaseId) {
        return (LookupResp) sendRequest(LOOKUP_PURCHASE, new LookupReq(Utils.nextRef(), purchaseId));
    }

    public String createCustomer(CustomerReq customerReq) {
        customerReq.setPassword("123");
        return (String) sendRequest(CREATE_CUSTOMER, customerReq);
    }

    public AuthPurchaseResp authPayment(String phone, String code) {
        OauthToken token = getClientAccessToken(phone, "123");
        if (token != null) {
            return (AuthPurchaseResp) sendRequest(AUTH_PAYMENT, token.getAccess_token(), new AuthPurchaseReq(Utils.nextRef(), code));
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
        String url = String.format(ACCESS_TOKEN, clientId, clientSecret);
        return gson.fromJson(getRequestJson(url), OauthToken.class);
    }

    private OauthToken getClientAccessToken(String username, String password) {
        String url = String.format(ACCESS_TOKEN_CLIENT, "mobileapp", "secret", username, password);
        return gson.fromJson(getRequestJson(url), OauthToken.class);
    }

    public void executeTask(Long purchaseId) {
        Runnable runnable = () -> {
            try {
                boolean lookuped = false;
                while (!shutdownThread) {
                    System.out.println("getPurchaseStatus: " + purchaseId);
                    PurchaseStatusResp resp = getPurchaseStatus(purchaseId);
                    if (resp != null) {
                        if (resp.getStatus().equals(PurchaseStatusResp.Status.OK)) {
                            PurchaseStatus status = resp.getPurchaseStatus();
                            if (status == PurchaseStatus.AUTHORIZED && !lookuped) {
                                LookupResp lookupResp = lookupPurchase(purchaseId);
                                resultListener.onAuthorized(lookupResp.getDiscountPrice());
                                lookuped = true;
                            } else if (status == PurchaseStatus.FUNDED
                                    || status == PurchaseStatus.INTRANSIT) {
                                resultListener.onConfirmed(status);
                                shutdownThread = true;
                            } else if (status == PurchaseStatus.REFUNDED
                                    || status == PurchaseStatus.PARTIALLY_REFUNDED
                                    || status == PurchaseStatus.NOT_ENOUGH_FUNDS
                                    || status == PurchaseStatus.CANCELED) {
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
