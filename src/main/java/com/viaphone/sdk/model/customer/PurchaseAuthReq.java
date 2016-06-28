package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class PurchaseAuthReq extends Request {

    private final String code;

    public PurchaseAuthReq(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tcode: " + code;
    }

}
