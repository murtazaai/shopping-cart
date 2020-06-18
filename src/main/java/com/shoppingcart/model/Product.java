package com.shoppingcart.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private String name;
    private BigDecimal price;
    private BigDecimal taxAmount;
    private Boolean isFree;

    public Product() {
        this.isFree = Boolean.FALSE;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = round(price, 2);
    }

    public Boolean getIsFree() {
        return this.isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }


    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = round(price, 2);
        this.isFree = Boolean.FALSE;
        this.taxAmount = BigDecimal.valueOf(0);
    }

    public Product(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        this.name = product.name;
        this.price = product.getPrice();
        this.isFree = Boolean.FALSE;
        this.taxAmount = BigDecimal.valueOf(0);
    }

    public static BigDecimal round(BigDecimal value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = value.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }
}
