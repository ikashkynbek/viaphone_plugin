package com.viaphone.sdk.model;


import com.viaphone.sdk.model.enums.CampaignStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Offer {

    private Long id;
    private String type;
    private Long ownerId;
    private String ownerName;
    private Double customerDiscount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double limit;
    private Long productId;
    private String productName;
    private String productCode;
    private Long brandId;
    private String brandName;
    private String discountUnit;
    private String description;
    private CampaignStatus status;
    private List<Long> cohort;
    private List<String> branches;
    private boolean isFavorite;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<Long> getCohort() {
        return cohort;
    }

    public void setCohort(List<Long> cohort) {
        this.cohort = cohort;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "\n\tid: " + id +
                "\n\ttype: " + type +
                "\n\townerId: " + ownerId +
                "\n\townerName: " + ownerName +
                "\n\tcustomerDiscount: " + customerDiscount +
                "\n\tstartDate: " + startDate +
                "\n\tendDate: " + endDate +
                "\n\tstartTime: " + startTime +
                "\n\tendTime: " + endTime +
                "\n\tlimit: " + limit +
                "\n\tproductId: " + productId +
                "\n\tproductName: " + productName +
                "\n\tproductCode: " + productCode +
                "\n\tbrandId: " + brandId +
                "\n\tbrandName: " + brandName +
                "\n\tdiscountUnit: " + discountUnit +
                "\n\tdescription: " + description +
                "\n\tstatus: " + status +
                "\n\tisFavorite: " + isFavorite;
    }
}
