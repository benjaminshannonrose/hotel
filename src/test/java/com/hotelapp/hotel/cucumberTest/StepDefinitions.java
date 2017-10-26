package com.hotelapp.hotel.cucumberTest;

import com.hotelapp.hotel.pages.BookingsPage;
import com.hotelapp.hotel.pages.IndexPage;
import com.hotelapp.hotel.pages.LoginPage;
import com.hotelapp.hotel.pages.SignUpPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StepDefinitions extends WebLayerTest {

    private WebDriver driver;
    private IndexPage indexPage;
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private BookingsPage bookingsPage;
    private FailScreenshot screenshot;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "\\\\OHFS07\\Home\\brose\\Desktop\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/index");
        indexPage = new IndexPage(driver);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        bookingsPage = new BookingsPage(driver);
    }

    @After
    public void tearDown(Scenario scenario){
        screenshot = new FailScreenshot(driver);
        try {
            screenshot.embedScreenshot(scenario);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Given("^user is on the home page$")
    public void user_is_on_the_home_page() throws Throwable {
        indexPage.verifyLandedOnHomePage();
    }

    @When("^user clicks on sign up link$")
    public void user_clicks_on_sign_up() throws Throwable {
        indexPage.clickSignUp();
    }

    @When("^user clicks on login link$")
    public void user_clicks_on_login() throws Throwable {
        indexPage.clickLogIn();
    }

    @When("^logged out user enters a zip code in the search bar$")
    public void user_enters_a_zip_code_in_the_search_bar() throws Throwable {
        indexPage.enterZip();
    }

    @When("^user enters a city and state in the search bar$")
    public void user_enters_a_city_and_state_in_the_search_bar() throws Throwable {
        indexPage.enterCityState();
    }

    @Then("^they should land on the log in page$")
    public void they_should_land_on_the_log_in_page() throws Throwable {
        loginPage.verifyRedirectToLoginpage();
    }

    @Then("^they should get an error message for the username field$")
    public void they_should_get_an_error_message_for_the_username_field() throws Throwable {
        signUpPage.verifyErrorMessageUsername();
    }

    @Then("^user should land on home page with personalized greeting$")
    public void user_should_land_on_home_page_with_personalized_greeting() throws Throwable {
        indexPage.checkSignedIn();
    }

    @Then("^user should receive login error message$")
    public void user_should_receive_login_error_message() throws Throwable {
        loginPage.verifyLoginErrorMessage();
    }

    @Then("^they should see list of hotels$")
    public void they_should_see_list_of_hotels() throws Throwable {
        indexPage.verifyHotelListPopulated();
    }

    @Then("^they should see both bookings$")
    public void they_should_see_both_bookings() throws Throwable {
        bookingsPage.verifyBookingListPopulated();
    }

    @Then("^they should see a google map of the hotel with details$")
    public void they_should_see_a_google_map_of_the_hotel_with_details() throws Throwable {
        bookingsPage.verifyGoogleMapContent();
    }

    @Then("^they should no longer see a booking after they click the button to delete it$")
    public void they_should_no_longer_see_a_booking_after_they_click_the_button_to_delete_it() throws Throwable {
        bookingsPage.clickDeleteButtonAndVerifyDelete();
    }

    @Then("^scenario should fail$")
    public void scenario_should_fail() throws Throwable {
        indexPage.enterZip();
    }

    @And("^user enters new valid username$")
    public void user_enters_new_valid_username() throws Throwable {
        signUpPage.enterValidUsername();
    }

    @And("^user enters new valid password$")
    public void user_enters_new_valid_password() throws Throwable {
        signUpPage.enterValidPassword();
    }

    @And("^user enters matching confirmation password$")
    public void user_enters_matching_confirmation_password() throws Throwable {
        signUpPage.enterMatchingConfirmationPassword();
    }

    @And("^user clicks sign up button$")
    public void user_clicks_sign_up_button() throws Throwable {
        signUpPage.clickSignUpButton();
    }

    @And("^user enters username that is too short$")
    public void user_enters_username_that_is_too_short() throws Throwable {
        signUpPage.enterUsernameTooShort();
    }

    @And("^user enters password that is too short$")
    public void user_enters_password_that_is_too_short() throws Throwable {
        signUpPage.enterPasswordTooShort();
    }

    @And("^user enters confirmation password that does not match$")
    public void user_enters_confirmation_password_that_does_not_match() throws Throwable {
        signUpPage.enterConfirmationPasswordDoesNotMatch();
    }

    @And("^they should get an error message for the password field$")
    public void they_should_get_an_error_message_for_the_password_field() throws Throwable {
        signUpPage.verifyErrorMessagePassword();
    }

    @And("^they should get an error message for the confirm password field$")
    public void they_should_get_an_error_message_for_the_confirm_password_field() throws Throwable {
        signUpPage.verifyErrorMessageConfirmPassword();
    }

    @And("^user enters taken username$")
    public void user_enters_a_taken_username() throws Throwable {
        signUpPage.enterTakenUsername();
    }

    @And("^user enters valid username$")
    public void user_enters_valid_username() throws Throwable {
        loginPage.enterValidUsername();
    }

    @And("^user enters valid password$")
    public void user_enters_valid_password() throws Throwable {
        loginPage.enterValidPassword();
    }

    @And("^user enters index log out username$")
    public void user_enters_index_log_out_username() throws Throwable {
        loginPage.enterIndexLogOutUsername();
    }

    @And("^clicks on login button$")
    public void clicks_on_login_button() throws Throwable {
        loginPage.clickLoginButton();
    }

    @And("^clicks on index log out link$")
    public void clicks_on_log_out_button() throws Throwable {
        indexPage.clickLogOut();
    }

    @And("^user enters invalid username$")
    public void user_enters_invalid_username() throws Throwable {
        loginPage.enterInvalidUsername();
    }

    @And("^user enters invalid password$")
    public void user_enters_invalid_password() throws Throwable {
        loginPage.enterInvalidPassword();
    }

    @And("^user clicks search button$")
    public void user_clicks_search_button() throws Throwable {
        indexPage.clickSearchButton();
    }

    @And("^user clicks book hotel button$")
    public void user_clicks_book_hotel_button() throws Throwable {
        indexPage.clickBookButton();
    }

    @And("^user enters bookings log out username$")
    public void user_enters_bookings_log_out_username() throws Throwable {
        loginPage.enterBookingsLogOutUsername();
    }

    @And("^user clicks view bookings link$")
    public void user_clicks_view_bookings_link() throws Throwable {
        indexPage.clickViewBookings();
    }

    @And("^clicks on bookings log out link$")
    public void clicks_on_bookings_log_out_link() throws Throwable {
        bookingsPage.clickLogOut();
    }

    @And("^user enters make booking username$")
    public void user_enters_make_booking_username() throws Throwable {
        loginPage.enterMakeBookingUsername();

    }
    @And("^user enters a zip code in the search bar$")
    public void and_user_enters_a_zip_code_in_the_search_bar () throws Throwable {
        indexPage.enterZip();
    }

    @And("^user enters map username$")
    public void user_enters_map_username () throws Throwable {
        loginPage.enterMapUsername();
    }

    @And("^user clicks view button for a booking$")
    public void user_clicks_view_button_for_a_booking () throws Throwable {
        bookingsPage.clickViewButton();
    }

    @And("^they should be able to close the map$")
    public void they_should_be_able_to_close_the_map () throws Throwable {
        bookingsPage.closeGoogleMap();
    }

    @And("^user enters delete booking username$")
    public void user_enters_delete_booking_username () throws Throwable {
        loginPage.enterDeleteBookingUsername();
    }

}