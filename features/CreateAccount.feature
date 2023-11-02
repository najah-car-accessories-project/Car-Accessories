Feature: Users Create Account
  Description: Users can create an account on the system
  Actor: All Users

  Scenario Outline: User creates an account with valid credentials
    Given the user is on the registration page
    When the user enters their personal information (<email>, <password>, <role> and <contactNumber>)
    Then if the details are valid, the user's account will be created successfully
    
    Examples:
      | email                      | password            | role        | contactNumber |
      | "majd@gmail.com"           | "majd0567"          | "Customer"  | "0599364789"  |
      | "ahmad@gmail.com"          | "ahmad2000"         | "Installer" | "0563149578"  |

  Scenario Outline: User tries to create an account with invalid credentials
    Given the user is on the registration page
    When the user enters their personal information (<email>, <password>, <role> and <contactNumber>)
    Then if the details are invalid, the user's account creation fails
    
    Examples:
      | email                      | password            | role        | contactNumber |
      | "majdgmail.com"            | "majd0567"          | "Customer"  | "0599364789"  |
      | "ahmad@gmail.com"          | "ahmad2000"         | "Installer" | "gdhnghgghh"  |
