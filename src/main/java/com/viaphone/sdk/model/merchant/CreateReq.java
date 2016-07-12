package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.Request;
import com.viaphone.sdk.model.enums.ConfirmType;

import java.util.Date;
import java.util.List;

public class CreateReq extends Request {

    private final Double amount;
    private final List<ProductItem> productItems;
    private final ConfirmType confirmType;
    private Date createDate;  //todo only for purchase generation

    public CreateReq(Double amount, List<ProductItem> details, ConfirmType confirmType) {
        this.amount = amount;
        this.productItems = details;
        this.confirmType = confirmType;
    }

    public void setCreateDate(Date ceateDate) {
        this.createDate = ceateDate;
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

    @Override
    public String toString() {
        return "\n\tamount: " + amount +
                "\n\tproductItems: " + productItems +
                "\n\tconfirmType: " + confirmType +
                "\n\tcreateDate: " + createDate +
                super.toString();
    }
}
