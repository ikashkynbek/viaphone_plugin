package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Response;

public class CustomerInfoResp extends Response {

    private Long customerId;

    public CustomerInfoResp(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
