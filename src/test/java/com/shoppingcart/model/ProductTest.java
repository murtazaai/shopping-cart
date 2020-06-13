package com.shoppingcart.model;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createProductWithConstructorInjection() {
        Product product = new Product(new Product("Axe Deo", BigDecimal.valueOf(99.99)));

        assertEquals("Axe Deo", product.getName());
        assertEquals(BigDecimal.valueOf(99.99), product.getPrice());
    }

    @Test
    public void createProductWithSetterInjection() {
        Product product = new Product();
        assertNotNull(product);

        product.setName("Dove Soap");
        assertEquals("Dove Soap", product.getName());

        product.setPrice(BigDecimal.valueOf(39.9911));
        assertEquals(BigDecimal.valueOf(39.99), product.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProductNull() {
        Product product = new Product(null);
    }

    @Test
    public void createNewProduct()  {

        Product product = new Product("Dove Soap", BigDecimal.valueOf(39.9911));
        assertEquals(product.getName(), "Dove Soap");
        assertEquals(product.getPrice(), BigDecimal.valueOf(39.99));

    }
}