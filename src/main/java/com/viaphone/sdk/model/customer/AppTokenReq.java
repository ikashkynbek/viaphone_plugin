package com.viaphone.sdk.model.customer;

public class AppTokenReq  {

    private final String token;

    public AppTokenReq(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "\n\ttoken: " + token;
    }
}
