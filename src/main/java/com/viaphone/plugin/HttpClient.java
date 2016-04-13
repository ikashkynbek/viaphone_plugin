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


public class HttpClient {


    public static final String ACCEPT_LANGUAGE = "en-US,en;q=0.5";
    public static final String ACCEPT_JSON = "application/json, text/plain, */*";
    public static final String POST = "POST";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";

    public static String postRequestJson(String url, String payload, Map<String, String> reqProps) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(POST);
        con.setRequestProperty("Accept", ACCEPT_JSON);
        con.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
        if (reqProps != null) {
            for (Map.Entry<String, String> e : reqProps.entrySet()) {
                con.setRequestProperty(e.getKey(), e.getValue());
            }
        }
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(payload);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Payload : " + payload);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

//    public static String getRequest(String url) {
//        try {
//            org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
//            HttpGet request = new HttpGet(url);
//            HttpResponse response = client.execute(request);
//            int resCode = response.getStatusLine().getStatusCode();
//
//            StringBuilder result = new StringBuilder();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            String line;
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            if (resCode == 200) {
//                return result.toString();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String getRequestJson(String url) {
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

    public static String postRequest(String url, String accessToken, String content) {
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

//    private static JSONObject toJson(String jsonString) {
//        JSONObject res = null;
//        try {
//            if (jsonString != null) {
//                res = new JSONObject(new JSONTokener(jsonString));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}
