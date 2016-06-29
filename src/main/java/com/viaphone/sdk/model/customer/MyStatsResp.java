package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Response;

public class MyStatsResp extends Response {

    private String phone;
    private String name;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
