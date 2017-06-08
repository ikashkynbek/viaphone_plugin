package com.viaphone.sdk;


import com.viaphone.sdk.model.CustomerInfo;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.customer.*;

import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;

public class CustomerSdk {

    private final String URL_SEND_INFO;
    private final String URL_CUST_INFO;
    private final String URL_GET_OFFERS;
    private final String URL_GET_BRANCHES;
    private final String URL_APP_TOKEN;
    private final String URL_HISTORY;
    private final String URL_AUTH_PURCHASE;
    private final String URL_CONFIRM_PURCHASE;
    private final String URL_SET_FAVORITE;
    private final String URL_GET_PROMO;
    private final String URL_SET_PROMO;

    private final HttpClient httpClient;

    public CustomerSdk(String username, String password) throws Exception {
        this(DEFAULT_HOST, username, password, false, null, null);
    }

    public CustomerSdk(String host, String username, String password) throws Exception {
        this(host, username, password, false, null, null);
    }

    public CustomerSdk(String username, String password, String proxyHost, int proxyPort) throws Exception {
        this(DEFAULT_HOST, username, password, true, proxyHost, proxyPort);
    }

    public CustomerSdk(String host, String username, String password, String proxyHost, int proxyPort) throws Exception {
        this(host, username, password, true, proxyHost, proxyPort);
    }

    private CustomerSdk(String host, String username, String password, boolean hasProxy, String proxyHost, Integer proxyPort) throws Exception {
        String apiRoot = host + "/api/customer/";
        String accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s&username=%s&password=%s";
        this.URL_SEND_INFO = apiRoot + "create-info";
        this.URL_CUST_INFO = apiRoot + "info";
        this.URL_GET_OFFERS = apiRoot + "get-offers";
        this.URL_GET_BRANCHES = apiRoot + "get-branches";
        this.URL_APP_TOKEN = apiRoot + "google-token";
        this.URL_HISTORY = apiRoot + "history";
        this.URL_AUTH_PURCHASE = apiRoot + "authorize-purchase";
        this.URL_CONFIRM_PURCHASE = apiRoot + "confirm-purchase";
        this.URL_SET_FAVORITE = apiRoot + "set-favorite";
        this.URL_GET_PROMO = apiRoot + "get-promo";
        this.URL_SET_PROMO = apiRoot + "set-promo";

        String tokenUrl = String.format(accessToken, "mobileapp", "secret", username, password);
        httpClient = new HttpClient(tokenUrl, hasProxy, proxyHost, proxyPort);
    }

    public Response sendAppToken(boolean isEnabled, String token) throws Exception {
        return httpClient.sendPostRequest(URL_APP_TOKEN, new AppTokenReq(isEnabled, token));
    }

    public Response authorizePurchase(String code) throws Exception {
        return httpClient.sendPostRequest(URL_AUTH_PURCHASE, new PurchaseAuthReq(code));
    }

    public Response confirmPurchase(Long purchaseId) throws Exception {
        return httpClient.sendPostRequest(URL_CONFIRM_PURCHASE, new ConfirmPurchaseReq(purchaseId));
    }

    public Response setFavoriteCampaign(Long campaign, boolean isFavorite) throws Exception {
        return httpClient.sendPostRequest(URL_SET_FAVORITE, new SetFavoriteReq(campaign, isFavorite));
    }

    public Response getBranches() throws Exception {
        return httpClient.sendGetRequest(URL_GET_BRANCHES);
    }

    public Response getMyStats() throws Exception {
        return httpClient.sendGetRequest(URL_CUST_INFO);
    }

    public Response getOffers() throws Exception {
        return httpClient.sendGetRequest(URL_GET_OFFERS);
    }

    public Response getHistory() throws Exception {
        return httpClient.sendGetRequest(URL_HISTORY);
    }

    public void createInfo(CustomerInfo info) throws Exception {
        httpClient.sendPostRequest(URL_SEND_INFO, info);
    }

    public Response getPromoCode() throws Exception {
        return httpClient.sendGetRequest(URL_GET_PROMO);
    }

    public Response setPromoCode(String code) throws Exception {
        return httpClient.sendPostRequest(URL_SET_PROMO, new PromoCodeReq(code));
    }
}
