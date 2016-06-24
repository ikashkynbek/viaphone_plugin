package com.viaphone.sdk.model.merchant;

import com.viaphone.sdk.model.Request;

public class CustomerInfoReq extends Request {

    private final String phoneNumber;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Long birthDate;
    private final String email;
    private final String gender;

    public CustomerInfoReq(String phoneNumber, String password, String firstName, String lastName, Long birthDate, String email, String gender) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public Long getBirthDate() {
        return birthDate;
    }


    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }
}
