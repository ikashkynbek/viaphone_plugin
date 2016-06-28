package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Request;

public class LookupReq extends Request {

    private final Long purchaseId;

    public LookupReq(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tpurchase: " + purchaseId;
    }
}
