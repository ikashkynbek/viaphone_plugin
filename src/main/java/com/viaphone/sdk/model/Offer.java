package com.viaphone.sdk.model;


import java.util.Date;

public class Offer {

    public static final String PERCENT = "PERCENT";
    public static final String MONEY = "MONEY";

    private Long id;
    private String type;
    private String ownerName;
    private Double customerDiscount;
    private Date startDate;
    private Date endDate;
    private String productName;
    private String discountUnit;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Double getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(Double customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(String discountUnit) {
        this.discountUnit = discountUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n\tid: " + id +
                "\n\ttype: " + type +
                "\n\townerName: " + ownerName +
                "\n\tcustomerDiscount: " + customerDiscount +
                "\n\tstartDate: " + startDate +
                "\n\tendDate: " + endDate +
                "\n\tproductName: " + productName +
                "\n\tdiscountUnit: " + discountUnit +
                "\n\tdescription: " + description;
    }
}
