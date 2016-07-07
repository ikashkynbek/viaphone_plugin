package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.Response;

public class LookupResp extends Response {

    private CustomerPurchase purchase;

    public CustomerPurchase getPurchase() {
        return purchase;
    }

    public void setPurchase(CustomerPurchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "\n\tpurchase: " + purchase +
                super.toString();
    }
}
