package com.viaphone.plugin.model;

public class PurchaseStatusReq {

    private Long ref;
    private Long paymentId;

    public PurchaseStatusReq(Long ref, long paymentId) {
        this.ref = ref;
        this.paymentId = paymentId;
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tpayment: " + paymentId;
    }
}
