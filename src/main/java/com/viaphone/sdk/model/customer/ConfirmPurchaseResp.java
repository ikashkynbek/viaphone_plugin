package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.enums.PurchaseStatus;

public class ConfirmPurchaseResp extends Response {

    private PurchaseStatus purchaseStatus;
    private long purchaseId;

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }
}
