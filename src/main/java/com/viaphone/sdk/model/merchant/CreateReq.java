package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.Product;
import com.viaphone.sdk.model.Request;

import java.util.List;

public class CreateReq extends Request{

    private final Double amount;
    private final List<Product> details;

    public CreateReq (Double amount, List<Product> details) {
        this.amount = amount;
        this.details = details;
    }

    public Double getAmount() {
        return amount;
    }
    public List<Product> getDetails() {
        return details;
    }

}
