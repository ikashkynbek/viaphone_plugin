package com.viaphone.sdk;

import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.merchant.*;
import com.viaphone.sdk.utils.ChirpApi;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;
import static com.viaphone.sdk.utils.Utils.fromJson;


public class MerchantSdk {

    private final String accessToken;
    private final String createPurchase;
    private final String lookupPurchase;
    private final String purchaseStatus;

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
        double amount = 0;
        for (ProductItem item : items) {
            amount += item.getPrice() * item.getQty();
        }
        return (CreateResp) sendRequest(createPurchase, new CreateReq(amount, items, confirmType));
    }

    @Deprecated
    public CreateResp createPurchase(List<ProductItem> items, Date date) throws IOException {
        double amount = 0;
        for (ProductItem item : items) {
            amount += item.getPrice() * item.getQty();
        }
        CreateReq req = new CreateReq(amount, items, ConfirmType.TOKEN);
        req.setCreateDate(date);
        return (CreateResp) sendRequest(createPurchase, req);
    }

    public PurchaseStatusResp getPurchaseStatus(long purchaseId) throws IOException {
        return (PurchaseStatusResp) sendRequest(purchaseStatus, new PurchaseStatusReq(purchaseId));
    }

    public LookupResp lookupPurchase(long purchaseId) throws IOException {
        return (LookupResp) sendRequest(lookupPurchase, new LookupReq(purchaseId));
    }

    private Object sendRequest(String url, Object obj) throws IOException {
        return HttpClient.sendRequest(url, token.getAccess_token(), obj);
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
