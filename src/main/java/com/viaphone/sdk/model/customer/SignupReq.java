package com.viaphone.sdk.model.customer;


public class SignupReq {

    private final String phone;
    private String promoCode;

    public SignupReq(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public String toString() {
        return "\n\tphone: " + phone +
                "\n\tpromoCode: " + promoCode;
    }
}
