Feature: user account creation

  Background:
    Given user is on the home page
    When user clicks on sign up link

  Scenario: intentional fail to test screenshot capture
    Then scenario should fail

  Scenario: user signs up with valid credentials
    And user enters new valid username
    And user enters new valid password
    And user enters matching confirmation password
    And user clicks sign up button
    Then they should land on the log in page

  Scenario: user signs up with invalid credentials
    And user enters username that is too short
    And user enters password that is too short
    And user enters confirmation password that does not match
    And user clicks sign up button
    Then they should get an error message for the username field
    And they should get an error message for the password field
    And they should get an error message for the confirm password field

  Scenario: user signs up with a taken username
    And user enters taken username
    And user enters new valid password
    And user enters matching confirmation password
    And user clicks sign up button
    Then they should get an error message for the username field


