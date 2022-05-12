package com.santander.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ComponentScan(basePackages = {"com.santander"})
public class TestContextConfig {

    @Bean(destroyMethod = "quit")
    public WebDriver getWebDriver() {
        WebDriver webDriver;
        webDriver = getChromeDriver();
        return webDriver;
    }

    private WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
