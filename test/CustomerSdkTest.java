import com.viaphone.sdk.CustomerSdk;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CustomerSdkTest {

    private static final String PHONE = "8555666";
    private static final String PASS = "12345";
    private static final String NICK = "mikkey";
    private String accessToken;
    private CustomerSdk sdk;


    public CustomerSdkTest() throws Exception {
        sdk = new CustomerSdk(PHONE, PASS);

    }

//    @Test
    public void signup() throws Exception {
        System.out.println("Signing up customer " + NICK);
        Long resp = sdk.signUp(PHONE, PASS, NICK);
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
