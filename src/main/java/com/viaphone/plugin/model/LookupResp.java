package com.viaphone.plugin.model;

public class LookupResp {

    private long ref;
    private double discountPrice;
    private Status status;

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " discountPrice: " + discountPrice + " status: " + status;
    }

    public enum Status {
        OK,
        REQUIRED_FIELD_NULL,
        MERCHANT_NOT_FOUND,
        PURCHASE_NOT_FOUND,
        NOT_OWN_PURCHASE
    }
}
