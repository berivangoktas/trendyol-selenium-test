package com.trendyol;

import com.trendyol.extensions.ReportExtension;
import com.trendyol.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Random;
@ExtendWith(ReportExtension.class)
public class LoginTest extends AbstractTrendyol
{
    private static final String ERROR_MESSAGE_FOR_MISSING_INPUT = "Lütfen geçerli bir e-posta adresi giriniz.";
    private static final String ERROR_MESSAGE_FOR_MISSING_PASSWORD = "Lütfen şifrenizi giriniz.";
    private static final String ERROR_MESSAGE_FOR_WRONG_PASSWORD = "E-posta adresiniz ve/veya şifreniz hatalı.";

    private LoginPage loginPage;
    @BeforeEach
    public void before()
    {
        loginPage = new LoginPage(driver);

        goToUrl(UrlFactory.LOGIN_PAGE_URL.pageUrl);
    }
    
    @Test
    public void testEmptyEmailAndPasswordWithLogin() throws InterruptedException {
        safeClick(loginPage.submitButton);
        Assertions.assertTrue(loginPage.errorMessageForLogin.getText().equals(ERROR_MESSAGE_FOR_MISSING_INPUT));
    }

    @Test
    public void testMissingPassword() throws InterruptedException {
        sendKeys(loginPage.emailInput,randomStringGenerator()+ "@grr.la");
        safeClick(loginPage.submitButton);
        Assertions.assertTrue(loginPage.errorMessageForLogin.getText().equals(ERROR_MESSAGE_FOR_MISSING_PASSWORD));
    }

    @Test
    public void testMissingEmail() throws InterruptedException {
        sendKeys(loginPage.passwordInput,randomStringGenerator());
        safeClick(loginPage.submitButton);
        Assertions.assertTrue(loginPage.errorMessageForLogin.getText().equals(ERROR_MESSAGE_FOR_MISSING_INPUT));
    }
    @Test
    public void testWrongPasswordWithLogin() throws InterruptedException {
        fillLoginAttribute("goktasberivan93@gmail.com",randomStringGenerator());
        Assertions.assertTrue(loginPage.errorMessageForLogin.getText().equals(ERROR_MESSAGE_FOR_WRONG_PASSWORD));
    }
    @Test
    public void testWrongEmailAndPasswordWithLogin() throws InterruptedException {
        fillLoginAttribute(randomStringGenerator()+ "@grr.la",randomStringGenerator());
        Assertions.assertTrue(loginPage.errorMessageForLogin.getText().equals(ERROR_MESSAGE_FOR_WRONG_PASSWORD));
    }
    @Test
    public void testCorrectUserLogin() throws InterruptedException {
        fillLoginAttribute("goktasberivan93@gmail.com","123qweasd");
        Assertions.assertTrue(getCurrentUrl().equals(UrlFactory.BOUTIQUE_LIST_PAGE.pageUrl));
    }

    @Test
    public void testForgotPasswordLinkControl() {
        safeClick(loginPage.forgotPasswordLink);
        Assertions.assertTrue(getCurrentUrl().equals(UrlFactory.FORGOT_PASSWORD.pageUrl));
    }

    @Test
    public void testEyeIconControl() {
        sendKeys(loginPage.passwordInput,randomStringGenerator());
        Assertions.assertTrue(isDisplayed(loginPage.eyeCloseIcon));
        safeClick(loginPage.eyeCloseIcon);
        Assertions.assertTrue(isDisplayed(loginPage.eyeOpenIcon));

    }

    public  void fillLoginAttribute(String emailValue, String passwordValue) throws InterruptedException {
        sendKeys(loginPage.emailInput,emailValue);
        sendKeys(loginPage.passwordInput,passwordValue);
        safeClick(loginPage.acceptButton);
        safeClick(loginPage.submitButton);
    }

    public String randomStringGenerator() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
