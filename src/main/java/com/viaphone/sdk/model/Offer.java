package com.viaphone.sdk.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class Offer {

    private Long id;
    private String type;
    private String ownerName;
    private Double customerDiscount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double limit;
    private Long productId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "\n\tid: " + id +
                "\n\ttype: " + type +
                "\n\townerName: " + ownerName +
                "\n\tcustomerDiscount: " + customerDiscount +
                "\n\tstartDate: " + startDate +
                "\n\tendDate: " + endDate +
                "\n\tstartTime: " + startTime +
                "\n\tendTime: " + endTime +
                "\n\tlimit: " + limit +
                "\n\tproductId: " + productId +
                "\n\tproductName: " + productName +
                "\n\tdiscountUnit: " + discountUnit +
                "\n\tdescription: " + description;
    }
}
