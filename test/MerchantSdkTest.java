import com.viaphone.sdk.ResultListener;
import com.viaphone.sdk.MerchantSdk;
import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.customer.ConfirmPurchaseResp;
import com.viaphone.sdk.model.customer.PurchaseAuthResp;
import com.viaphone.sdk.model.enums.ConfirmType;
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
        System.out.println("createResp:\n" + createResp);

        CustomerSdkTest customerSdk = new CustomerSdkTest();
        PurchaseAuthResp authResp = customerSdk.authPurchase(createResp.getConfirmCode());
        System.out.println("authResp:\n" + authResp);

        LookupResp lookupResp = mt.lookup(createResp.getPurchaseId());
        System.out.println("lookupResp:\n" + lookupResp);

        ConfirmPurchaseResp confirmResp = customerSdk.confirmPurchase(createResp.getPurchaseId());
        System.out.println("confirmResp:\n" + confirmResp);
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
        items.add(new ProductItem("07124917312", "L'Oreal Paris Men's Expert Hydra-Energetic Ice Cold Eye Roller", "Contact Lenses sub 1", "Zanone", "Yogurt", 5, 20.0));
        items.add(new ProductItem("1234567", "test prod 2", "Contact Lenses sub 1", "Zanone", "Yogurt", 2, 50.0));
        return api.createPurchase(items, ConfirmType.TOKEN);
    }

    @Override
    public void onAuthorized(CustomerPurchase purchase) {
        System.out.println("onAuthorized: " + purchase.toString());
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
