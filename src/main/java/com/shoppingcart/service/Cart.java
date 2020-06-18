package com.shoppingcart.service;

import com.shoppingcart.model.Offer;
import com.shoppingcart.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Cart implements ICart {

    private List<Product> products = Collections.synchronizedList(new ArrayList<>());
    List<Offer> offers = new ArrayList<>();
    private BigDecimal totalPrice;
    private BigDecimal totalTaxAmount;
    private BigDecimal totalPriceWithTax;
    private BigDecimal discount;

    public Cart() {
        this.totalPrice = BigDecimal.valueOf(0);
        this.totalTaxAmount = BigDecimal.valueOf(0);
        this.totalPriceWithTax = BigDecimal.valueOf(0);
        this.discount = BigDecimal.valueOf(0);
    }

    public void empty() {
        this.products.clear();
    }

    public void add(Product product) {

        this.products.add(product);
        this.totalPrice = Product.round(this.totalPrice.add(product.getPrice()), 2);

        product.setTaxAmount(Product.round(product.getPrice().multiply(BigDecimal.valueOf(125))
                .divide(BigDecimal.valueOf(1000)), 2));

        BigDecimal productAmountWithTax = Product.round(product.getPrice().add(product.getTaxAmount()), 2);

        this.totalTaxAmount = Product.round(this.totalTaxAmount.add(product.getTaxAmount()), 2);

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

    public void createOffer(Offer offer) {
        this.offers.add(offer);
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void applyOffers() {
        this.offers.stream().forEach( offer -> {
            List<Product> list = this.products.stream()
                    .filter(
                            product -> product.getName().equals(offer.getProduct().getName()) && product.getIsFree() == Boolean.FALSE
                    ).collect(Collectors.toList());
            int size = list.size();

            for (int i = 0; i < size; i++) {
                if (size > offer.getThreshold()) {
                    for (int j = 0; i < offer.getFreeItems(); i++) {
                        BigDecimal discount0 = offer.getProduct().getPrice();
                        this.discount = this.discount.add(discount0);
                        this.totalPrice = this.totalPrice.subtract(this.discount);

                        this.totalTaxAmount = this.totalTaxAmount.subtract(list.get(0).getTaxAmount());

                        System.out.println("sdfasdfsda");
                    }
                }
            }
        });

    }

}
