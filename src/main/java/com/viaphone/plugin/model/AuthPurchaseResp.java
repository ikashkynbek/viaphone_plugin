package com.viaphone.plugin.model;


public class AuthPurchaseResp {

    private long ref;
    private long purchaseId;
    private PurchaseStatus purchaseStatus;
    private Status status;

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
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
        return "ref: " + ref + " purchaseId: " + purchaseId + " purchaseStatus:" + purchaseStatus + " status: " + status;
    }

    public enum Status {
        OK,
        REQUIRED_FIELD_NULL,
        NOT_CORRECT_SECRET_CODE,
        PURCHASE_NOT_FOUND
    }
}
