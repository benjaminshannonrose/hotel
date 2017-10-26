package com.hotelapp.hotel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    By username = By.id("username");
    By password = By.id("password");
    By logInSubmit = By.id("logInSubmit");
    By logInError = By.id("logInError");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterValidUsername(){
        driver.findElement(username).sendKeys("realname");
    }

    public void enterValidPassword(){
        driver.findElement(password).sendKeys("123456789");
    }

    public void clickLoginButton(){
        driver.findElement(logInSubmit).click();
    }

    public void enterInvalidUsername(){
        driver.findElement(username).sendKeys("badloginname");
    }

    public void enterInvalidPassword(){
        driver.findElement(password).sendKeys("badloginpassword");
    }

    public void verifyRedirectToLoginpage(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(logInSubmit));
        driver.findElement(logInSubmit);
    }

    public void verifyLoginErrorMessage(){
        driver.findElement(logInError);
    }

    public void enterMapUsername(){
        driver.findElement(username).sendKeys("mapusername");
    }

    public void enterMakeBookingUsername(){
        driver.findElement(username).sendKeys("bookinguser");
    }

    public void enterDeleteBookingUsername(){
        driver.findElement(username).sendKeys("deleteuser");
    }

    public void enterIndexLogOutUsername(){
        driver.findElement(username).sendKeys("indexlogout");
    }

    public void enterBookingsLogOutUsername(){
        driver.findElement(username).sendKeys("bookingslogout");
    }
}
