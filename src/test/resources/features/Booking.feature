Feature: booking hotels

  Background:
    Given user is on the home page


  Scenario: user enters zip code to search for hotels
    When user enters a zip code in the search bar
    And user clicks search button
    Then they should see list of hotels

  Scenario: user enters city and state to search for hotels
    When user enters a city and state in the search bar
    And user clicks search button
    Then they should see list of hotels

  Scenario: user clicks book hotel button while not signed in
    When logged out user enters a zip code in the search bar
    And user clicks search button
    And user clicks book hotel button
    Then they should land on the log in page

  Scenario: user logs in and then logs out from bookings page
    When user clicks on login link
    And user enters bookings log out username
    And user enters valid password
    And clicks on login button
    And user clicks view bookings link
    And clicks on bookings log out link
    Then they should land on the log in page

  Scenario: user clicks book hotel button while signed in and checks their bookings
    When user clicks on login link
    And user enters make booking username
    And user enters valid password
    And clicks on login button
    And user enters a zip code in the search bar
    And user clicks search button
    And user clicks book hotel button
    And user clicks book hotel button
    And user clicks view bookings link
    Then they should see both bookings

  Scenario: user views the google map associated a booking
    When user clicks on login link
    And user enters map username
    And user enters valid password
    And clicks on login button
    And user clicks view bookings link
    And user clicks view button for a booking
    Then they should see a google map of the hotel with details
    And they should be able to close the map

  Scenario: user deletes a booking
    When user clicks on login link
    And user enters delete booking username
    And user enters valid password
    And clicks on login button
    And user clicks view bookings link
    Then they should no longer see a booking after they click the button to delete it
