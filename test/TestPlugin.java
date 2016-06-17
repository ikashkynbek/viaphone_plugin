import com.viaphone.plugin.ResultListener;
import com.viaphone.plugin.ViaphoneApi;
import com.viaphone.plugin.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin implements ResultListener {

    public static void main(String[] args) throws Exception {
        String host = "http://localhost:8081";
        String clientId = "6cb4cd53-10c2-4c2a-abc1-f007515acb09";
        String clientSecret = "d2de319a-4613-4d71-8741-f801c5f15a54";
        TestPlugin test = new TestPlugin();

        ViaphoneApi api = new ViaphoneApi(host, clientId, clientSecret, test);

        List<Product> items = new ArrayList<>();
        items.add(new Product("product test", "Produce", "Zanone", 3, 20));

        CreateResp resp = api.createPurchase(items);
        api.playChirp(resp.getToken());
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
