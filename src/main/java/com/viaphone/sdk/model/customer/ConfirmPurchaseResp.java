package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.enums.PurchaseStatus;

public class ConfirmPurchaseResp extends Response {

    private PurchaseStatus purchaseStatus;
    private Long purchaseId;

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseStatus: " + purchaseStatus +
                "\n\tpurchaseId: " + purchaseId +
                super.toString();
    }
}
