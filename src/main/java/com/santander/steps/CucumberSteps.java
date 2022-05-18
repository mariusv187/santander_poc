package com.santander.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.context.ScenarioContext;
import com.santander.context.TestContextConfig;
import com.santander.pages.AgentsPage;
import com.santander.pages.BasePage;
import com.santander.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import models.Instances;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestContextConfig.class)
public class CucumberSteps {

    private ObjectMapper mapper = new ObjectMapper();
    private final ScenarioContext scenarioContext;

    public CucumberSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("Login users for {string} configuration")
    public void loginUsers(String configuration) {

        Instances instances;
        try {
            File file = new File("src/test/resources/configuration_files/" + configuration);
            instances = mapper.readValue(file, Instances.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scenarioContext.setInContext("instances", instances);
        ForkJoinPool threadPool = new ForkJoinPool(4);
        ForkJoinTask<?> task = threadPool.submit(() -> instances.getInstances().stream().parallel().forEach(action -> {
            WebDriver webDriver = BasePage.getWebDriver();
            scenarioContext.setInContext(action.getName(), webDriver);
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.openPage(instances.getUrl());
            AgentsPage agentsPage = loginPage.login(action.getUsername(), action.getPassword());
            try {
                agentsPage.setUserToAvailable();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        task.join();
    }

    @After
    public void closeBrowsers() {
        scenarioContext.getFromContext("instances", Instances.class).getInstances().stream().parallel().forEach(actions -> {
            scenarioContext.getFromContext(actions.getName(), WebDriver.class).quit();
        });
    }

    @Given("pause test")
    public void pauseTest() {
        System.out.println("stop");
    }
}
