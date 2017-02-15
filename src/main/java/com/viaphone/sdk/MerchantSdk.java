package com.viaphone.sdk;

import com.viaphone.sdk.model.*;
import com.viaphone.sdk.model.enums.CampaignStatus;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.enums.MessageType;
import com.viaphone.sdk.model.merchant.CreateReq;
import com.viaphone.sdk.model.merchant.CreateResp;

import java.util.List;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.model.enums.MessageType.*;
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

    public CreateResp createPurchase(List<ProductItem> items, Long branchId, ConfirmType confirmType) throws Exception {
        return (CreateResp) sendPostRequest(createPurchase, new CreateReq(items, branchId, confirmType), CREATE_PURCHASE);
    }

    public void savePurchases(List<CreateReq> purchases) throws Exception {
        sendPostRequest(savePurchases, purchases, SAVE_PURCHASES);
    }

    public CustomerPurchase lookupPurchase(long purchaseId) throws Exception {
        String url = lookupPurchase + "?id=" + purchaseId;
        return (CustomerPurchase) sendGetRequest(url);
    }

    public List<Offer> offers(CampaignStatus status) throws Exception {
        String url = offers + "?status=" + status.name();
        return (List<Offer>) sendGetRequest(url);
    }

    public List<String> purchaseComments() throws Exception {
        return (List<String>) sendGetRequest(purchaseComments);
    }

    private Object sendGetRequest(String url) throws Exception {
        Object result = HttpClient.getRequest(url, token.getAccess_token());
        if (result instanceof OauthToken) {
            token = getAccessToken();
            result = HttpClient.getRequest(url, token.getAccess_token());
        }
        return result;
    }

    private Object sendPostRequest(String url, Object obj, MessageType type) throws Exception {
        Object result = HttpClient.postRequest(url, token.getAccess_token(), obj);
        if (result instanceof OauthToken) {
            token = getAccessToken();
            result = HttpClient.postRequest(url, token.getAccess_token(), obj);
        }
        return result;
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
