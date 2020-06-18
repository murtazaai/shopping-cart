package com.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Offer {
    private Product product;
    private int threshold;
    private int freeItems;

    public Offer() {

    }

    public Offer(Product product, int threshold, int freeItems) {
        this.product = product;
        this.product.setIsFree(Boolean.TRUE);
        this.threshold = threshold;
        this.freeItems = freeItems;
    }


    public Product getProduct() {
        return this.product;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public int getFreeItems() {
        return this.freeItems;
    }
}
