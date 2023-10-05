Feature: Users Create Account
  Description: Users can create an account on the system
  Actor: All Users

  Background: 
    Given the users credentials
      | mohammadbadawi@gmail.com | mohammadbadawi2001 | Admin     |
      | majd@gmail.com           | majd056            | Customer  |
      | ahmad@gmail.com          | ahmad2000          | Installer |

  Scenario: User creates an account with valid credentials
    Given the user is on the registration page
    When the user provides the following valid details
      | email                      | password            | role        |
      | "mohammadbadawi@gmail.com" | "mohmmadbadawi2001" | "Admin"     |
      | "majd@gmail.com"           | "majd056"           | "Customer"  |
      | "ahmad@gmail.com"          | "ahmad2000"         | "Installer" |
    Then the user's account is created successfully

  Scenario: User tries to create an account with invalid credentials
    Given the user is on the registration page
    When the user provides the following invalid details
      | email                      | password            | role        |
      | "mohammadbadawi@gmail.com" | "mohmmadbadawi2001" | "0"         |
      | "majd"                     | "majd056"           | "Customer"  |
      | "ahmad"                    | "ahmad2000"         | "Installer" |
    Then the user's account creation fails
