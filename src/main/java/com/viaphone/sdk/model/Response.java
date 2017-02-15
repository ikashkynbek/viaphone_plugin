package com.viaphone.sdk.model;


import com.viaphone.sdk.model.enums.MessageType;

public class Response {

    private Object data;
    private MessageType messageType;
    private Long timestamp;

    public Response(Object data, MessageType type) {
        this.data = data;
        this.messageType = type;
        this.timestamp = System.currentTimeMillis();
    }

    public Object getData() {
        return data;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
