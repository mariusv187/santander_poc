package com.santander.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class AgentsPage extends BasePage {

    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[4]/ul/li/div/div/button")
    private WebElement omniChannel;
    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/div/button/lightning-primitive-icon")
    private WebElement dropButton;
    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/div/div/ul/li[1]/a")
    private WebElement setToAvailable;
    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/span")
    private WebElement status;

    public AgentsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setUserToAvailable() throws InterruptedException {
        omniChannel.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/div/button/lightning-primitive-icon")));
        dropButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/div/div/ul/li[1]/a")));
        Thread.sleep(1000);
        setToAvailable.click();
        await().pollDelay(Duration.ofSeconds(1)).pollInterval(Duration.ofSeconds(2)).atMost(Duration.ofSeconds(10)).until(() ->
                "Available".equalsIgnoreCase(status.getText()));

    }
}
