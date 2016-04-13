package com.viaphone.plugin.model;


import java.util.List;

public class CreateReq {

    private Long ref;
    private Double amount;
    private List<Product> details;

    public CreateReq (Long ref, Double amount, List<Product> details) {
        this.ref = ref;
        this.amount = amount;
        this.details = details;
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public List<Product> getDetails() {
        return details;
    }

    public void setDetails(List<Product> details) {
        this.details = details;
    }
}
