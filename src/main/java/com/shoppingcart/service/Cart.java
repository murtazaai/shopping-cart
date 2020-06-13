package com.shoppingcart.service;

import com.shoppingcart.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Cart implements ICart {

    private List<Product> products = Collections.synchronizedList(new ArrayList<>());
    private double totalPrice;
    private double totalTaxAmount;
    private double totalPriceWithTax;

    public Cart() {
        this.totalPrice = 0;
        this.totalTaxAmount = 0;
        this.totalPriceWithTax = 0;
    }

    public void empty() {
        this.products.clear();
    }

    public void add(Product product) {
        this.products.add(product);
        this.totalPrice = Product.round(this.totalPrice + product.getPrice(), 2);

        double productTaxAmount = Product.round(product.getPrice() * 125 / 1000, 2);
        double productAmountWithTax = Product.round(product.getPrice() + productTaxAmount, 2);

        this.totalTaxAmount = Product.round(this.totalTaxAmount + productTaxAmount, 2);

        this.totalPriceWithTax = this.totalPriceWithTax + productAmountWithTax ;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public double getTotalTaxAmount() { return this.totalTaxAmount; }

    public double getTotalPriceWithTax() { return this.totalPriceWithTax; }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
