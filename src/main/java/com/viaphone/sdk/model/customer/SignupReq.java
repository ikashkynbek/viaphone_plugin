package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class SignupReq extends Request {

    private final String phone;

    public SignupReq(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "\n\tphone: " + phone +
                super.toString();
    }
}
