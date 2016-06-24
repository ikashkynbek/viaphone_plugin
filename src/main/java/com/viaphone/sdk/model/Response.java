package com.viaphone.sdk.model;

import java.util.Date;

public abstract class Response {
    protected long ref;
    protected Status status;
    protected Date timestamp;

    public long getRef() {
        return ref;
    }

    public Status getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public enum Status {
        OK,
        NOT_CORRECT_SECRET_CODE,
        CUSTOMER_NOT_FOUND,
        CANT_SEND_SECRET_CODE,
        REQUIRED_FIELD_NULL,
        MERCHANT_NOT_FOUND,
        NOT_OWN_PURCHASE,
        PURCHASE_NOT_FOUND,
        ERROR
    }
}
