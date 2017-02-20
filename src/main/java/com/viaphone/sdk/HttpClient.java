package com.viaphone.sdk;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.viaphone.sdk.model.*;
import com.viaphone.sdk.model.customer.SetFavoriteReq;
import com.viaphone.sdk.model.enums.MessageType;
import com.viaphone.sdk.model.merchant.CreateResp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import static com.viaphone.sdk.utils.gson.GsonHelper.fromJson;
import static com.viaphone.sdk.utils.gson.GsonHelper.toJson;

class HttpClient {

    private final String tokenUrl;
    private final boolean hasProxy;
    private final String proxyHost;
    private final Integer proxyPort;
    private OauthToken token;

    HttpClient(String tokenUrl, boolean hasProxy, String proxyHost, Integer proxyPort) throws Exception {
        this.tokenUrl = tokenUrl;
        this.hasProxy = hasProxy;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        getAccessToken();
    }

    Response sendGetRequest(String url) throws Exception {
        Object result = getRequest(url);
        if (result instanceof OauthToken) {
            getAccessToken();
            result = getRequest(url);
        }
        return (Response) result;
    }

    Response sendPostRequest(String url, Object obj) throws Exception {
        Object result = postRequest(url, obj);
        if (result instanceof OauthToken) {
            getAccessToken();
            result = getRequest(url);
        }
        return (Response) result;
    }

    private void getAccessToken() throws Exception {
        CloseableHttpClient client = createHttpClient();
        HttpGet request = new HttpGet(tokenUrl);
        HttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        token =  (OauthToken) fromJson(result, OauthToken.class);
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    private Object getRequest(String url) throws Exception {
        HttpResponse response = sendGet(url);
        return parseResponse(response);
    }

    private Object postRequest(String url, Object obj) throws Exception {
        HttpResponse response = sendPost(url, toJson(obj));
        return parseResponse(response);
    }

    private Object parseResponse(HttpResponse response) throws Exception {
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        if (code == 200) {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(result).getAsJsonObject();
            MessageType type = MessageType.fromValue(obj.get("messageType").getAsInt());
            switch (type) {
                case OFFERS:
                    return fromJson(result, new TypeToken<Response<Offer>>() {});
                case PURCHASE:
                    return fromJson(result, new TypeToken<Response<CustomerPurchase>>() {});
                case CUSTOMER_INFO:
                    return fromJson(result, new TypeToken<Response<CustomerInfo>>() {});
                case BRANCHES:
                    return fromJson(result, new TypeToken<Response<CustomerBranch>>() {});
                case FAVORITE_CAMPAIGN:
                    return fromJson(result, new TypeToken<Response<SetFavoriteReq>>() {});
                case CREATE_PURCHASE:
                    return fromJson(result, new TypeToken<Response<CreateResp>>() {});
                case PURCHASE_COMMENTS:
                    return fromJson(result, new TypeToken<Response<String>>() {});
                default:
                    return fromJson(result, new TypeToken<Response<String>>() {});
            }
        } else if (code == 401) {
            if (result.contains("Access token expired: " + token.getAccess_token())) {
                return fromJson(result, OauthToken.class);
            }
        }
        throw new ClassCastException(result);
    }

    private HttpResponse sendGet(String url) throws Exception {
        CloseableHttpClient client = createHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", "Bearer " + token.getAccess_token());
        return client.execute(get);
    }

    private HttpResponse sendPost(String url, String content) throws Exception {
        CloseableHttpClient client = createHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + token.getAccess_token());
        HttpEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
        post.setEntity(entity);
        return client.execute(post);
    }

    private CloseableHttpClient createHttpClient() {
        HttpClientBuilder builder = HttpClients.custom();
        if (hasProxy) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            builder.setRoutePlanner(routePlanner);
        }
        return builder.build();
    }
}
