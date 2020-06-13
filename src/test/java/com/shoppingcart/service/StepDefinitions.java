package com.shoppingcart.service;

import com.shoppingcart.model.Product;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StepDefinitions {
    private static final double DELTA = 1e-15;
    private List<Product> listOfProducts = Collections.synchronizedList(new ArrayList<>());
    private Cart cart = null;

    @Given("^an empty shopping cart$")
    public void an_empty_shopping_cart() {
        listOfProducts.clear();
        cart = new Cart();

        assertTrue(true);
    }

    @And("^a product, (.*?) (.*?) with a unit price of (\\d+)\\.(\\d+)$")
    public void a_product_with_a_unit_price_of(String productName, String productCategory,
                                               int priceBeforeDecimal, int priceAfterDecimal) {
        BigDecimal expected = BigDecimal.valueOf(
                Double.parseDouble(priceBeforeDecimal + "." + priceAfterDecimal));
        Product product = new Product(productName + " " + productCategory, expected);

        listOfProducts.add(product);

        assertEquals(productName + " " + productCategory, product.getName());
        assertEquals(expected,
                product.getPrice());
    }

    @When("^the user adds (\\d+) (.*?) (.*?) to the shopping cart$")
    public void the_user_adds_to_the_shopping_cart(int count, String productName, String productCategory) {
        String productNameWithCategory = productName + " " + productCategory.substring(0, productName.length());

        List<Product> products = listOfProducts.stream()
                .filter(product -> product.getName().equals(productNameWithCategory))
                .collect(Collectors.toList());

        for (int i =0; i < count; i++) {
            cart.add(new Product(productNameWithCategory, products.get(0).getPrice()));
        }

        for (int i =(cart.getProducts().size() - count) ; i < count; i++) {
            assertEquals(productNameWithCategory, cart.getProducts().get(i).getName());
            assertEquals(BigDecimal.valueOf(39.99), cart.getProducts().get(i).getPrice());
        }
    }

    @And("^the shopping cart’s total price should equal (\\d+)\\.(\\d+)$")
    public void the_shopping_carts_total_price_should_equal(int priceBeforeDecimal, int priceAfterDecimal) {
        assertEquals(BigDecimal.valueOf(Double.parseDouble(priceBeforeDecimal + "." + priceAfterDecimal)),
                cart.getTotalPrice());
    }

    @Then("^the shopping cart should contain (\\d+) (.*?) (.*?) each with a unit price of (\\d+)\\.(\\d+)$")
    public void the_shopping_cart_should_contain_each_with_a_unit_price_of(int count, String productName, String productCategory, int priceBeforeDecimal, int priceAfterDecimal) {
        final String productNameFilter = productName + " " + productCategory.substring(0, productName.length());

        List<Product> products = cart.getProducts().stream().filter(product -> product.getName().equals(productNameFilter)).collect(Collectors.toList());

        assertEquals(count, products.size(), DELTA);

        products.forEach(product -> {
            assertEquals(BigDecimal.valueOf(Double.parseDouble(priceBeforeDecimal + "." + priceAfterDecimal)),
                    product.getPrice());
        });
    }

    @And("^a tax rate of (\\d+)\\.(\\d+) percent$")
    public void a_tax_rate_of_percent(int taxBeforeDecimal, int taxAfterDecimal) {
        cart.getProducts().forEach(product -> {
            product.setPrice(product.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(125)
                    .divide(BigDecimal.valueOf(1000)))));
        });
    }

    @And("^the total tax amount should equal (\\d+)\\.(\\d+)$")
    public void the_total_tax_amount_should_equal(int taxAmountBeforeDecimal, int taxAmountAfterDecimal) {
        assertEquals(Product.round(BigDecimal.valueOf(Double.parseDouble(taxAmountBeforeDecimal + "." + taxAmountAfterDecimal)), 2), cart.getTotalTaxAmount());
    }

    @And("^the shopping cart’s total price with tax should equal (\\d+)\\.(\\d+)$")
    public void the_shopping_carts_total_price_with_tax_should_equal(int priceBeforeDecimal, int priceAfterDecimal) {
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(Double.parseDouble(priceBeforeDecimal + "." + priceAfterDecimal));

        assertEquals(expectedTotalPrice, cart.getTotalPriceWithTax());
    }
}
