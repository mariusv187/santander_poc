package com.santander.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgentsPage extends BasePage {

    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[4]/ul/li/div/div/button/span")
    private WebElement omniChannel;
    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/div/button/lightning-primitive-icon")
    private WebElement dropButton;
    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[2]/div[1]/div[3]/div/div/div[1]/div/div/ul/li[1]/a")
    private WebElement setToAvailable;

    public AgentsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setUserToAvailable() {
        omniChannel.click();
        dropButton.click();
        setToAvailable.click();
    }
}
