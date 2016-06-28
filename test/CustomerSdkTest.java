import com.viaphone.sdk.CustomerSdk;
import org.junit.Assert;
import org.junit.Test;

public class CustomerSdkTest {

    private String accessToken;
    private CustomerSdk sdk;


    public CustomerSdkTest() {
        sdk = new CustomerSdk();

    }

    @Test
    public void signupCustomer() {
        long cusomerId = sdk.signUp("8555666", "12345", "mikkey");
        Assert.assertNotEquals(cusomerId,0);
    }

/*

    public static void main(String[] args) {
        CustomerSdkTest test = new CustomerSdkTest();
        test.signupCustomer();
    }
*/

}
