package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class ConfirmPurchaseReq extends Request {

    private final Long purchaseId;

    public ConfirmPurchaseReq(long paymentId) {
        this.purchaseId = paymentId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }
}
