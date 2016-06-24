package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Request;

public class PurchaseStatusReq extends Request {

    private final Long purchaseId;

    public PurchaseStatusReq(long purchaseId) {
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
