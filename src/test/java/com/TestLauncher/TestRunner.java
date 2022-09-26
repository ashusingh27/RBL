package com.TestLauncher;

import org.testng.annotations.Listeners;
import com.generics.CustomReporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@Listeners(CustomReporter.class)
@CucumberOptions(features = ("src\\test\\java\\com\\feature\\IMPS\\FundTransfer_IMPS_Feature.feature"),
                 glue = { "com.stepDefinition" }, 
//                 tags = {"@Execute" },
                 plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				            "html:target/custom-reports/cucumber-htmlreports",
				            "json:target/custom-reports/cucumber-reports/cucumber-jsonreports.json",
				            "junit:target/custom-reports/cucumber-reports/cucumber-junitreports.xml",
				            "rerun:rerun/failed_scenarios.txt" 
				}

//                , monochrome = true
//              , dryRun = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}