import com.viaphone.sdk.MerchantSdk;
import com.viaphone.sdk.ResultListener;
import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.merchant.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MerchantSdkTest implements ResultListener {


    private static final String host = "http://localhost:8081";
    private static final String clientId = "6cb4cd53-10c2-4c2a-abc1-f007515acb09";
    private static final String clientSecret = "d2de319a-4613-4d71-8741-f801c5f15a54";
    private static final String PATTERN = "yyyy-MM-dd hh:mm:ss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);

    private MerchantSdk api;

    public static void main(String[] args) throws Exception {
        MerchantSdkTest mt = new MerchantSdkTest();
       /* CreateResp createResp = mt.createResponse();
        System.out.println("createResp:\n" + createResp);

        CustomerSdkTest customerSdk = new CustomerSdkTest();
        PurchaseAuthResp authResp = customerSdk.authPurchase(createResp.getConfirmCode());
        System.out.println("authResp:\n" + authResp);

        LookupResp lookupResp = mt.lookup(createResp.getPurchaseId());
        System.out.println("lookupResp:\n" + lookupResp);

        ConfirmPurchaseResp confirmResp = customerSdk.confirmPurchase(createResp.getPurchaseId());
        System.out.println("confirmResp:\n" + confirmResp);*/

        SavePurchasesResp savePurchasesResp = mt.savePurchase();
        System.out.println("savePurchasesResp:\n" + savePurchasesResp);
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

    private SavePurchasesResp savePurchase() throws Exception {
        System.out.println("Sending CreateReq");
        List<ProductItem> items = new ArrayList<>();
        ProductItem pi = new ProductItem();
        pi.setName("2043-MR Ваза-универсальная (x8)");
        pi.setBarCode("2000043970016");
        pi.setPrice(150.0);
        pi.setQty(1);
        items.add(pi);

        ProductItem pi2 = new ProductItem();
        pi2.setName("ДК Растишка 110 гр йогурт Клубника");
        pi2.setBarCode("4870206412596");
        pi2.setPrice(100.0);
        pi2.setQty(2);
        pi2.setDiscount(10.0);
        Map<Long, Double> mapproduct = new LinkedHashMap<>();
        mapproduct.put(19L, 11.0);
        pi2.setDiscounts(mapproduct);
        items.add(pi2);

        Map<Long, Double> mapbill = new LinkedHashMap<>();
        mapbill.put(18L, 5.0);
        String str = "2016-11-03 16:08:10";
        Date date = formatter.parse(str);

        CreateReq req = new CreateReq(items, 1L, date, 1L, "test", 16.0, mapbill);
        List<CreateReq> reqs = new ArrayList<>();
        reqs.add(req);
        return api.savePurchases(reqs);
    }

    private CreateResp createResponse() throws Exception {
        System.out.println("Sending CreateReq");
        List<ProductItem> items = new ArrayList<>();
        ProductItem pi = new ProductItem();
        pi.setName("2043-MR Ваза-универсальная (x8)");
        pi.setBarCode("2000043970016");
        pi.setPrice(150.0);
        pi.setQty(1);

        ProductItem pi2 = new ProductItem();
        pi2.setName("ДК Растишка 110 гр йогурт Клубника");
        pi2.setBarCode("4870206412596");
        pi2.setPrice(100.0);
        pi2.setQty(2);
        items.add(pi);
        items.add(pi2);
        return api.createPurchase(items, 1L, ConfirmType.TOKEN);
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
