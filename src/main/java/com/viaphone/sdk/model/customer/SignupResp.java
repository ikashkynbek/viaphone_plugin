package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

public class SignupResp extends Response {

    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "\n\tcustomerId: " + customerId + super.toString();
    }
}
