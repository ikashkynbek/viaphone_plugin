package com.viaphone.sdk.model.customer;


public class SignupReq {

    private final String phone;

    public SignupReq(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "\n\tphone: " + phone;
    }
}
