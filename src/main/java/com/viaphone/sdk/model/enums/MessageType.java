package com.viaphone.sdk.model.enums;

public enum MessageType {
    OFFERS(0),
    PURCHASE(1),
    CUSTOMER_INFO(2),
    BRANCHES(3),
    FAVORITE_CAMPAIGN(4),
    EMPTY(5),
    CREATE_PURCHASE(6),
    PURCHASE_COMMENTS(7),
    PRODUCT(8),
    PROMO_CODE(9);

    private int value;

    MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MessageType fromValue(int value) {
        return MessageType.values()[value];
    }
}