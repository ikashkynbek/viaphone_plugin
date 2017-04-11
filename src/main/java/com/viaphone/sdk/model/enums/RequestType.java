package com.viaphone.sdk.model.enums;

public enum RequestType {
    LOGIN(0, MessageType.EMPTY),
    CREATE_INFO(1, MessageType.EMPTY),
    GET_INFO(2, MessageType.CUSTOMER_INFO),
    GET_OFFERS(3, MessageType.OFFERS),
    GET_BRANCHES(4, MessageType.BRANCHES),
    SEND_TOKEN(5, MessageType.EMPTY),
    GET_HISTORY(6, MessageType.PRODUCT),
    AUTH_PURCHASE(7, MessageType.PURCHASE),
    CONFIRM_PURCHASE(8, MessageType.EMPTY),
    SET_FAVORITE_CAMPAIGN(9, MessageType.FAVORITE_CAMPAIGN),
    CREATE_PURCHASE(10, MessageType.CREATE_PURCHASE),
    LOOKUP_PURCHASE(11, MessageType.PURCHASE),
    SAVE_PURCHASE(12, MessageType.EMPTY),
    GET_PURCHASE_COMMENTS(13, MessageType.PURCHASE_COMMENTS),
    SIGN_UP(14, MessageType.EMPTY),
    GET_PROMO(15, MessageType.PROMO_CODE),
    SET_PROMO(16, MessageType.EMPTY);

    private int value;
    private MessageType messageType;

    RequestType(int value, MessageType messageType) {
        this.value = value;
        this.messageType = messageType;
    }

    public int getValue() {
        return value;
    }

    public static RequestType fromValue(int value) {
        return RequestType.values()[value];
    }

    public MessageType getMessageType() {
        return messageType;
    }
}