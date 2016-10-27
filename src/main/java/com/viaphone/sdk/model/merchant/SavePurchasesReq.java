package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.Request;

import java.util.List;

public class SavePurchasesReq extends Request {

    private final List<CreateReq> purchases;

    public SavePurchasesReq(List<CreateReq> purchases) {
        this.purchases = purchases;
    }

    public List<CreateReq> getPurchases() {
        return purchases;
    }

    @Override
    public String toString() {
        return "\n\tpurchases: " + purchases +
                super.toString();
    }
}
