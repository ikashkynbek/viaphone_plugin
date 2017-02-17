package com.viaphone.sdk;


import com.viaphone.sdk.model.CustomerInfo;
import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.customer.AppTokenReq;
import com.viaphone.sdk.model.customer.ConfirmPurchaseReq;
import com.viaphone.sdk.model.customer.PurchaseAuthReq;
import com.viaphone.sdk.model.customer.SetFavoriteReq;
import com.viaphone.sdk.model.enums.PurchaseStatus;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;
import static com.viaphone.sdk.utils.gson.GsonHelper.fromJson;

public class CustomerSdk {

    private final String URL_SEND_INFO;
    private final String URL_CUST_INFO;
    private final String URL_GET_OFFERS;
    private final String URL_GET_BRANCHES;
    private final String URL_APP_TOKEN;
    private final String URL_PURCHASES;
    private final String URL_AUTH_PURCHASE;
    private final String URL_CONFIRM_PURCHASE;
    private final String URL_SET_FAVORITE;

    private final String accessToken, username, password;
    private OauthToken token;

    public CustomerSdk(String username, String password) throws Exception {
        this(DEFAULT_HOST, username, password);
    }

    public CustomerSdk(String host, String username, String password) throws Exception {
        String apiRoot = host + "/api/customer/";
        this.accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s&username=%s&password=%s";
        this.URL_SEND_INFO = apiRoot + "create-info";
        this.URL_CUST_INFO = apiRoot + "info";
        this.URL_GET_OFFERS = apiRoot + "get-offers";
        this.URL_GET_BRANCHES = apiRoot + "get-branches";
        this.URL_APP_TOKEN = apiRoot + "google-token";
        this.URL_PURCHASES = apiRoot + "purchases";
        this.URL_AUTH_PURCHASE = apiRoot + "authorize-purchase";
        this.URL_CONFIRM_PURCHASE = apiRoot + "confirm-purchase";
        this.URL_SET_FAVORITE = apiRoot + "set-favorite";

        this.username = username;
        this.password = password;
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public Response sendAppToken(String token) throws Exception {
        return sendPostRequest(URL_APP_TOKEN, new AppTokenReq(token));
    }

    public Response authorizePurchase(String code) throws Exception {
        return sendPostRequest(URL_AUTH_PURCHASE, new PurchaseAuthReq(code));
    }

    public Response confirmPurchase(Long purchaseId) throws Exception {
        return sendPostRequest(URL_CONFIRM_PURCHASE, new ConfirmPurchaseReq(purchaseId));
    }

    public Response setFavoriteCampaign(Long campaign, boolean isFavorite) throws Exception {
        return sendPostRequest(URL_SET_FAVORITE, new SetFavoriteReq(campaign, isFavorite));
    }

    public Response getBranches() throws Exception {
        return sendGetRequest(URL_GET_BRANCHES);
    }

    public Response getMyStats() throws Exception {
        return sendGetRequest(URL_CUST_INFO);
    }

    public Response getOffers() throws Exception {
        return sendGetRequest(URL_GET_OFFERS);
    }

    public Response getPurchases(PurchaseStatus status) throws Exception {
        String url = URL_PURCHASES + "?status=" + status.name();
        return sendGetRequest(url);
    }

    public void createInfo(CustomerInfo info) throws Exception {
        sendPostRequest(URL_SEND_INFO, info);
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
        String url = String.format(accessToken, "mobileapp", "secret", username, password);
        return (OauthToken) fromJson(getRequestJson(url), OauthToken.class);
    }
}
