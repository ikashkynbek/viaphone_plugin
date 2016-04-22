package com.viaphone.plugin.model;

public class LookupReq {

    private Long ref;
    private Long purchaseId;
    private boolean calcDiscount = false;

    public LookupReq(Long ref, long purchaseId) {
        this.ref = ref;
        this.purchaseId = purchaseId;
        this.calcDiscount = true;
    }

    public LookupReq(Long ref, long purchaseId, boolean calcDiscount) {
        this.ref = ref;
        this.purchaseId = purchaseId;
        this.calcDiscount = calcDiscount;
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public boolean isCalcDiscount() {
        return calcDiscount;
    }

    public void setCalcDiscount(boolean calcDiscount) {
        this.calcDiscount = calcDiscount;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tpurchase: " + purchaseId + "\n\tcalcDiscount: " + calcDiscount;
    }
}
