package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

public class SignupResp extends Response {

    private Long customerId;

    public SignupResp (Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
