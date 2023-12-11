Feature: Installation Requests

  Scenario: Customer submits an installation request
    Given a Customer is logged in
    When they select products and specify installation details like date, time, and car details
    Then an installation request is created and added to the system

  Scenario: Admin views all installation requests
    Given an Admin is logged in
    When they navigate to the 'Installation Requests' section
    Then they see a list of all installation requests

  Scenario: Admin edits or deletes an installation request
    Given an Admin is viewing installation requests
    When they choose to edit or delete a request
    Then the selected request is edited or deleted from the system

  Scenario: Installer views assigned installation requests
    Given an Installer is logged in
    When they update the status of a request
    Then the updated status is reflected in the system
