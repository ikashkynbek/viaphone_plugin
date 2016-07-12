package com.viaphone.sdk;


import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.customer.*;
import com.viaphone.sdk.model.enums.PurchaseStatus;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;
import static com.viaphone.sdk.utils.Utils.fromJson;

public class CustomerSdk {

    private final String URL_SEND_INFO;
    private final String URL_CUST_INFO;
    private final String URL_GET_OFFERS;
    private final String URL_GET_BRANCHES;
    private final String URL_APP_TOKEN;
    private final String URL_PURCHASES;
    private final String URL_AUTH_PURCHASE;
    private final String URL_CONFIRM_PURCHASE;

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

        this.username = username;
        this.password = password;
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public AppTokenResp sendAppToken(String token) throws Exception {
        return (AppTokenResp) sendRequest(URL_APP_TOKEN, new AppTokenReq(token));
    }

    public BranchResp getNearestBranches(double latitude, double longitude) throws Exception {
        return (BranchResp) sendRequest(URL_GET_BRANCHES, new BranchReq(latitude, longitude));
    }

    public PurchaseAuthResp authorizePurchase(String code) throws Exception {
        return (PurchaseAuthResp) sendRequest(URL_AUTH_PURCHASE, new PurchaseAuthReq(code));
    }

    public ConfirmPurchaseResp confirmPurchase(Long purchaseId) throws Exception {
        return (ConfirmPurchaseResp) sendRequest(URL_CONFIRM_PURCHASE, new ConfirmPurchaseReq(purchaseId));
    }

    public MyStatsResp getMyStats() throws Exception {
        return (MyStatsResp) sendRequest(URL_CUST_INFO, new MyStatsReq());
    }

    public OffersResp getOffers() throws Exception {
        return (OffersResp) sendRequest(URL_GET_OFFERS, new OffersReq());
    }

    public PurchasesResp getPurchases(PurchaseStatus status) throws Exception {
        return (PurchasesResp) sendRequest(URL_PURCHASES, new PurchasesReq(status));
    }

    public Long createInfo(CreateInfoReq req) throws Exception {
        CreateInfoResp resp = (CreateInfoResp) sendRequest(URL_SEND_INFO, req);
        return resp.getInfoId();
    }

    private Object sendRequest(String url, Object obj) throws Exception {
        Object result = HttpClient.sendRequest(url, token.getAccess_token(), obj);
        if (result instanceof OauthToken) {
            token = getAccessToken();
            result = HttpClient.sendRequest(url, token.getAccess_token(), obj);
        }
        return result;
    }

    private OauthToken getAccessToken() throws Exception {
        String url = String.format(accessToken, "mobileapp", "secret", username, password);
        return (OauthToken) fromJson(getRequestJson(url), OauthToken.class);
    }
}
