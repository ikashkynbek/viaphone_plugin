package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Request;

public class LookupReq extends Request{

    private final Long purchaseId;
    private boolean calcDiscount = false;

    public LookupReq(long purchaseId) {
        this.purchaseId = purchaseId;
        this.calcDiscount = true;
    }

    public LookupReq(long purchaseId, boolean calcDiscount) {
        this.purchaseId = purchaseId;
        this.calcDiscount = calcDiscount;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }
    public boolean isCalcDiscount() {
        return calcDiscount;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tpurchase: " + purchaseId + "\n\tcalcDiscount: " + calcDiscount;
    }
}
