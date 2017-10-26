package com.hotelapp.hotel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    WebDriver driver;
    By username = By.id("username");
    By password = By.id("password");
    By passwordConfirm = By.id("passwordConfirm");
    By signUpSubmit = By.id("signUpSubmit");
    By usernameError = By.id("usernameError");
    By passwordError = By.id("passwordError");
    By confirmPasswordError = By.id("confirmPasswordError");

    public SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterValidUsername(){
        driver.findElement(username).sendKeys("fakeuser");
    }

    public void enterValidPassword(){
        driver.findElement(password).sendKeys("123456789");
    }

    public void enterMatchingConfirmationPassword(){
        driver.findElement(passwordConfirm).sendKeys("123456789");
    }

    public void clickSignUpButton(){
        driver.findElement(signUpSubmit).click();
    }

    public void enterUsernameTooShort(){
        driver.findElement(username).sendKeys("short");
    }

    public void enterPasswordTooShort(){
        driver.findElement(password).sendKeys("short");
    }

    public void enterConfirmationPasswordDoesNotMatch(){
        driver.findElement(passwordConfirm).sendKeys("nonmatchingpassword");
    }

    public void verifyErrorMessageUsername(){
        driver.findElement(usernameError);
    }

    public void verifyErrorMessagePassword(){
        driver.findElement(passwordError);
    }

    public void verifyErrorMessageConfirmPassword(){
        driver.findElement(confirmPasswordError);
    }

    public void enterTakenUsername(){
        driver.findElement(username).sendKeys("realname");
    }
}
