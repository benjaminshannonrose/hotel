package com.hotelapp.hotel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {

    WebDriver driver;
    By signUpButton = By.id("signUp");
    By welcome = By.id("welcome");
    By logOut = By.id("logOut");
    By logIn = By.id("logIn");
    By hotelSearchBar = By.id("hotelSearchBar");
    By findHotel = By.id("findHotel");
    By book = By.className("book");
    By viewBookings = By.id("viewBookings");
    By hopperIcon = By.id("hopperIcon");
    By hotelName = By.className("hotelName");

    public IndexPage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyLandedOnHomePage(){
        driver.findElement(hopperIcon);
    }

    public void clickSignUp(){
        driver.findElement(signUpButton).click();
    }

    public String checkSignedIn(){
        String welcomeText = driver.findElement(welcome).getText();
        return welcomeText;
    }

    public void clickLogOut(){
        driver.findElement(logOut).click();
    }

    public void clickLogIn(){
        driver.findElement(logIn).click();
    }

    public void enterZip(){
        driver.findElement(hotelSearchBar).sendKeys("43016");
    }

    public void enterCityState(){
        driver.findElement(hotelSearchBar).sendKeys("Atlanta GA");
    }

    public void clickSearchButton(){
        driver.findElement(findHotel).click();
    }

    public void clickBookButton(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElements(book).get(1).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickViewBookings(){
        driver.findElement(viewBookings).click();
    }

    public void verifyHotelListPopulated(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.numberOfElementsToBe(hotelName, 10));
        driver.findElements(hotelName);
    }
}
