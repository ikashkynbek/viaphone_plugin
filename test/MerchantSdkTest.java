import com.viaphone.sdk.MerchantSdk;
import com.viaphone.sdk.ResultListener;
import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.enums.CampaignStatus;
import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.merchant.CreateReq;
import com.viaphone.sdk.model.merchant.CreateResp;

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
        Response response = mt.createResponse();
        if (response.getStatus() != 0) {
            System.out.println(response.getError());
            return;
        }

        CreateResp createResp = (CreateResp) response.getData().get(0);
        System.out.println("createResp:\n" + createResp);

        CustomerSdkTest customerSdk = new CustomerSdkTest(null, null);
        customerSdk.authPurchase(createResp.getConfirmCode());

        Response purchase = mt.api.lookupPurchase(createResp.getPurchaseId());
        System.out.println("lookupPurchase:\n" + purchase.getData());
        customerSdk.confirmPurchase(createResp.getPurchaseId());

        Response save = mt.savePurchase();
    }

    private MerchantSdkTest() throws Exception {
        String proxyHost = "190.248.134.70";
        int proxyPort = 8080;
        api = new MerchantSdk(host, clientId, clientSecret);
//        api = new MerchantSdk(clientId, clientSecret, proxyHost, proxyPort);
        System.out.println(api.purchaseComments().getData());;
        System.out.println(api.offers(CampaignStatus.ACTIVE).getData());
    }

    private Response savePurchase() throws Exception {
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

    private Response createResponse() throws Exception {
        System.out.println("Sending CreateReq");
        List<ProductItem> items = new ArrayList<>();
//        ProductItem pi = new ProductItem();
//        pi.setName("2043-MR Ваза-универсальная (x8)");
//        pi.setBarCode("2000043970016");
//        pi.setPrice(150.0);
//        pi.setQty(1);

//        ProductItem pi2 = new ProductItem();
//        pi2.setName("ДК Растишка 110 гр йогурт Клубника");
//        pi2.setBarCode("4870206412596");
//        pi2.setPrice(100.0);
//        pi2.setQty(2);

//        ProductItem pi3 = new ProductItem();
//        pi3.setName("ДК Растишка 110 гр йогурт Банан");
//        pi3.setBarCode("4870206412985");
//        pi3.setPrice(200.0);
//        pi3.setQty(2);
//
        ProductItem pi4 = new ProductItem();
        pi4.setName("ДК Растишка 110 гр йогурт Без фруктов и ягод");
        pi4.setBarCode("4870206412725");
        pi4.setPrice(220.0);
        pi4.setQty(1);

        ProductItem pi5 = new ProductItem();
        pi5.setName("ДК Растишка 110 гр йогурт Яблоко-груша");
        pi5.setBarCode("4870206412602");
        pi5.setPrice(230.0);
        pi5.setQty(2);

//        items.add(pi);
//        items.add(pi2);
//        items.add(pi3);
        items.add(pi4);
        items.add(pi5);
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

//    @Override
//    public void onError(Response.Status status) {
//        System.out.println("onError: " + status);
//    }
}
