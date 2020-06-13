# Shopping Cart

## Overview
Implementation of a shopping cart in Java.

## The problem
Code to allow a user to add products to a shopping cart, and then calculate the total
price and tax amounts for the items contained in the cart. 

## Note 
All totals are formatted to 2 decimal places, rounded up i.e. 0.567
should result in 0.57 but 0.564 result in 0.56

### Scenario 1: Add products to the shopping cart.
- Given an empty shopping cart
- And a product, Dove Soap with a unit price of 39.99
- When the user adds 5 Dove Soaps to the shopping cart
- Then the shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
- And the shopping cart’s total price should equal 199.95

### Scenario 2: Add additional products of the same type to the shopping cart.
- Given an empty shopping cart
- And a product, Dove Soap with a unit price of 39.99
- When the user adds 5 Dove Soaps to the shopping cart
- And then adds another 3 Dove Soaps t o the shopping cart
- Then the shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
- And the shopping cart’s total price should equal 319.92

### Scenario 3: Calculate the tax rate of the shopping cart with multiple items
- Given an empty shopping cart
- And a product, Dove Soap with a unit price of 39.99
- And another product, Axe Deo w ith a unit price of 99.99
- And a tax rate of 12.5%
- When the user adds 2 Dove Soaps to the shopping cart
- And then adds 2 Axe Deo’s t o the shopping cart
- Then the shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
- And the shopping cart should contain 2 Axe Deo’s each with a unit price of 99.99
- And the total tax amount should equal 35.00
- And the shopping cart’s total price should equal 314.96

## Prerequisites
- Java 8
- maven 3

## Build
`mvn clean install`

## Test
`mvn clean test`

## BDD Test (Cucumber)
`mvn clean verify -Dit.test=ICartRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"`