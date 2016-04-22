package com.viaphone.plugin.model;


public class CreateResp {

    private long ref;
    private long purchaseId;
    private PurchaseStatus purchaseStatus;
    private String token;
    private Status status;
    private ConfirmType confirmType;
    private String qr;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Status getStatus() {
        return status;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ConfirmType getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(ConfirmType confirmType) {
        this.confirmType = confirmType;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " purchaseId: " + purchaseId + " token: " + token
                + " purchaseStatus:" + purchaseStatus + " status: " + status;
    }

    public enum Status {
        ERROR,
        OK,
        REQUIRED_FIELD_NULL,
        CUSTOMER_NOT_FOUND,
        MERCHANT_NOT_FOUND,
        CANT_SEND_SECRET_CODE
    }

    public enum ConfirmType{
        SMS,PUSH,QR
    }
}
