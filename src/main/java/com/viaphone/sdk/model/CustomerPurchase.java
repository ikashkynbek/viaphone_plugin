package com.viaphone.sdk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerPurchase extends Response {

    private String id;
    private String branchName;
    private String storeName;
    private Double amount;
    private Double discount;
    private String secret;
    private Date created;
    private Date completed;
    private List<ProductItem> products = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "\n\tid: " + id +
                "\n\tbranchName: " + branchName +
                "\n\tstoreName: " + storeName +
                "\n\tamount: " + amount +
                "\n\tdiscount: " + discount +
                "\n\tsecret: " + secret +
                "\n\tcreated: " + created +
                "\n\tcompleted: " + completed +
                "\n\tproducts: " + products +
                super.toString();
    }
}

