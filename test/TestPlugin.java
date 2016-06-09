import com.viaphone.plugin.ResultListener;
import com.viaphone.plugin.ViaphoneApi;
import com.viaphone.plugin.model.CreateResp;
import com.viaphone.plugin.model.Product;
import com.viaphone.plugin.model.PurchaseStatus;
import com.viaphone.plugin.model.PurchaseStatusResp;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin implements ResultListener {

    public static void main(String[] args) throws Exception {
//        String clientId = "ae99f6c6-52f2-4433-85b5-e81c31f5805f";
//        String clientSecret = "3cfe5805-da51-4359-9db1-fc1754ee449f";
        String clientId = "6cb4cd53-10c2-4c2a-abc1-f007515acb09";
        String clientSecret = "d2de319a-4613-4d71-8741-f801c5f15a54";
        TestPlugin test = new TestPlugin();

        ViaphoneApi api = new ViaphoneApi(clientId, clientSecret, test);

        List<Product> items = new ArrayList<>();
        items.add(new Product("Product x", "Produce", "lg", 3, 20));
        items.add(new Product("tv 107", "Produce", "lg", 1, 500));

        CreateResp resp = api.createPurchase(items);
        api.playChirp(resp.getToken());
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
