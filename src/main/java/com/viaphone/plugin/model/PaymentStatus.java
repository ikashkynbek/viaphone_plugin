package com.viaphone.plugin.model;

public enum PaymentStatus {
    CREATED,
    AUTHORIZED,
    FUNDED,
    INTRANSIT,
    SETTLED,
    NOT_ENOUGH_FUNDS,
    REFUNDED,
    PARTIALLY_REFUNDED,
    CANCELED;

    private String errorMsg;

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}