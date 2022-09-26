package com.generics;

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
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		//new CustomReporterHelper(null, null).startExcelReport();
	}
}