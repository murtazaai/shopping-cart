Feature: Shopping Cart Test

  Scenario: Add products to the shopping cart.
    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99
    When the user adds 5 Dove Soaps to the shopping cart
    And the shopping cart’s total price should equal 199.95

  Scenario: Add additional products of the same type to the shopping cart.
    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99
    When the user adds 5 Dove Soaps to the shopping cart
    Then the user adds 3 Dove Soaps to the shopping cart
    And the shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
    And the shopping cart’s total price should equal 319.92

  Scenario: Calculate the tax rate of the shopping cart with multiple items
    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99
    And a product, Axe Deo with a unit price of 99.99
    And a tax rate of 12.5 percent
    When the user adds 2 Dove Soaps to the shopping cart
    And the user adds 2 Axe Deos to the shopping cart
    Then the shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
    And the shopping cart should contain 2 Axe Deos each with a unit price of 99.99
    And the total tax amount should equal 35.00
    And the shopping cart’s total price with tax should equal 314.96

  Scenario: Add products to the shopping cart, which have “Buy X, Get Y Free” Offer.
    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99 and an associated buy 2 get 1 free offer
    And a product, Axe Deo with a unit price of 89.99 and no associated offer
    When the user adds 3 Dove Soaps to the shopping cart
    Then the shopping cart should contain 3 Dove Soaps each with a unit price of 39.99
    And the shopping cart’s total price should equal 79.98
    And the shopping cart’s discount should equal 39.99
    And the total tax amount should equal 10.00