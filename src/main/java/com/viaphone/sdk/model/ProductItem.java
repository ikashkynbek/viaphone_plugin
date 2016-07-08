package com.viaphone.sdk.model;

public class ProductItem {

    private String barCode;
    private String name;
    private String category;
    private String brand;
    private Integer qty;
    private Double price;
    private Double discount;
    private String type;

    public ProductItem() {
    }

    public ProductItem(String barCode, String name, String category, String brand, String type, Integer qty, Double price) {
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

    public Integer getQty() {
        return qty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "\n\t\tbarCode: " + barCode +
                "\n\t\tname: " + name +
                "\n\t\tcategory: " + category +
                "\n\t\tbrand: " + brand +
                "\n\t\tqty: " + qty +
                "\n\t\tprice: " + price +
                "\n\t\tdiscount: " + discount +
                "\n\t\ttype: " + type;
    }
}
