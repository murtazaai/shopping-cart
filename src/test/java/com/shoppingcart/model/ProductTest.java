package com.shoppingcart.model;

import org.junit.Test;

import com.shoppingcart.exception.ProductNotFoundException;

import static org.junit.Assert.*;

public class ProductTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createEmptyProduct() {
        Product product = new Product();
        assertNotNull(product);
    }

    @Test
    public void createNewProduct() throws ProductNotFoundException {

        Product product = new Product("Dove Soap", 39.9911);
        assertEquals(product.getName(), "Dove Soap");
        assertEquals(product.getPrice(), 39.99, DELTA);

    }
}