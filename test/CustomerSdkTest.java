import com.viaphone.sdk.CustomerSdk;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CustomerSdkTest {

    private CustomerSdk sdk;

    public CustomerSdkTest() throws Exception {
        sdk = new CustomerSdk("", "");
    }

    @Test
    public void signupCustomer() throws IOException {
        Long cusomerId = sdk.signUp("8555666", "12345", "mikkey");
        Assert.assertNotEquals(cusomerId, null);
    }

/*

    public static void main(String[] args) {
        CustomerSdkTest test = new CustomerSdkTest();
        test.signupCustomer();
    }
*/

}
