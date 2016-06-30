package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class PurchaseStatusResp extends Response {

    private PurchaseStatus purchaseStatus;

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseStatus: " + purchaseStatus + super.toString();
    }
}
