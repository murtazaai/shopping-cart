package com.shoppingcart.service;

import com.shoppingcart.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Cart implements ICart {

    private List<Product> products = Collections.synchronizedList(new ArrayList<>());
    private BigDecimal totalPrice;
    private BigDecimal totalTaxAmount;
    private BigDecimal totalPriceWithTax;

    public Cart() {
        this.totalPrice = BigDecimal.valueOf(0);
        this.totalTaxAmount = BigDecimal.valueOf(0);
        this.totalPriceWithTax = BigDecimal.valueOf(0);
    }

    public void empty() {
        this.products.clear();
    }

    public void add(Product product) {
        this.products.add(product);
        this.totalPrice = Product.round(this.totalPrice.add(product.getPrice()), 2);

        BigDecimal productTaxAmount = Product.round(product.getPrice().multiply(BigDecimal.valueOf(125))
                .divide(BigDecimal.valueOf(1000)), 2);

        BigDecimal productAmountWithTax = Product.round(product.getPrice().add(productTaxAmount), 2);

        this.totalTaxAmount = Product.round(this.totalTaxAmount.add(productTaxAmount), 2);

        this.totalPriceWithTax = Product.round(this.totalPriceWithTax.add(productAmountWithTax), 2) ;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public BigDecimal getTotalTaxAmount() { return this.totalTaxAmount; }

    public BigDecimal getTotalPriceWithTax() { return this.totalPriceWithTax; }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
