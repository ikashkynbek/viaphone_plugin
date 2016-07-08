package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class PurchaseStatusResp extends Response {

    private Long purchaseId;
    private PurchaseStatus purchaseStatus;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId +
                "\n\tpurchaseStatus: " + purchaseStatus +
                super.toString();
    }
}
