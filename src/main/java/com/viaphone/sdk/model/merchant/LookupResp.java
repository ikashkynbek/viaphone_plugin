package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Response;

public class LookupResp extends Response {

    private Long purchaseId;
    private Double discountPrice;

    public LookupResp(Long purchaseId, double discountPrice, Status status) {
        this.purchaseId = purchaseId;
        this.discountPrice = discountPrice;
        this.status = status;
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
