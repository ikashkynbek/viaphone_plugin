package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.enums.ConfirmType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreateReq {

    private final Double amount;
    private final List<ProductItem> productItems;
    private final Long branchId;
    private ConfirmType confirmType;
    private Date createDate;
    private Long customerId;
    private String comment;
    private Double totalDiscount;
    private Map<Long, Double> billDiscounts;

    public CreateReq(List<ProductItem> details, Long branchId, ConfirmType confirmType) {
        double amount = 0;
        for (ProductItem item : details) {
            amount += item.getPrice() * item.getQty();
        }
        this.amount = amount;
        this.productItems = details;
        this.branchId = branchId;
        this.confirmType = confirmType;
    }

    public CreateReq(List<ProductItem> details, Long branchId, Date createDate, Long customerId, String comment,
                     Double totalDiscount, Map<Long, Double> billDiscounts) {
        double amount = 0;
        for (ProductItem item : details) {
            amount += item.getPrice() * item.getQty();
        }
        this.amount = amount;
        this.productItems = details;
        this.branchId = branchId;
        this.createDate = createDate;
        this.customerId = customerId;
        this.comment = comment;
        this.totalDiscount = totalDiscount;
        this.billDiscounts = billDiscounts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Double getAmount() {
        return amount;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public ConfirmType getConfirmType() {
        return confirmType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getComment() {
        return comment;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public Map<Long, Double> getBillDiscounts() {
        return billDiscounts;
    }

    public Long getBranchId() {
        return branchId;
    }

    @Override
    public String toString() {
        return "\n\tamount: " + amount +
                "\n\tproductItems: " + productItems +
                "\n\tbranchId: " + branchId +
                "\n\tconfirmType: " + confirmType +
                "\n\tcreateDate: " + createDate +
                "\n\tcustomerId: " + customerId +
                "\n\tcomment: " + comment +
                "\n\ttotalDiscount: " + totalDiscount +
                "\n\tbillDiscounts: " + billDiscounts;
    }
}
