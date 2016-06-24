package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Response;

public class MyStatsResp extends Response{

    private String phone;
    private String name;
    private String email;

    public MyStatsResp(String phone, String name, String email) {
        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
