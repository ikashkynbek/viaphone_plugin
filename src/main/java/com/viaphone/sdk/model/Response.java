package com.viaphone.sdk.model;


import com.viaphone.sdk.model.enums.MessageType;

import java.util.Collections;
import java.util.List;

public class Response<T> {

    private final List<T> data;
    private final int messageType;
    private final int status;
    private final Long timestamp;
    private final String error;

    public Response() {
        this(Collections.emptyList(), MessageType.EMPTY);
    }

    public Response(T data, MessageType type) {
        this(Collections.singletonList(data), type);
    }

    public Response(List<T> data, MessageType type) {
        this.data = data;
        this.status = STATUS.OK.getValue();
        this.messageType = type.getValue();
        this.timestamp = System.currentTimeMillis();
        this.error = null;
    }

    public Response(STATUS status, String error) {
        this.data = Collections.emptyList();
        this.status = status.getValue();
        this.messageType = MessageType.EMPTY.getValue();
        this.error = error;
        this.timestamp = System.currentTimeMillis();
    }

    public List getData() {
        return data;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getStatus() {
        return status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public enum STATUS {
        OK(0),
        USER_NOT_FOUND(1),
        EMPTY_REQUEST(2),
        REQUIRED_PARAM_NULL(3),
        ERROR(4);

        private int value;

        private STATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
