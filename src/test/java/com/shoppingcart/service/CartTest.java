package com.shoppingcart.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.shoppingcart.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartTest {

    private static final double DELTA = 1e-15;
    private Cart cart = new Cart();

    @Test
    public void productSetterInjection() {
        List<Product> listOfProducts = Collections.synchronizedList(new ArrayList<>());
        cart.setProducts(listOfProducts);
        assertEquals(listOfProducts, cart.getProducts());
    }

    @Test
    public void addAProductToCart()  {
        this.cart.empty();
        assertEquals(0, this.cart.getProducts().size(), DELTA);

        Product product = new Product("Dove Soap", BigDecimal.valueOf(39.99123));
        this.cart.add(product);

        assertEquals(cart.getProducts().get(0).getName(), "Dove Soap");
        assertEquals(cart.getProducts().get(0).getPrice(), BigDecimal.valueOf(39.99));

    }

    @Test
    public void addMultipleProductsToCart() {
        for (int i =0; i < 5; i++) {
            cart.add(new Product("Dove" + " " + "Soap", BigDecimal.valueOf(39.99)));
        }

        for (int i =0; i < 5; i++) {
            assertEquals(cart.getProducts().get(0).getName(), "Dove Soap");
            assertEquals(cart.getProducts().get(0).getPrice(), BigDecimal.valueOf(39.99));
        }

        assertEquals(BigDecimal.valueOf(199.95), cart.getTotalPrice());
        assertEquals(Product.round(BigDecimal.valueOf(25), 2), cart.getTotalTaxAmount());

        assertEquals(BigDecimal.valueOf(224.95), cart.getTotalPriceWithTax());
    }
}
