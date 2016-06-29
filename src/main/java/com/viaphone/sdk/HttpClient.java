package com.viaphone.sdk;

import com.viaphone.sdk.model.customer.*;
import com.viaphone.sdk.model.merchant.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.viaphone.sdk.utils.Utils.fromJson;
import static com.viaphone.sdk.utils.Utils.toJson;


class HttpClient {

    static String getRequestJson(String url) throws IOException {
        org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        StringBuilder result = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    static Object sendRequest(String url, String token, Object obj) throws IOException {
        String result = postRequest(url, token, toJson(obj));
        if (obj instanceof CreateReq) {
            return fromJson(result, CreateResp.class);
        } else if (obj instanceof PurchaseStatusReq) {
            return fromJson(result, PurchaseStatusResp.class);
        } else if (obj instanceof LookupReq) {
            return fromJson(result, LookupResp.class);
        } else if (obj instanceof SignupReq) {
            return fromJson(result, SignupResp.class);
        } else if (obj instanceof CreateInfoReq) {
            return fromJson(result, CreateInfoResp.class);
        } else if (obj instanceof MyStatsReq) {
            return fromJson(result, MyStatsResp.class);
        } else if (obj instanceof OffersReq) {
            return fromJson(result, OffersResp.class);
        } else if (obj instanceof BranchReq) {
            return fromJson(result, BranchResp.class);
        } else if (obj instanceof AppTokenReq) {
            return fromJson(result, AppTokenResp.class);
        } else if (obj instanceof PurchasesReq) {
            return fromJson(result, PurchasesResp.class);
        } else if (obj instanceof PurchaseAuthReq) {
            return fromJson(result, PurchaseAuthResp.class);
        } else if (obj instanceof ConfirmPurchaseReq) {
            return fromJson(result, ConfirmPurchaseResp.class);
        } else {
            return result;
        }
    }

    private static String postRequest(String url, String accessToken, String content) throws IOException {
        org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }
}
