package com.viaphone.plugin.model;

public class AuthPurchaseReq {

    private Long ref;
    private String code;

    public AuthPurchaseReq(Long ref, String code) {
        this.ref = ref;
        this.code = code;
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref + "\n\tcode: " + code;
    }

}
