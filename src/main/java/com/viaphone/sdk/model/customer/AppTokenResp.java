package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Response;

public class AppTokenResp extends Response {

    private Long customerId;

    public AppTokenResp(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
