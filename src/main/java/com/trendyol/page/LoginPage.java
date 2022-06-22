package com.trendyol.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends PageObject{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#login-email")
    public WebElement emailInput;

    @FindBy(css = "#login-password-input")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@class='q-primary q-fluid q-button-medium q-button submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//div[@id='error-box-wrapper']/span[@class='message']")
    public WebElement errorMessageForLogin;

    @FindBy(css = "#onetrust-accept-btn-handler")
    public WebElement acceptButton;

    @FindBy(css = ".forgot-password")
    public WebElement forgotPasswordLink;

    @FindBy(css = ".i-eye-close")
    public WebElement eyeCloseIcon;

    @FindBy(css = ".i-eye-open")
    public WebElement eyeOpenIcon;

}
