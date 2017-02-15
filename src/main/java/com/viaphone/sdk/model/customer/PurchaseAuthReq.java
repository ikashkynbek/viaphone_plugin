package com.viaphone.sdk.model.customer;

public class PurchaseAuthReq {

    private final String code;

    public PurchaseAuthReq(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "\n\tcode: " + code;
    }
}
