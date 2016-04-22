package com.viaphone.plugin.model;

public class PurchaseStatusReq {

    private Long ref;
    private Long purchaseId;

    public PurchaseStatusReq(Long ref, long purchaseId) {
        this.ref = ref;
        this.purchaseId = purchaseId;
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

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tpurchase: " + purchaseId;
    }
}
