package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Purchase;
import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.List;

public class PurchasesResp extends Response {

    private List<Purchase> purchases = new ArrayList<>();

    public PurchasesResp(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public boolean hasPurchases() {
        return purchases.size() > 0;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
