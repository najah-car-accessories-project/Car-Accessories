Feature: Admin Dashboard Management

  Scenario Outline: Admin manages product categories
    Given the Admin is signed in
    And they navigate to the 'Product Categories' section
    Then they can view all product categories
    And they have options to <Action> a product category

    Examples: 
      | Action |
      | Add    |
      | Edit   |
      | Delete |

  Scenario Outline: Admin manages product listings
    Given the Admin is signed in
    And they navigate to the 'Product Listings' section
    Then they can view all products
    And they have options to <Action> a product

    Examples: 
      | Action |
      | Add    |
      | Edit 	 |
      | Delete |

  Scenario Outline: Admin manages customer accounts
    Given the Admin is signed in
    And they navigate to the 'Customer Accounts' section
    Then they can view all user accounts
    And they have options to <Action> a user account

    Examples: 
      | Action     |
      | Activate   |
      | Deactivate |
      | Delete     |
      | Role     	 |
      

  Scenario Outline: Admin manages installation appointments
    Given the Admin is signed in
    And they navigate to the 'Installation Appointments' section
    Then they can view all upcoming appointments
    And they have options to <Action> an appointment

    Examples: 
      | Action     |
      | Accepted   |
      | Reschedule |
      | Completed  |
