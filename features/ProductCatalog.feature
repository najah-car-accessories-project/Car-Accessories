Feature: Product Catalog

  Scenario: Admin adds a new product
    Given the Admin is logged in
    When enter product details including name, description, price, and availability
    Then the product is added to the specified category in the catalog

  Scenario: Admin edits or deletes a product
    Given the Admin is logged in
    When enter product index
    Then the product is either updated with new details or removed from the catalog

  Scenario: Admin views all products
    Given the Admin is logged in
    When they select to view all products
    Then they see a list of all products with their details

  Scenario: Admin adds a new product category
    Given the Admin is logged in
    When they add a new category with name
    Then the new category is added to the product catalog

  Scenario: Admin edits or deletes a product category
    Given the Admin is logged in
    And a product category exists
    When they choose to edit or delete the category
    Then the category is either updated with new details or removed from the catalog

  Scenario: Admin views all product categories
    Given the Admin is logged in
    When they select to view all product categories
    Then they see a list of all categories

  Scenario: Search and filter products
    Given a list of products in the catalog
    When a User searches by search key
    Then they see a list of products matching the search key
