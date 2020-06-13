package com.shoppingcart.model;

import com.shoppingcart.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private String name;
    private double price;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = round(price, 2);
        this.price = price;
    }

    public Product() {

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = round(price, 2);
    }

    public Product(Product product) throws ProductNotFoundException {

        if (product == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        this.name = product.name;
        this.price = product.getPrice();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
