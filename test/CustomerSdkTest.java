import com.viaphone.sdk.CustomerSdk;
import com.viaphone.sdk.model.CustomerBranch;
import com.viaphone.sdk.model.CustomerInfo;
import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.Offer;
import com.viaphone.sdk.model.enums.PurchaseStatus;

import java.util.List;

public class CustomerSdkTest {

    private static final String HOST = "http://localhost:8081";
    private static final String PHONE = "77785522433";
    private static final String PASS = "123";
    private CustomerSdk sdk;

    public CustomerSdkTest() throws Exception {
        sdk = new CustomerSdk(HOST, PHONE, PASS);
        List<CustomerBranch> branches = sdk.getBranches();
        CustomerInfo customerInfo = sdk.getMyStats();
        List<Offer> offers = sdk.getOffers();
        List<CustomerPurchase> purchases = sdk.getPurchases(PurchaseStatus.COMPLETED);
        System.out.println(branches);
        System.out.println(customerInfo);
        System.out.println(offers);
        System.out.println(purchases);

    }

    public void authPurchase(String code) throws Exception {
        sdk.authorizePurchase(code);
    }

    public void confirmPurchase(Long id) throws Exception {
        sdk.confirmPurchase(id);
    }

    public static void main(String[] args) throws Exception {
        new CustomerSdkTest();
    }
}
