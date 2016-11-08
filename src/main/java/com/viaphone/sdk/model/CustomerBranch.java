package com.viaphone.sdk.model;

import java.time.LocalTime;

public class CustomerBranch {

    private String name;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "\n\tname: " + name +
                "\n\tphone: " + phone +
                "\n\topenTime: " + openTime +
                "\n\tcloseTime: " + closeTime +
                "\n\taddress: " + address +
                "\n\tlatitude: " + latitude +
                "\n\tlongitude: " + longitude +
                "\n\tdistance: " + distance;
    }
}
