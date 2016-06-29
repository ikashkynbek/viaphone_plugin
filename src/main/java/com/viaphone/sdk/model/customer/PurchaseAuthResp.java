package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class PurchaseAuthResp extends Response {

    private long purchaseId;
    private PurchaseStatus purchaseStatus;

    public PurchaseAuthResp(long ref, long purchaseId, PurchaseStatus purchaseStatus) {
        this.ref = ref;
        this.purchaseId = purchaseId;
        this.purchaseStatus = purchaseStatus;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " purchaseId: " + purchaseId + " purchaseStatus:" + purchaseStatus + " status: " + status;
    }
}
