import com.viaphone.sdk.CustomerSdk;
import com.viaphone.sdk.model.Response;

public class CustomerSdkTest {

    private static final String HOST = "http://localhost:8081";
    private static final String PHONE = "77785522433";
    private static final String PASS = "7409";
    private CustomerSdk sdk;

    CustomerSdkTest(String proxyHost, Integer proxyPort) throws Exception {
        if (proxyHost != null) {
            sdk = new CustomerSdk(HOST, PHONE, PASS, proxyHost, proxyPort);
        } else {
            sdk = new CustomerSdk(HOST, PHONE, PASS);
        }

//        List branches = sdk.getBranches().getData();
//        CustomerInfo customerInfo = (CustomerInfo) sdk.getMyStats().getData().get(0);
//        List offers = sdk.getOffers().getData();
        Response purchases = sdk.getHistory();
//        System.out.println(branches);
//        System.out.println(customerInfo);
//        System.out.println(offers);
        System.out.println(purchases);
//        Response response = sdk.setFavoriteCampaign(23L, true);
//        System.out.println(response);
    }

    void authPurchase(String code) throws Exception {
        sdk.authorizePurchase(code);
    }

    void confirmPurchase(Long id) throws Exception {
        sdk.confirmPurchase(id);
    }

    public static void main(String[] args) throws Exception {
        String proxyHost = "190.248.134.70";
        int proxyPort = 8080;
        new CustomerSdkTest(null, proxyPort);
    }
}
