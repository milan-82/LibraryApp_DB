Feature: Login Functionality

  @db
  Scenario: Login with valid credentials
    Given the user logged in "librarian10@library" and "libraryUser"
    When user gets username from user fields
    Then the username be same with database


  @wip @db
  Scenario Outline: Login with valid credentials <email>
    Given the user logged in "<email>" and "<password>"
    When user gets username from user fields
    Then the username be same with database
    Examples:
      | email               | password    |
      | librarian9@library | libraryUser |
      | librarian5@library | libraryUser |
      | librarian8@library | libraryUser |

