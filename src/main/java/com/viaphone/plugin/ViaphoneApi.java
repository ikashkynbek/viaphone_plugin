package com.viaphone.plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viaphone.plugin.model.*;
import com.viaphone.plugin.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.viaphone.plugin.HttpClient.getRequestJson;
import static com.viaphone.plugin.HttpClient.postRequest;


public class ViaphoneApi {

    public static String HOST = "http://default-environment-xt3p4dpnej.elasticbeanstalk.com";
    //    public static String HOST = "http://ikashkynbek.com";
    public static String ACCESS_TOKEN = HOST + "/oauth/token?grant_type=password&client_id=%s&client_secret=%s";
    public static String API_ROOT = HOST + "/api/merchant";
    public static final String CREATE_PURCHASE = API_ROOT + "/create-payment-token";
    public static final String LOOKUP_PURCHASE = API_ROOT + "/lookup-payment";
    public static final String PURCHASE_STATUS = API_ROOT + "/payment-status";

    private String clientId;
    private String clientSecret;
    private OauthToken token;
    private Gson gson;
    private List<CreateResp> purchasedList = Collections.synchronizedList(new ArrayList<>());

    public ViaphoneApi(String clientId, String clientSecret) throws Exception {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        gson = new GsonBuilder().create();
        token = getAccessToken();
        if (token == null || token.getAccess_token() == null) {
            throw new Exception("Access token is null");
        }
    }

    public void executeTask(CreateResp createResp) {
        Runnable runnable = () -> {
            try {
                PurchaseStatus status = PurchaseStatus.CREATED;
                while (status == PurchaseStatus.CREATED) {
                    System.out.println("Get status for payment: " + createResp.getPaymentId());
                    PurchaseStatusResp resp = getPurchaseStatus(createResp.getPaymentId());
                    if (resp != null && resp.getStatus().equals(PurchaseStatusResp.Status.OK)) {
                        status = resp.getPaymentStatus();
                        if (status == PurchaseStatus.AUTHORIZED) {
                            LookupResp lookupResp = lookupPurchase(createResp.getPaymentId());
                            System.out.println(lookupResp);
                        }
                    }
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public CreateResp createPurchase(List<Product> items) {
        long ref = Utils.nextRef();
        double amount = 0;
        for (Product item : items) {
            amount += item.getPrice() * item.getQty();
        }
        CreateResp resp = (CreateResp) sendRequest(CREATE_PURCHASE, new CreateReq(ref, amount, items));
        executeTask(resp);
        return resp;
    }

    public PurchaseStatusResp getPurchaseStatus(long purchaseId) {
        return (PurchaseStatusResp) sendRequest(PURCHASE_STATUS, new PurchaseStatusReq(Utils.nextRef(), purchaseId));
    }

    public LookupResp lookupPurchase(long purchaseId) {
        return (LookupResp) sendRequest(LOOKUP_PURCHASE, new LookupReq(Utils.nextRef(), purchaseId));
    }

    private Object sendRequest(String url, Object obj) {
        String result = postRequest(url, token.getAccess_token(), gson.toJson(obj));
        System.out.println(result);
        if (obj instanceof CreateReq) {
            return gson.fromJson(result, CreateResp.class);
        } else if (obj instanceof PurchaseStatusReq) {
            return gson.fromJson(result, PurchaseStatusResp.class);
        } else if (obj instanceof LookupReq) {
            return gson.fromJson(result, LookupResp.class);
        }
        return result;
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

        CreateResp resp = api.createPurchase(items);
        File file = Utils.generateQr(resp.getToken());
        System.out.println(resp);

    }
}
