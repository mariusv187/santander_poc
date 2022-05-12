package com.santander.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.context.TestContextConfig;
import com.santander.pages.AgentsPage;
import com.santander.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import models.Instances;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestContextConfig.class)
public class CucumberSteps {

    private final LoginPage loginPage;
    private final AgentsPage agentsPage;
    private ObjectMapper mapper = new ObjectMapper();

    public CucumberSteps(LoginPage loginPage, AgentsPage agentsPage) {
        this.loginPage = loginPage;
        this.agentsPage = agentsPage;
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

        ForkJoinPool threadPool = new ForkJoinPool(instances.getInstances().size());
        ForkJoinTask<?> task = threadPool.submit(() -> instances.getInstances().stream().parallel().forEach(action -> {
            loginPage.openPage(instances.getUrl());
            loginPage.login(action.getUsername(), action.getPassword());
            agentsPage.setUserToAvailable();
        }));
        task.join();
    }

}
