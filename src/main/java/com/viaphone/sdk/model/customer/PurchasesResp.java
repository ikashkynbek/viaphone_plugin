package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.List;

public class PurchasesResp extends Response {

    private List<CustomerPurchase> purchases = new ArrayList<>();

    public boolean hasPurchases() {
        return purchases.size() > 0;
    }

    public List<CustomerPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<CustomerPurchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "\n\tpurchases: " + purchases + super.toString();
    }
}
