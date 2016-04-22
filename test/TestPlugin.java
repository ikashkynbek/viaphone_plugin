import com.viaphone.plugin.ResultListener;
import com.viaphone.plugin.ViaphoneApi;
import com.viaphone.plugin.model.Product;
import com.viaphone.plugin.model.PurchaseStatus;
import com.viaphone.plugin.model.PurchaseStatusResp;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin implements ResultListener {

    public static void main(String[] args) throws Exception {
//        String clientId = "ae99f6c6-52f2-4433-85b5-e81c31f5805f";
//        String clientSecret = "3cfe5805-da51-4359-9db1-fc1754ee449f";
        String clientId = "92ae1229-0665-482f-b3e2-9bd103502f23";
        String clientSecret = "c3e10bc9-699e-45c0-b282-3f048e94c8f2";
        TestPlugin test = new TestPlugin();

        ViaphoneApi api = new ViaphoneApi(clientId, clientSecret, test);

        List<Product> items = new ArrayList<>();
        items.add(new Product("Product x", "Produce", "lg", 3, 20));
        items.add(new Product("tv 107", "Produce", "lg", 1, 500));

//        CreateResp resp = api.createPurchase(items);
//        File file = Utils.generateQr(resp.getToken());
//        System.out.println(resp);
        api.executeTask(1L);

    }

    @Override
    public void onAuthorized(double discountPrice) {
        System.out.println("onAuthorized: " + discountPrice);
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
