package com.viaphone.plugin.model;


public class PurchaseStatusResp {

    private long ref;
    private PurchaseStatus purchaseStatus;
    private Status status;

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " purchaseStatus: " + purchaseStatus + " status: " + status;
    }

    public enum Status {
        OK,
        REQUIRED_FIELD_NULL,
        MERCHANT_NOT_FOUND,
        NOT_OWN_PURCHASE,
        PURCHASE_NOT_FOUND
    }
}
