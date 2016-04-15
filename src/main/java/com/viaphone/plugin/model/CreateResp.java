package com.viaphone.plugin.model;


public class CreateResp {

    private long ref;
    private long paymentId;
    private PurchaseStatus paymentStatus;
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

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public PurchaseStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PurchaseStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        return "ref: " + ref + " paymentId: " + paymentId + " token: " + token
                + " paymentStatus:" + paymentStatus + " status: " + status;
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
