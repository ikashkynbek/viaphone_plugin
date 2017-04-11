package com.viaphone.sdk.model.customer;

public class PromoCodeReq {

    private final String code;

    public PromoCodeReq(String code) {
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
