package com.santander;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.santander.steps","com.santander.context"},
        tags = "",
        plugin = {"pretty", "html:build/reports/feature.html"})
public class RunCucumber {

}
