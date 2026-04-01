package com.training.runners; 
 
import io.cucumber.testng.AbstractTestNGCucumberTests; 
import io.cucumber.testng.CucumberOptions; 
 
@CucumberOptions( 
        features = "src/test/resources/features", 
        glue = { "com.training.stepdefinitions", "com.training.hooks" }, 
        plugin = { 
                "pretty", 
                "html:target/cucumber-reports/cucumber.html", 
                "json:target/cucumber-reports/cucumber.json" 
        }, 
        monochrome = true 
) 
public class RunCucumberTest extends AbstractTestNGCucumberTests { 
} 