package com.viaphone.plugin.model;

public class LookupReq {

    private Long ref;
    private Long paymentId;
    private boolean calcDiscount = false;

    public LookupReq(Long ref, long paymentId) {
        this.ref = ref;
        this.paymentId = paymentId;
        this.calcDiscount = true;
    }

    public LookupReq(Long ref, long paymentId, boolean calcDiscount) {
        this.ref = ref;
        this.paymentId = paymentId;
        this.calcDiscount = calcDiscount;
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

    public boolean isCalcDiscount() {
        return calcDiscount;
    }

    public void setCalcDiscount(boolean calcDiscount) {
        this.calcDiscount = calcDiscount;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tpayment: " + paymentId + "\n\tcalcDiscount: " + calcDiscount;
    }
}
