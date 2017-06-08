package com.viaphone.sdk.model.customer;

public class AppTokenReq  {

    private final boolean isEnabled;
    private final String token;

    public AppTokenReq(boolean isEnabled, String token) {
        this.isEnabled = isEnabled;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "\n\tisEnabled: " + isEnabled
                + "\n\ttoken: " + token;
    }

}
