Feature: Users Sign Out
  Description: The Users signed out of the System
  Actor: All Users

  Background: 
    Given the users credentials
      | mohammadbadawi@gmail.com | mohammadbadawi2001 | Admin     |
      | majd@gmail.com           | majd0567           | Customer  |
      | ahmad@gmail.com          | ahmad2000          | Installer |

  Scenario Outline: the <role> signed out
    Given that the <role> is signed in
    When the <role> signs out
    Then the <role> is not signed in

    Examples: 
      | email                        | password            | role        |
      | "mohammadbadawi@gmail.com"	 | "mohmmadbadawi2001" | "Admin"     |
      | "majd@gmail.com"             | "majd056"           | "Customer"  |
      | "ahmad@gmail.com"            | "ahmad2000"         | "Installer" |
