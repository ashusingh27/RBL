package com.TestLauncher;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.generics.CustomReporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@Listeners(CustomReporter.class)
@CucumberOptions(features = ("src\\test\\java\\com\\features\\rbl"),
					 glue = {"com.stepDefinition.API.RBL"},
					tags = {"@Execute"},
					 plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
								"html:target/custom-reports/cucumber-htmlreports",
						      "json:target/custom-reports/cucumber-reports/cucumber-jsonreports.json",
						      "junit:target/custom-reports/cucumber-reports/cucumber-junitreports.xml",
						      "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm" ,
							  "rerun:rerun/failed_scenarios.txt"
						      }
			// ,monochrome = true,
				//, dryRun = true
)
}
