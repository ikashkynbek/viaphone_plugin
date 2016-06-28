package com.viaphone.sdk.model;


import java.util.Date;

public abstract class Response {
    protected long ref;
    protected Status status;
    protected Date timestamp;
    protected String error;

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public enum Status {
        OK,
        NOT_CORRECT_SECRET_CODE,
        CUSTOMER_NOT_FOUND,
        CUSTOMER_CUSTOMER_ALREADY_EXIST,
        CANT_SEND_SECRET_CODE,
        REQUIRED_FIELD_NULL,
        MERCHANT_NOT_FOUND,
        NOT_OWN_PURCHASE,
        PURCHASE_NOT_FOUND,
        ERROR
    }


}
