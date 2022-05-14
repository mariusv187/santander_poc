package com.santander.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public static WebDriver getWebDriver() {
        WebDriver webDriver;
        webDriver = getChromeDriver();
        return webDriver;
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void waitForPageLoad() {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(driver -> String.valueOf(
                        ((JavascriptExecutor) driver).executeScript("return document.readyState"))
                .equals("complete"));
    }
}
