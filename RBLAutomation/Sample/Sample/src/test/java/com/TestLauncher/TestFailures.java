package com.TestLauncher;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
	        features = {"@rerun/failed_scenarios.txt"}, 
	        monochrome = true, 
	        plugin = {
	        		"html:target/custom-reports/cucumber-htmlreports",
					"json:target/custom-reports/cucumber-reports/cucumber-jsonreports.json",
					"junit:target/custom-reports/cucumber-reports/cucumber-junitreports.xml",
					"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm" },
	        glue = {"com.stepDefinition"}
	        )
	public class TestFailures extends AbstractTestNGCucumberTests {
	
	}