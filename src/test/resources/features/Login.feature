Feature: user login

  Background:
    Given user is on the home page
    When user clicks on login link

  Scenario: user logs in with valid credentials
    And user enters valid username
    And user enters valid password
    And clicks on login button
    Then user should land on home page with personalized greeting

  Scenario: user logs in and then logs out from index
    And user enters index log out username
    And user enters valid password
    And clicks on login button
    And clicks on index log out link
    Then they should land on the log in page

  Scenario: user tries to login with invalid credentials
    And user enters invalid username
    And user enters invalid password
    And clicks on login button
    Then user should receive login error message