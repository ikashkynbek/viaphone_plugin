package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Response;
import com.viaphone.sdk.model.enums.Gender;
import com.viaphone.sdk.model.enums.SocialStatus;

import java.time.LocalDate;

public class MyStatsResp extends Response {

    private Long customerId;
    private String phone;
    private String name;
    private String email;
    private String country;
    private String state;
    private String city;
    private LocalDate birthday;
    private Gender gender;
    private SocialStatus socialStatus;
    private String facebookId;
    private String googleId;
    private String instagramId;
    private String twitterId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SocialStatus getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @Override
    public String toString() {
        return "\n\tcustomerId: " + customerId +
                "\n\tphone: " + phone +
                "\n\tname: " + name +
                "\n\temail: " + email +
                "\n\tcountry: " + country +
                "\n\tstate: " + state +
                "\n\tcity: " + city +
                "\n\tbirthday: " + birthday +
                "\n\tgender: " + gender +
                "\n\tsocialStatus: " + socialStatus +
                "\n\tfacebookId: " + facebookId +
                "\n\tgoogleId: " + googleId +
                "\n\tinstagramId: " + instagramId +
                "\n\ttwitterId: " + twitterId +
                super.toString();
    }
}
