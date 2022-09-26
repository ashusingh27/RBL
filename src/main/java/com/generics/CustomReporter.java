package com.generics;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @ScriptName : CustomeReporter
 * @Description : Excel Based report creation for regression execution
 * @Author : Nikhil Agarwal.
 */

public class CustomReporter implements ITestListener {
	public void onTestStart(ITestResult result) {
	}
	public void onTestSuccess(ITestResult result) {
	}
	public void onTestFailure(ITestResult result) {	
		boolean isProductionReady = true;
		// Check if the annotation attribute value is productionReady=true
		if (isProductionReady) {
			System.out.println("IS PRODUCTION READY : " + isProductionReady);
			JiraServiceProvider jiraSP = new JiraServiceProvider("https://vythaku.atlassian.net",
					"vikash.thakur@aqmtechnologies.com", "U9M1JNi2TP82DxaQLEGdFE68", "CCC");

			// Add the failed method name as the issue summary
			String issueSummary = result.getThrowable().getMessage()
					+ " was failed due to an exception and Assertion";
			issueSummary.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			// get the error message from the exception to description
			String issueDescription = "Exception details : " + result.getThrowable().getMessage() + "\n";
			// Append the full stack trace to the description.
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			jiraSP.createJiraIssue("Bug", issueSummary, issueDescription, "vikash chandra thakur", "High");
		}
	}
	public void onTestSkipped(ITestResult result) {
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	public void onStart(ITestContext context) {
	}
	public void onFinish(ITestContext context) {
	}
}