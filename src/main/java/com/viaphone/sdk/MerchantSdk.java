package com.viaphone.sdk;

import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.enums.CampaignStatus;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.merchant.CreateReq;

import java.util.List;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;
import static com.viaphone.sdk.utils.gson.GsonHelper.fromJson;


public class MerchantSdk {

    private final String accessToken;
    private final String createPurchase;
    private final String lookupPurchase;
    private final String savePurchases;
    private final String offers;
    private final String purchaseComments;

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private ResultListener resultListener;
//    private ChirpApi chirpApi;
    private boolean shutdownThread = false;

    public MerchantSdk(String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        this(DEFAULT_HOST, clientId, clientSecret, resultListener);
    }

    public MerchantSdk(String host, String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        String apiRoot = host + "/api/merchant";

        this.accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
        this.createPurchase = apiRoot + "/create-purchase";
        this.lookupPurchase = apiRoot + "/lookup-purchase";
        this.savePurchases = apiRoot + "/save-purchases";
        this.offers = apiRoot + "/get-offers";
        this.purchaseComments = apiRoot + "/purchase-comments";

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.resultListener = resultListener;
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public Response createPurchase(List<ProductItem> items, Long branchId, ConfirmType confirmType) throws Exception {
        return sendPostRequest(createPurchase, new CreateReq(items, branchId, confirmType));
    }

    public Response savePurchases(List<CreateReq> purchases) throws Exception {
        return sendPostRequest(savePurchases, purchases);
    }

    public Response lookupPurchase(long purchaseId) throws Exception {
        String url = lookupPurchase + "?id=" + purchaseId;
        return sendGetRequest(url);
    }

    public Response offers(CampaignStatus status) throws Exception {
        String url = offers + "?status=" + status.name();
        return sendGetRequest(url);
    }

    public Response purchaseComments() throws Exception {
        return sendGetRequest(purchaseComments);
    }

    private Response sendGetRequest(String url) throws Exception {
        Object result = HttpClient.getRequest(url, token.getAccess_token());
        if (result instanceof OauthToken) {
            token = getAccessToken();
            result = HttpClient.getRequest(url, token.getAccess_token());
        }
        return (Response) result;
    }

    private Response sendPostRequest(String url, Object obj) throws Exception {
        Object result = HttpClient.postRequest(url, token.getAccess_token(), obj);
        if (result instanceof OauthToken) {
            token = getAccessToken();
            result = HttpClient.postRequest(url, token.getAccess_token(), obj);
        }
        return (Response) result;
    }

    private OauthToken getAccessToken() throws Exception {
        String url = String.format(accessToken, clientId, clientSecret);
        return (OauthToken) fromJson(getRequestJson(url), OauthToken.class);
    }

    /*private void executeTask(Long purchaseId) {
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
                                resultListener.onAuthorized(lookupResp.getPurchase());
                                lookuped = true;
                            } else if (sta  tus == PurchaseStatus.COMPLETED) {
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
    }*/
}
