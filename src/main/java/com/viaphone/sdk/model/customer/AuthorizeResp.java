package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

public class AuthorizeResp extends Response {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;

    public AuthorizeResp(String access_token, String token_type, String refresh_token, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.scope = scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getScope() {
        return scope;
    }

}

