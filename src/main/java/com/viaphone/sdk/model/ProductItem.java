package com.viaphone.sdk.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class ProductItem {

    private Long id;
    @SerializedName(value="productId", alternate={"product_id"})
    private Long productId;
    @SerializedName(value="barCode", alternate={"bar_code"})
    private String barCode;
    private String name;
    private String category;
    private String brand;
    private Integer qty;
    private Double price;
    private Double discount;
    private Map<Long, Double> discounts;
    private String type;
    private Date purchaseDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Map<Long, Double> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Map<Long, Double> discounts) {
        this.discounts = discounts;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "\n\t\tbarCode: " + barCode +
                "\n\t\tproductId: " + productId +
                "\n\t\tname: " + name +
                "\n\t\tcategory: " + category +
                "\n\t\tbrand: " + brand +
                "\n\t\tqty: " + qty +
                "\n\t\tprice: " + price +
                "\n\t\tdiscount: " + discount +
                "\n\t\tdiscounts: " + discounts +
                "\n\t\ttype: " + type;
    }
}
