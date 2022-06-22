package com.trendyol;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public abstract class SeleniumDriver {
    public WebDriver driver;
    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;

    public WebDriver createChromeDriver()
    {
        chromeOptions = chromeOptions();
        desiredCapabilities = desiredCapabilities(chromeOptions);
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        log.info("This driver create ...");

        driver = new ChromeDriver(desiredCapabilities);

        //driver.manage().window().setSize(new Dimension(width, height));
        driver.navigate().to(UrlFactory.BASE_URL.pageUrl);
        driver.navigate().refresh();
        return driver;
    }

    public WebDriver createFireFoxOptions()
    {
        System.setProperty("webdriver.gecko.driver", "driverGecko/geckodriver");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);
        driver=  new FirefoxDriver(capabilities);
        driver.navigate().to(UrlFactory.BASE_URL.pageUrl);
        driver.navigate().refresh();

        return driver;
    }

    private DesiredCapabilities desiredCapabilities( ChromeOptions chromeOptions)
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }

    private ChromeOptions chromeOptions()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        return chromeOptions;
    }
    public void returnBack(){
        driver.navigate().back();
    }


}
