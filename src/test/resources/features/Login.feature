Feature: Login functionality

  Scenario: Successful login
    Given I am on the login page
    When I enter valid credentials
    Then I should be logged in

  Scenario: Failed Login
    Given I am on the login page
    When I enter invalid credentials
    Then I should not be able to login



