package com.viaphone.plugin.model;


public class PurchaseStatusResp {

    private long ref;
    private PurchaseStatus paymentStatus;
    private Status status;

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public PurchaseStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PurchaseStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " paymentStatus: " + paymentStatus + " status: " + status;
    }

    public enum Status {
        OK,
        REQUIRED_FIELD_NULL,
        MERCHANT_NOT_FOUND,
        NOT_OWN_PAYMENT,
        PAYMENT_NOT_FOUND
    }
}
