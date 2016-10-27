package com.viaphone.sdk;

import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.customer.OffersReq;
import com.viaphone.sdk.model.customer.OffersResp;
import com.viaphone.sdk.model.enums.CampaignStatus;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.merchant.*;
import com.viaphone.sdk.utils.ChirpApi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;
import static com.viaphone.sdk.utils.gson.GsonHelper.fromJson;


public class MerchantSdk {

    private final String accessToken;
    private final String createPurchase;
    private final String lookupPurchase;
    private final String purchaseStatus;
    private final String savePurchases;
    private final String offers;

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private ResultListener resultListener;
    private ChirpApi chirpApi;
    private boolean shutdownThread = false;

    public MerchantSdk(String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        this(DEFAULT_HOST, clientId, clientSecret, resultListener);
    }

    public MerchantSdk(String host, String clientId, String clientSecret, ResultListener resultListener) throws Exception {
        String apiRoot = host + "/api/merchant";

        this.accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
        this.createPurchase = apiRoot + "/create-purchase";
        this.lookupPurchase = apiRoot + "/lookup-purchase";
        this.purchaseStatus = apiRoot + "/purchase-status";
        this.savePurchases = apiRoot + "/save-purchases";
        this.offers = apiRoot + "/get-offers";

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.resultListener = resultListener;
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

    public CreateResp createPurchase(List<ProductItem> items, ConfirmType confirmType) throws IOException {
        return (CreateResp) sendRequest(createPurchase, new CreateReq(items, confirmType));
    }

    public SavePurchasesResp savePurchases(List<CreateReq> purchases) throws IOException {
        return (SavePurchasesResp) sendRequest(savePurchases, new SavePurchasesReq(purchases));
    }

    public PurchaseStatusResp getPurchaseStatus(long purchaseId) throws IOException {
        return (PurchaseStatusResp) sendRequest(purchaseStatus, new PurchaseStatusReq(purchaseId));
    }

    public LookupResp lookupPurchase(long purchaseId) throws IOException {
        return (LookupResp) sendRequest(lookupPurchase, new LookupReq(purchaseId));
    }

    public OffersResp offers(CampaignStatus status) throws IOException {
        return (OffersResp) sendRequest(offers, new OffersReq(status));
    }

    private Object sendRequest(String url, Object obj) throws IOException {
        Object result = HttpClient.sendRequest(url, token.getAccess_token(), obj);
        if (result instanceof OauthToken) {
            token = getAccessToken();
            result = HttpClient.sendRequest(url, token.getAccess_token(), obj);
        }
        return result;
    }

    private OauthToken getAccessToken() throws IOException {
        String url = String.format(accessToken, clientId, clientSecret);
        return (OauthToken) fromJson(getRequestJson(url), OauthToken.class);
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
                                resultListener.onAuthorized(lookupResp.getPurchase());
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
