package com.viaphone.plugin;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


class HttpClient {

    static String getRequestJson(String url) {
        try {
            org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            int resCode = response.getStatusLine().getStatusCode();

            StringBuilder result = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            if (resCode == 200) {
                return result.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String postRequest(String url, String accessToken, String content) {
        String result = null;
        try {
            org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + accessToken);
            HttpEntity entity = new ByteArrayEntity(content.getBytes("UTF-8"));
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
