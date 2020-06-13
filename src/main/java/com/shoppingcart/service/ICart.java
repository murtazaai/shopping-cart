package com.shoppingcart.service;

import com.shoppingcart.exception.ProductNotFoundException;
import com.shoppingcart.model.Product;

import java.util.List;

public interface ICart {
    public List<Product> getProducts();

    public void empty();

    public void add(Product product) throws ProductNotFoundException;

}
