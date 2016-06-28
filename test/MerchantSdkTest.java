import com.viaphone.sdk.ResultListener;
import com.viaphone.sdk.MerchantSdk;
import com.viaphone.sdk.model.merchant.CreateResp;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.PurchaseStatus;
import com.viaphone.sdk.model.merchant.LookupResp;
import com.viaphone.sdk.model.merchant.PurchaseStatusResp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchantSdkTest implements ResultListener {


    private static final String host = "http://localhost:8081";
    private static final String clientId = "6cb4cd53-10c2-4c2a-abc1-f007515acb09";
    private static final String clientSecret = "d2de319a-4613-4d71-8741-f801c5f15a54";

    private MerchantSdk api;

    public static void main(String[] args) throws Exception {
        MerchantSdkTest mt = new MerchantSdkTest();
//        mt.createResponse();
//        mt.loockup(33);
        mt.loockup(33);
    }


    private MerchantSdkTest() throws Exception {
        api = new MerchantSdk(host, clientId, clientSecret, this);
    }

    private void loockup(long purchaseId) throws IOException {
        System.out.println("Sendind LookupReq");
        LookupResp resp = api.lookupPurchase(purchaseId);
        System.out.println("Response: " + resp);
    }

    public void purchaseStatus(long purchaseId) throws IOException {
        System.out.println("Sendind PurchaseStatusReq");
        PurchaseStatusResp resp = api.getPurchaseStatus(purchaseId);
        System.out.println("Response: " + resp);
    }


    private void createResponse() throws Exception {
        System.out.println("Sendind CreateReq");
        List<ProductItem> items = new ArrayList<>();
        items.add(new ProductItem("45745755374y", "Rastishka", "Produce", "Zanone", "Yogurt", 5, 20));
        CreateResp resp = api.createPurchase(items);
        api.playChirp(resp.getToken());
        System.out.println("Response: " + resp);
    }

    @Override
    public void onAuthorized(double discountPrice) {
        System.out.println("onAuthorized: " + discountPrice);
    }

    @Override
    public void onConfirmed(PurchaseStatus status) {
        System.out.println("onConfirmed: " + status);
    }

    @Override
    public void onCancel(PurchaseStatus status) {
        System.out.println("onCancel: " + status);
    }

    @Override
    public void onError(PurchaseStatusResp.Status status) {
        System.out.println("onError: " + status);
    }
}
