package com.viaphone.sdk;


import com.viaphone.sdk.model.OauthToken;
import com.viaphone.sdk.model.Request;
import com.viaphone.sdk.model.customer.*;

import java.io.IOException;

import static com.viaphone.sdk.HttpClient.getRequestJson;
import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;
import static com.viaphone.sdk.utils.Utils.fromJson;

public class CustomerSdk {

    //    private static final String URL_APP_TOKEN = HOST + URL_CUST + "google-token";
//    private static final String URL_CUST_INF = HOST + URL_CUST + "info";
//    private static final String URL_ACC_INF = HOST + URL_CUST + "account-info";
//    private static final String URL_PURCHASES = HOST + URL_CUST + "purchases";
//    private static final String URL_GET_BRANCHES = HOST + URL_CUST + "get-stores";
//    private static final String URL_GET_OFFERS = HOST + URL_CUST + "get-offers";
    private final String URL_SIGN_UP;
    private final String URL_SEND_INFO;
    private final String URL_AUTH_PURCHASE;
    private final String URL_CONFIRM_PURCHASE;


    private final String accessToken;
    private OauthToken token;

    public CustomerSdk(String username, String password) throws Exception {
        this(DEFAULT_HOST, username, password);
    }

    public CustomerSdk(String host, String username, String password) throws Exception {
        String apiRoot = host + "/api/customer/";
        this.accessToken = host + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s&username=%s&password=%s";
        this.URL_SIGN_UP = host + "/customer/create";
        this.URL_SEND_INFO = apiRoot + "create-info";
        this.URL_AUTH_PURCHASE = apiRoot + "authorize-purchase";
        this.URL_CONFIRM_PURCHASE = apiRoot + "confirm-purchase";

        token = getAccessToken(username, password);
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public void sendAppToken(String token) {
    }

    public void getNearestBranches(double longitude, double attitude) {
    }

    public PurchaseAuthResp authorizePurchase(String code) throws IOException {
        return (PurchaseAuthResp) sendRequest(URL_AUTH_PURCHASE, new PurchaseAuthReq(code));
    }


    public ConfirmPurchaseResp confirmPurchase(Long purchaseId) throws IOException {
        return (ConfirmPurchaseResp) sendRequest(URL_CONFIRM_PURCHASE, new ConfirmPurchaseReq(purchaseId));
    }

    public void getMyStats() {

    }

    public void getOffers() {

    }

    public void getPurchases() {

    }

    public Long signUp(String phone, String pass, String nick) throws IOException {
        Request signupReq = new SignupReq(phone, pass, nick);
        SignupResp resp = (SignupResp) HttpClient.sendRequest(URL_SIGN_UP, null, signupReq);
        return resp.getCustomerId();
    }

    public Long createInfo(CreateInfoReq req) throws IOException {
        CreateInfoResp resp = (CreateInfoResp) sendRequest(URL_SEND_INFO, req);
        return resp.getInfoId();
    }

    private Object sendRequest(String url, Object obj) throws IOException {
        return HttpClient.sendRequest(url, token.getAccess_token(), obj);
    }

    private OauthToken getAccessToken(String username, String password) throws IOException {
        String url = String.format(accessToken, "mobileapp", "secret", username, password);
        return (OauthToken) fromJson(getRequestJson(url), OauthToken.class);
    }
}
