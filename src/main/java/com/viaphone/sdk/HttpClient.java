package com.viaphone.sdk;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.viaphone.sdk.model.*;
import com.viaphone.sdk.model.enums.MessageType;
import com.viaphone.sdk.model.merchant.CreateResp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.viaphone.sdk.model.enums.MessageType.*;
import static com.viaphone.sdk.utils.gson.GsonHelper.fromJson;
import static com.viaphone.sdk.utils.gson.GsonHelper.toJson;

class HttpClient {

    static String getRequestJson(String url) throws Exception {
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

    static Object getRequest(String url, String token) throws Exception {
        HttpResponse response = sendGetRequest(url, token);
        return parseResponse(response, token);
    }

    static Object postRequest(String url, String token, Object obj) throws Exception {
        HttpResponse response = sendPostRequest(url, token, toJson(obj));
        return parseResponse(response, token);
    }

    private static Object parseResponse(HttpResponse response, String token) throws Exception {
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        if (code == 200) {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(result).getAsJsonObject();
            MessageType type = MessageType.valueOf(obj.get("messageType").getAsString());
            String data = obj.get("data").toString();
            if (type == CREATE_PURCHASE) {
                return fromJson(data, CreateResp.class);
            } else if (type == LOOKUP_PURCHASE) {
                return fromJson(data, CustomerPurchase.class);
            } else if (type == SAVE_PURCHASES) {
                return true;
            } else if (type == GET_OFFERS) {
                return fromJson(data, new TypeToken<ArrayList<Offer>>() {});
            } else if (type == PURCHASE_COMMENTS) {
                return fromJson(data, new TypeToken<ArrayList<String>>() {});
            } else if (type == SEND_INFO) {
                return true;
            } else if (type == GET_INFO) {
                return fromJson(data, CustomerInfo.class);
            } else if (type == GET_BRANCHES) {
                return fromJson(data, new TypeToken<ArrayList<CustomerBranch>>() {});
            } else if (type == GET_PURCHASES) {
                return fromJson(data, new TypeToken<ArrayList<CustomerPurchase>>() {});
            } else if (type == SEND_APP_TOKEN) {
                return true;
            } else if (type == AUTH_PURCHASE) {
                return fromJson(data, CustomerPurchase.class);
            } else if (type == CONFIRM_PURCHASE) {
                return true;
            }
        } else if (code == 401) {
            if (result.contains("Access token expired: " + token)) {
                return fromJson(result, OauthToken.class);
            }
        }
        throw new ClassCastException(result);
    }

    private static HttpResponse sendGetRequest(String url, String accessToken) throws Exception {
        org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", "Bearer " + accessToken);
        return client.execute(get);
    }

    private static HttpResponse sendPostRequest(String url, String accessToken, String content) throws Exception {
        org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
        post.setEntity(entity);
        return client.execute(post);
    }
}
