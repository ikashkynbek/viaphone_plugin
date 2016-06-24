package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Request;

class AuthorizeReq extends Request {

    private final String name;
    private final String pass;

    public AuthorizeReq(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

}

