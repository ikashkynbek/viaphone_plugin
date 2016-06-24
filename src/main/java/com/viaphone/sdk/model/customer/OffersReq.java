package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class OffersReq extends Request{

    private final Long customerId;

    public OffersReq(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
