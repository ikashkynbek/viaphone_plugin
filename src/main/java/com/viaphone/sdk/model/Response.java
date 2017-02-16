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

    public Response(String key, Object value, MessageType type) {
        this.data = new DefaultObject(key, value);
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

    class DefaultObject {

        private String key;
        private Object value;

        DefaultObject(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
