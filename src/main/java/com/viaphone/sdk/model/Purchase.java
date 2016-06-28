package com.viaphone.sdk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Purchase extends Response implements Serializable, Comparable {


    private String branchName;
    private String storeName;
    private Double amount;
    private Double discount;
    private String secret;
    private Date created;
    private Date completed;
    private List<ProductItem> products = new ArrayList<>();


    public Purchase() {
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

    public void update(Purchase p) {
        if (p.getAmount() != null && p.getAmount() != 0) {
            amount = p.getAmount();
        }
        if (p.created != null) {
            created = p.created;
        }
        if (p.completed != null) {
            completed = p.completed;
        }
        if (p.getStatus() != null) {
            status = p.getStatus();
        }

    }

    @Override
    public int compareTo(Object another) {
        if (another instanceof Purchase) {
            Purchase anotherPayment = (Purchase) another;
            return anotherPayment.created.compareTo(created);
        }
        return -1;
    }

}

