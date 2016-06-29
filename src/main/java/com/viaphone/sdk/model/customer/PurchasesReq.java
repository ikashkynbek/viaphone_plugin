package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class PurchasesReq extends Request {

    private final Status status;

    public PurchasesReq(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        HISTORY, ACTIVE
    }
}
