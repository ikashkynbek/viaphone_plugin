package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

public class ConfirmPurchaseResp extends Response {

    private String purchaseStatus;
    private long purchaseId;

    public ConfirmPurchaseResp(String purchaseStatus, long purchaseId) {
        this.purchaseStatus = purchaseStatus;
        this.purchaseId = purchaseId;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public long getPurchaseId() {
        return purchaseId;
    }
}
