package com.shoppingcart.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private String name;
    private BigDecimal price;

    public Product() {

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

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = round(price, 2);
    }

    public Product(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        this.name = product.name;
        this.price = product.getPrice();
    }

    public static BigDecimal round(BigDecimal value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = value.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }
}
