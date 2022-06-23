package com.trendyol;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public abstract class SeleniumDriver {
    public WebDriver driver;
    private ChromeOptions chromeOptions;

    public WebDriver createChromeDriver() throws MalformedURLException {

        String plafform = System.getProperty("platform.type");
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        chromeOptions = chromeOptions();

        if (plafform.equals("local")) {

            log.info("This driver create ...");
            driver = new ChromeDriver();
        }
        else
        {
             driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

        }
        driver.navigate().to(UrlFactory.BASE_URL.pageUrl);
        driver.navigate().refresh();
        return driver;
    }

    public WebDriver createFireFoxOptions() throws MalformedURLException {

        String plafform = System.getProperty("platform.type");
        System.setProperty("webdriver.gecko.driver", "driverGecko/geckodriver");
        FirefoxOptions browserOptions = new FirefoxOptions();

        if (plafform.equals("local")) {

            driver = new FirefoxDriver(browserOptions);
        } else {
             driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        }


        driver.navigate().to(UrlFactory.BASE_URL.pageUrl);
        driver.navigate().refresh();
        return driver;
    }


    private ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        return chromeOptions;
    }

    public void returnBack() {
        driver.navigate().back();
    }


}
