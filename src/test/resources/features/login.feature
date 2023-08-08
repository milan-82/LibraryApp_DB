Feature: Login Functionality

  @wip
  Scenario: Login with valid credentials
    Given the user logged in "librarian10@library" and "libraryUser"
    When user gets username from user fields
    Then the username be same with database

