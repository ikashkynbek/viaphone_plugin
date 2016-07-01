import com.viaphone.sdk.CustomerSdk;
import com.viaphone.sdk.model.customer.ConfirmPurchaseResp;
import com.viaphone.sdk.model.customer.PurchaseAuthResp;

public class CustomerSdkTest {

    private static final String HOST = "http://localhost:8081";
    private static final String PHONE = "77785522433";
    private static final String PASS = "123";
    private static final String NICK = "mikkey";
    private String accessToken;
    private CustomerSdk sdk;


    public CustomerSdkTest() throws Exception {
        sdk = new CustomerSdk(HOST, PHONE, PASS);
    }

    public PurchaseAuthResp authPurchase(String code) throws Exception {
        return sdk.authorizePurchase(code);
    }

    public ConfirmPurchaseResp confirmPurchase(Long id) throws Exception {
        return sdk.confirmPurchase(id);
    }
}
