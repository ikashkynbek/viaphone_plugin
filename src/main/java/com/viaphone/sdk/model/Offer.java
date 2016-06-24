package com.viaphone.sdk.model;


import java.util.Date;

public class Offer {

    public static final String PERCENT = "PERCENT";
    public static final String MONEY = "MONEY";

    private String manufacturerName;
    private Double customerDiscount;
    private Date startDate;
    private Date endDate;
    private String productName;
    private String discountUnit;

    public void update(Offer off) {
        if (off.manufacturerName != null) {
            manufacturerName = off.manufacturerName;
        }
        if (off.customerDiscount != null) {
            customerDiscount = off.customerDiscount;
        }
        if (off.startDate != null) {
            startDate = off.startDate;
        }
        if (off.endDate != null) {
            endDate = off.endDate;
        }
        if (off.productName != null) {
            productName = off.productName;
        }
        if (off.discountUnit != null) {
            discountUnit = off.discountUnit;
        }
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getDiscountUnit() {
        return discountUnit;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }


    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    public String getProductName() {
        return productName;
    }

    public enum DiscountUnit {
        PERCENT,
        MONEY
    }

}
