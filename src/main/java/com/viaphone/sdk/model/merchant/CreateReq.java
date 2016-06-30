package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.ProductItem;
import com.viaphone.sdk.model.Request;

import java.util.Date;
import java.util.List;

public class CreateReq extends Request {

    private final Double amount;
    private final List<ProductItem> productItems;
    private Date createDate;  //only for purchase generation

    public CreateReq(Double amount, List<ProductItem> details) {
        this.amount = amount;
        this.productItems = details;
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

    @Override
    public String toString() {
        return "\n\tamount: " + amount +
                "\n\tproductItems: " + productItems +
                "\n\tcreateDate: " + createDate +
                super.toString();
    }
}
