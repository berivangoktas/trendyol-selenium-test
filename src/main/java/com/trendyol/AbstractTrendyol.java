package com.trendyol;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Slf4j
public abstract class AbstractTrendyol extends SeleniumDriver {
    @BeforeEach
    public void init() throws MalformedURLException {

        String browserType = System.getProperty("browser.type");

        if (browserType.equals("CHROME")) {
            driver = createChromeDriver();

        } else if(browserType.equals("FIREFOX")) {
            driver = createFireFoxOptions();
        }
        else{
            driver = createChromeDriver();}
    }

    @AfterEach
   public void after() throws IOException {
        SessionId sessionid = ((RemoteWebDriver) driver).getSessionId();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.move(Paths.get(scrFile.getPath()), Paths.get("./temp/" +sessionid+".png"),StandardCopyOption.REPLACE_EXISTING);
       // driver.close();
         driver.quit();
   }

   public String getCurrentUrl() {
       return driver.getCurrentUrl();
   }

   public void safeClick(WebElement element) {
       try {
           click(element);
       } catch (Exception e) {
           log.warn("Could not click");
       }
   }

   public void click(WebElement element) throws InterruptedException {
       element.click();
       Thread.sleep(400);
   }
    public void sendKeys(WebElement webElement, CharSequence... var1)
   {
       webElement.clear();
       webElement.sendKeys(var1);
   }

    public SessionId returnSessionId(){
       return  ((RemoteWebDriver) driver).getSessionId();
    }

    public void goToUrl(String url){
        driver.get(url);
    }

    public boolean isDisplayed(WebElement element)
    {
        try
        {
            return element.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

}