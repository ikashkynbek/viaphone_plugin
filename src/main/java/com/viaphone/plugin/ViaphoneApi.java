package com.viaphone.plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viaphone.plugin.model.CreateReq;
import com.viaphone.plugin.model.CreateResp;
import com.viaphone.plugin.model.OauthToken;
import com.viaphone.plugin.model.Product;
import com.viaphone.plugin.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.viaphone.plugin.HttpClient.getRequestJson;
import static com.viaphone.plugin.HttpClient.postRequest;


public class ViaphoneApi {

    public static String HOST = "http://default-environment-xt3p4dpnej.elasticbeanstalk.com";
    //    public static String HOST = "http://ikashkynbek.com";
    public static String ACCESS_TOKEN = HOST + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
    public static String API_ROOT = HOST + "/api/merchant";
    public static final String CREATE_PAYMENT = API_ROOT + "/create-payment-token";

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private Gson gson;

    public ViaphoneApi(String clientId, String clientSecret) throws Exception {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        gson = new GsonBuilder().create();
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public CreateResp createPayment(List<Product> items) {
        long ref = Utils.nextRef();
        double amount = 0;
        for (Product item : items) {
            amount += item.getPrice() * item.getQty();
        }
        return (CreateResp) sendRequest(CREATE_PAYMENT, new CreateReq(ref, amount, items));
    }

    private Object sendRequest(String url, Object obj) {
        String result = postRequest(url, token.getAccess_token(), gson.toJson(obj));
        if (obj instanceof CreateReq) {
            return gson.fromJson(result, CreateResp.class);
        }
        return obj;
    }

    private OauthToken getAccessToken() {
        String url = String.format(ACCESS_TOKEN, clientId, clientSecret);
        return gson.fromJson(getRequestJson(url), OauthToken.class);
    }

    public static void main(String[] args) throws Exception {
        String clientId = "ae99f6c6-52f2-4433-85b5-e81c31f5805f";
        String clientSecret = "3cfe5805-da51-4359-9db1-fc1754ee449f";
//        String clientId = "92ae1229-0665-482f-b3e2-9bd103502f23";
//        String clientSecret = "c3e10bc9-699e-45c0-b282-3f048e94c8f2";

        ViaphoneApi api = new ViaphoneApi(clientId, clientSecret);

        List<Product> items = new ArrayList<>();
        items.add(new Product("apple china", "grocery", "lg", 1, 550));

        CreateResp resp = api.createPayment(items);
        System.out.println(resp);

    }
}
