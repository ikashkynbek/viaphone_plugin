package com.viaphone.sdk;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viaphone.sdk.model.Request;
import com.viaphone.sdk.model.customer.SignupReq;
import com.viaphone.sdk.model.customer.SignupResp;

import java.io.IOException;

public class CustomerSdk {

    //    private static final String HOST = "http://viaphoneplatform-dev.us-west-2.elasticbeanstalk.com";
    private static final String HOST = "http://192.168.10.67:8081";
    //    private static final String HOST= "http://192.168.10.69:8081";
    private static final String URL_CUST = "/api/customer/";
    private static final String URL_AUTH = HOST + "/" + "oauth/token";
    private static final String URL_APP_TOKEN = HOST + URL_CUST + "google-token";
    private static final String URL_CUST_INF = HOST + URL_CUST + "info";
    private static final String URL_ACC_INF = HOST + URL_CUST + "account-info";
    private static final String URL_PURCHASES = HOST + URL_CUST + "purchases";
    private static final String URL_AUTH_PURCHASE = HOST + URL_CUST + "authorize-purchase";
    private static final String URL_CONFIRM_PURCHASE = HOST + URL_CUST + "confirm-purchase";
    private static final String URL_GET_BRANCHES = HOST + URL_CUST + "get-stores";
    private static final String URL_GET_OFFERS = HOST + URL_CUST + "get-offers";
    private static final String URL_SIGN_UP = HOST + "/customer/create";
    private static final String URL_SEND_INFO = HOST + URL_CUST + "create-info";
    private Gson gson;
//    private final String clientId;
//    private final String secretId;

    public static final String SIMPLE_DT_FOMRATE = "yyyy-MM-dd hh:mm:ss";

    public CustomerSdk() {
        gson = new GsonBuilder().setDateFormat(SIMPLE_DT_FOMRATE).create();
//        this.clientId = clientId;
//        this.secretId = secretId;
    }


    public void authorize(String name, String pass) {
    }

    public void sendAppToken(String token) {
    }

    public void getNearestBranches(double longitude, double attitude) {
    }

    public void confirmPurchase(long purchaseId) {
    }

    public void getMyStats() {

    }

    public void getOffers() {

    }

    public void getPurchases() {

    }

    public long signUp(String phone, String pass, String nick) {
        Request signupReq = new SignupReq(phone, pass, nick);
        try {
            String resp = HttpClient.postRequest(URL_SIGN_UP, null, signupReq.toJson());
            SignupResp sResp = gson.fromJson(resp, SignupResp.class);
            return sResp.getCustomerId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
