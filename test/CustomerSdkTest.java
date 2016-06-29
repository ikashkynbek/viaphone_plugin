import com.viaphone.sdk.CustomerSdk;
import com.viaphone.sdk.model.customer.AuthorizeResp;
import com.viaphone.sdk.model.customer.SignupResp;
import org.junit.Assert;
import org.junit.Test;

public class CustomerSdkTest {

    private static final String PHONE = "8555666";
    private static final String PASS = "12345";
    private static final String NICK = "mikkey";
    private String accessToken;
    private CustomerSdk sdk;


    public CustomerSdkTest() {
        sdk = new CustomerSdk();

    }

    @Test
    public void signup() {
        System.out.println("Signing up customer " + NICK);
        SignupResp resp = sdk.signUp(PHONE, PASS, NICK);
        Assert.assertNotEquals(resp, null);
        System.out.println("Response: " + (resp));
    }

    @Test
    public void authorize() {
        System.out.println("Authorizung customer " + PHONE);
        AuthorizeResp resp = sdk.authorize(PHONE,PASS);
        Assert.assertNotEquals(resp, null);
        System.out.println("Response: " + (resp));
    }

/*

    public static void main(String[] args) {
        CustomerSdkTest test = new CustomerSdkTest();
        test.signupCustomer();
    }
*/

}
