package com.viaphone.sdk.model.customer;

public class ConfirmPurchaseReq {
    private final Long purchaseId;

    public ConfirmPurchaseReq(long paymentId) {
        this.purchaseId = paymentId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }
}
