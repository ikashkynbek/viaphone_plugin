package com.viaphone.sdk;

import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.enums.CampaignStatus;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.merchant.CreateReq;

import java.util.List;

import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;


public class MerchantSdk {

    private final String createPurchase;
    private final String lookupPurchase;
    private final String savePurchases;
    private final String offers;
    private final String purchaseComments;
    private final HttpClient httpClient;

//    private ResultListener resultListener;
//    private ChirpApi chirpApi;
//    private boolean shutdownThread = false;

    public MerchantSdk(String clientId, String clientSecret) throws Exception {
        this(DEFAULT_HOST, clientId, clientSecret, false, null, null);
    }

    public MerchantSdk(String clientId, String clientSecret, String proxyHost, int proxyPort) throws Exception {
        this(DEFAULT_HOST, clientId, clientSecret, true, proxyHost, proxyPort);
    }

    public MerchantSdk(String host, String clientId, String clientSecret) throws Exception {
        this(host, clientId, clientSecret, false, null, null);
    }

    public MerchantSdk(String host, String clientId, String clientSecret, String proxyHost, int proxyPort) throws Exception {
        this(host, clientId, clientSecret, true, proxyHost, proxyPort);
    }

    private MerchantSdk(String host, String clientId, String clientSecret, boolean hasProxy, String proxyHost, Integer proxyPort) throws Exception {
        String apiRoot = host + "/api/merchant";
        String accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
        this.createPurchase = apiRoot + "/create-purchase";
        this.lookupPurchase = apiRoot + "/lookup-purchase";
        this.savePurchases = apiRoot + "/save-purchases";
        this.offers = apiRoot + "/get-offers";
        this.purchaseComments = apiRoot + "/purchase-comments";

        String tokenUrl = String.format(accessToken, clientId, clientSecret);
        httpClient = new HttpClient(tokenUrl, hasProxy, proxyHost, proxyPort);
    }

    public Response createPurchase(List<ProductItem> items, Long branchId, ConfirmType confirmType) throws Exception {
        return httpClient.sendPostRequest(createPurchase, new CreateReq(items, branchId, confirmType));
    }

    public Response savePurchases(List<CreateReq> purchases) throws Exception {
        return httpClient.sendPostRequest(savePurchases, purchases);
    }

    public Response lookupPurchase(long purchaseId) throws Exception {
        String url = lookupPurchase + "?id=" + purchaseId;
        return httpClient.sendGetRequest(url);
    }

    public Response offers(CampaignStatus status) throws Exception {
        String url = offers + "?status=" + status.name();
        return httpClient.sendGetRequest(url);
    }

    public Response purchaseComments() throws Exception {
        return httpClient.sendGetRequest(purchaseComments);
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
