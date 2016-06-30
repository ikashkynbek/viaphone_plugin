package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class AppTokenReq extends Request {

    private final String token;

    public AppTokenReq(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "\n\ttoken: " + token + super.toString();
    }
}
