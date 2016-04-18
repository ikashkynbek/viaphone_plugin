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

    public static String HOST = "http://default-environment-xt3p4dpnej.elasticbeanstalk.com";
    //    public static String HOST = "http://ikashkynbek.com";
    public static String ACCESS_TOKEN = HOST + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
    public static String API_ROOT = HOST + "/api/merchant";
    public static final String CREATE_PURCHASE = API_ROOT + "/create-payment-token";
    public static final String LOOKUP_PURCHASE = API_ROOT + "/lookup-payment";
    public static final String PURCHASE_STATUS = API_ROOT + "/payment-status";

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private Gson gson;
    private ResultListener resultListener;

    public ViaphoneApi(String clientId, String clientSecret, ResultListener resultListener) throws Exception {
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
        if (resp != null) {
            executeTask(resp.getPaymentId());
        }
        return resp;
    }

    public PurchaseStatusResp getPurchaseStatus(long purchaseId) {
        return (PurchaseStatusResp) sendRequest(PURCHASE_STATUS, new PurchaseStatusReq(Utils.nextRef(), purchaseId));
    }

    public LookupResp lookupPurchase(long purchaseId) {
        return (LookupResp) sendRequest(LOOKUP_PURCHASE, new LookupReq(Utils.nextRef(), purchaseId));
    }

    private Object sendRequest(String url, Object obj) {
        String result = postRequest(url, token.getAccess_token(), gson.toJson(obj));
        try {
            if (obj instanceof CreateReq) {
                return gson.fromJson(result, CreateResp.class);
            } else if (obj instanceof PurchaseStatusReq) {
                return gson.fromJson(result, PurchaseStatusResp.class);
            } else if (obj instanceof LookupReq) {
                return gson.fromJson(result, LookupResp.class);
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

    public void executeTask(Long purchaseId) {
        Runnable runnable = () -> {
            try {
                PurchaseStatus status = PurchaseStatus.CREATED;
                while (status == PurchaseStatus.CREATED) {
                    PurchaseStatusResp resp = getPurchaseStatus(purchaseId);
                    if (resp != null) {
                        if (resp.getStatus().equals(PurchaseStatusResp.Status.OK)) {
                            status = resp.getPaymentStatus();
                            if (status == PurchaseStatus.AUTHORIZED) {
                                LookupResp lookupResp = lookupPurchase(purchaseId);
                                resultListener.onAuthorized(lookupResp.getDiscountPrice());
                            }
                        } else {
                            resultListener.onError(resp.getStatus());
                        }
                    }
                    TimeUnit.SECONDS.sleep(3);
                }

                while (status == PurchaseStatus.AUTHORIZED) {
                    PurchaseStatusResp resp = getPurchaseStatus(purchaseId);
                    if (resp != null) {
                        if (resp.getStatus().equals(PurchaseStatusResp.Status.OK)) {
                            status = resp.getPaymentStatus();
                            if (status == PurchaseStatus.FUNDED
                                    || status == PurchaseStatus.INTRANSIT) {
                                resultListener.onConfirmed(status);
                            } else if (status == PurchaseStatus.REFUNDED
                                    || status == PurchaseStatus.PARTIALLY_REFUNDED
                                    || status == PurchaseStatus.NOT_ENOUGH_FUNDS
                                    || status == PurchaseStatus.CANCELED) {
                                resultListener.onCancel(status);
                            }
                        } else {
                            resultListener.onError(resp.getStatus());
                        }
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
}
