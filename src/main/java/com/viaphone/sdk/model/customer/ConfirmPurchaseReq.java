package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class ConfirmPurchaseReq extends Request {

    private final Long purchaseId;

    public ConfirmPurchaseReq(Long paymentId) {
        this.purchaseId = paymentId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId + super.toString();
    }
}
