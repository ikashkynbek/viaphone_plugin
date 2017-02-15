package com.viaphone.sdk.model.customer;


public class ConfirmPurchaseReq {

    private final Long purchaseId;

    public ConfirmPurchaseReq(Long paymentId) {
        this.purchaseId = paymentId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId;
    }
}
