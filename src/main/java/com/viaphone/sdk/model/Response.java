package com.viaphone.sdk.model;


import java.util.Date;

public abstract class Response {

    private long ref;
    private Status status = Status.OK;
    private Date timestamp;
    private String error;

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
        PURCHASE_NOT_AUTHORIZED,
        PURCHASE_ALREADY_AUTHORIZED,
        PURCHASE_ALREADY_COMPLETED,
        TOKEN_NULL_OR_EMPTY,
        ERROR
    }

    @Override
    public String toString() {
        return "\n\tref: " + ref +
                "\n\tstatus: " + status +
                "\n\ttimestamp: " + timestamp +
                "\n\terror: " + error;
    }
}
