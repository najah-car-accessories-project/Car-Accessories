Feature: Users Sign in
  Description: The Users signs into System
  Actor: All Users

  Background: 
    Given the users credentials
      | mohammadbadawi@gmail.com | mohammadbadawi2001 | Admin     |
      | majd@gmail.com           | majd056            | Customer  |
      | ahmad@gmail.com          | ahmad2000          | Installer |

  Scenario Outline: the <role> enter valid credentials
    Given That the <role> is not signed in
    And the <role> email is <email>
    And the <role> password is <password>
    Then the <role> sign in succeeds
    And the <role> is signed in
    Then go to <role> page

    Examples: 
      | email                      | password            | role        |
      | "mohammadbadawi@gmail.com" | "mohmmadbadawi2001" | "Admin"     |
      | "majd@gmail.com"           | "majd056"           | "Customer"  |
      | "ahmad@gmail.com"          | "ahmad2000"         | "Installer" |

  Scenario Outline: the <role> enter invalid credentials
    Given That the <role> is not signed in
    And the <role> email is <email>
    And the <role> password is <password>
    Then the <role> sign in not succeeds
    And the <role> is not signed in

    Examples: 
      | email                      | password            | role        |
      | "mohammadbadawi@gmail.com" | "mohmmadbadawi2002" | "Admin"     |
      | "majd@gmail.com"           | "majd059"           | "Customer"  |
      | "ahmad@gmail.com"          | "ahmad2002"         | "Installer" |
