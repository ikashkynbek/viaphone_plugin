package com.viaphone.sdk.model.customer;


import com.viaphone.sdk.model.Request;

public class BranchReq extends Request {

    private final Double latitude;
    private final Double longitude;

    public BranchReq(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
