package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

public class SignupReq extends Request {

    private final String phone;
    private final String password;
    private final String nick;

    public SignupReq(String phone, String password, String nick) {
        this.phone = phone;
        this.password = password;
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }

    @Override
    public String toString() {
        return "\n\tphone: " + phone +
                "\n\tpassword: " + password +
                "\n\tnick: " + nick +
                super.toString();
    }
}
