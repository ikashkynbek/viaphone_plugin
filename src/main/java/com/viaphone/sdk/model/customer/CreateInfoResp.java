package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

public class CreateInfoResp extends Response {

    private Long infoId;

    public CreateInfoResp (Long infoId) {
        this.infoId = infoId;
    }

    public Long getInfoId() {
        return infoId;
    }
}
