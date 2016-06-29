package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Response;

public class LookupResp extends Response {

    private Long purchaseId;
    private Double discountPrice;

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " discountPrice: " + discountPrice + " status: " + status;
    }

}
