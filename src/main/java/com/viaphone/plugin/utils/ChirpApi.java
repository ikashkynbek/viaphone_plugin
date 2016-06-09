package com.viaphone.plugin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

public class ChirpApi {

    private String api = "http://api.chirp.io/v1/";
    private String authUrl = api + "authenticate";
    private String createUrl = api + "chirps";
    private String accessToken;

    public static void main(String[] args) throws IOException {
        new ChirpApi().start("14945ad5-de23-4301-a4b3-a5d4cbe2d936");
    }

    public ChirpApi() throws IOException {
        accessToken = getAccessToken(authUrl, getAuthData());
        System.out.println(accessToken);
    }

    public void start(String token) throws IOException {
        String shortcode = create(token);
        String file = shortcode + ".wav";
        InputStream audioFile = downloadFile(createUrl + "/" + file);
        playSound(audioFile);
    }

    private void playSound(InputStream inputStream) throws IOException {
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
    }

    private String getAuthData() {
        JSONObject obj = new JSONObject();
        obj.put("app_key", "aaa");
        obj.put("app_secret", "aaa");
        obj.put("device_id", "12345678123456781234567812345678");
        return obj.toString();
    }

    private String create(String token) throws IOException {
        JSONObject obj = new JSONObject();
        JSONObject array = new JSONObject();
        array.put("text", token);
        obj.put("data", array);
        obj.put("public", true);

        String jsonStr = postReq(createUrl, obj.toString());
        System.out.println(jsonStr);
        JSONObject res = new JSONObject(new JSONTokener(jsonStr));
        return res.getString("shortcode");
    }

    private String getAccessToken(String url, String data) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        HttpEntity entity = new ByteArrayEntity(data.getBytes("UTF-8"));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        JSONObject res = new JSONObject(new JSONTokener(result));
        return res.getString("access_token");
    }

    private String postReq(String url, String data) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("X-Auth-Token", accessToken);
        post.setHeader("Content-Type", "application/json");
        HttpEntity entity = new ByteArrayEntity(data.getBytes("UTF-8"));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }

    private InputStream downloadFile(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.setHeader("X-Auth-Token", accessToken);
        HttpResponse response = client.execute(request);
        return response.getEntity().getContent();
    }
}
