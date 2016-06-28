package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

public class SignupResp extends Response {
    Long customerId;


    public SignupResp() {
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }

}
