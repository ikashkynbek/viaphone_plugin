package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class PurchaseAuthResp extends Response {

    private CustomerPurchase purchase;

    public CustomerPurchase getPurchase() {
        return purchase;
    }

    public void setPurchase(CustomerPurchase purchase) {
        this.purchase = purchase;
    }
}
