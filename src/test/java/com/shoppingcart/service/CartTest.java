package com.shoppingcart.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.shoppingcart.model.Product;

public class CartTest {

    private static final double DELTA = 1e-15;
    private Cart cart = new Cart();

    @Test
    public void addAProductToCart()  {
        Product product = new Product("Dove Soap", 39.99123);
        this.cart.add(product);

        assertEquals(cart.getProducts().get(0).getName(), "Dove Soap");
        assertEquals(cart.getProducts().get(0).getPrice(), 39.99, DELTA);
    }

    @Test
    public void addMultipleProductsToCart() {
        for (int i =0; i < 5; i++) {
            cart.add(new Product("Dove" + " " + "Soap", 39.99));
        }

        for (int i =0; i < 5; i++) {
            assertEquals(cart.getProducts().get(0).getName(), "Dove Soap");
            assertEquals(cart.getProducts().get(0).getPrice(), 39.99, DELTA);
        }
    }
}
