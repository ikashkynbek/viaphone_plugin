package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class PurchaseStatusResp extends Response{

    private PurchaseStatus purchaseStatus;

    public PurchaseStatusResp(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " purchaseStatus: " + purchaseStatus + " status: " + status;
    }

}
