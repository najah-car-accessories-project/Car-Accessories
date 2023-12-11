Feature: Notifications

  Scenario: Customer receives order status updates
    Given a Customer has an pinding order
    When there is a status update on their order
    Then the Customer receives an update notification via email

  Scenario: Installer receives new installation request notification
    Given an installer is  in the system
    When a new installation request is created
    Then a notification is sent to the installer email informing about the new request
