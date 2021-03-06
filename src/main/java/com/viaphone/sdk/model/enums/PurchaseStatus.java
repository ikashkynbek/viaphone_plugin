package com.viaphone.sdk.model.enums;

public enum PurchaseStatus {

    CREATED,
    AUTHORIZED,
    COMPLETED,
    CANCELED;

    private String errorMsg;

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}