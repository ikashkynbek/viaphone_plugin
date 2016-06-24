package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Request;

public class MyStatsReq extends Request {

    private final Long customerId;

    public MyStatsReq(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
