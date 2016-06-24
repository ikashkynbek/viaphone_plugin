package com.viaphone.sdk.model;

public class Branch extends Response {
    private Long id;
    private String name;
    private String phone;
    private int openTime;
    private int closeTime;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;


    public double getDistance() {
        return distance;
    }

    public long getId() {
        return id;
    }

    public void update(Branch newBranch) {
        if (newBranch.distance != 0) {
            this.distance = newBranch.distance;
        }
        if (newBranch.latitude != 0) {
            this.latitude = newBranch.latitude;
        }
        if (newBranch.longitude != 0) {
            this.longitude = newBranch.longitude;
        }
        if (newBranch.address != null) {
            this.address = newBranch.address;
        }
        if (newBranch.address != null) {
            this.address = newBranch.address;
        }
        if (newBranch.openTime != -1) {
            this.openTime = newBranch.openTime;
        }
        if (newBranch.closeTime != -1) {
            this.closeTime = newBranch.closeTime;
        }

    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getOpenTime() {
        return openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
