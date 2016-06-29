package com.viaphone.sdk.model;

public class ProductItem {

    private String barCode;
    private String name;
    private String category;
    private String brand;
    private int qty;
    private double price;
    private String type;


    public ProductItem() {
    }


    public ProductItem(String barCode, String name, String category, String brand, String type, int qty, double price) {
        this.barCode = barCode;
        this.name = name;
        this.type = type;
        this.category = category;
        this.brand = brand;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQty() {
        return qty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
