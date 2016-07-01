import com.viaphone.sdk.CustomerSdk;
import com.viaphone.sdk.ResultListener;
import com.viaphone.sdk.MerchantSdk;
import com.viaphone.sdk.model.customer.ConfirmPurchaseResp;
import com.viaphone.sdk.model.customer.PurchaseAuthResp;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.merchant.CreateResp;
import com.viaphone.sdk.model.ProductItem;
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
        CreateResp createResp = mt.createResponse();
        System.out.println(createResp);

        CustomerSdkTest customerSdk = new CustomerSdkTest();
        PurchaseAuthResp authResp = customerSdk.authPurchase(createResp.getToken());
        System.out.println(authResp);

        System.out.println(mt.lookup(createResp.getPurchaseId()));

        ConfirmPurchaseResp resp = customerSdk.confirmPurchase(createResp.getPurchaseId());
        System.out.println(resp);
    }

    private MerchantSdkTest() throws Exception {
        api = new MerchantSdk(host, clientId, clientSecret, this);
    }

    private LookupResp lookup(long purchaseId) throws IOException {
        return api.lookupPurchase(purchaseId);
    }

    public void purchaseStatus(long purchaseId) throws IOException {
        System.out.println("Sending PurchaseStatusReq");
        PurchaseStatusResp resp = api.getPurchaseStatus(purchaseId);
        System.out.println("Response: " + resp);
    }


    private CreateResp createResponse() throws Exception {
        System.out.println("Sending CreateReq");
        List<ProductItem> items = new ArrayList<>();
        items.add(new ProductItem("123456", "test prod", "Contact Lenses sub 1", "Zanone", "Yogurt", 5, 20));
        items.add(new ProductItem("1234567", "test prod 2", "Contact Lenses sub 1", "Zanone", "Yogurt", 2, 50));
        return api.createPurchase(items);
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
