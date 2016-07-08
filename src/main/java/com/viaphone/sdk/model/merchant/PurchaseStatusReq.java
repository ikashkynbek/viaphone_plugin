package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Request;

public class PurchaseStatusReq extends Request {

    private final Long purchaseId;

    public PurchaseStatusReq(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId + super.toString();
    }
}
