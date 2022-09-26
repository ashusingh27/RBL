package com.rbl.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

import com.PageFactory.LoginPage;
import com.generics.BaseTest;
import com.generics.Pojo;

public class CommonCode {

	private Pojo objPojo;
	Properties prop;
	BaseTest pojo;
	private String testData;
	private LoginPage objLoginPage;

	public CommonCode() {
	}

	public CommonCode(Pojo objPojo) {
		this.objPojo = objPojo;
		objLoginPage = new LoginPage(objPojo);
		this.pojo = pojo;
		prop = new Properties();
	}

	public void compareToMap(String responseStatus, String applicationStatus) {
		System.out.println("Common responseStatus : "+responseStatus+" Commom applicationStatus : "+applicationStatus);
		// Assert.assertEquals(applicationStatus, responseStatus.trim());
		if(responseStatus!=null && applicationStatus!=null) {
		if (responseStatus.trim().equalsIgnoreCase(applicationStatus.trim()))
			objPojo.getObjUtilities().logReporter(
					"Response status : " + responseStatus + " || Application status : " + applicationStatus + " match",
					true);
		else if (responseStatus.trim().equalsIgnoreCase("Initiated") && applicationStatus.trim().equalsIgnoreCase("Success"))
			objPojo.getObjUtilities().logReporter(
					"Response status : " + responseStatus + " || Application status : " + applicationStatus + " match",
					true);
		else if (responseStatus.trim().equalsIgnoreCase("Failed") && applicationStatus.trim().equalsIgnoreCase("Failure"))
			objPojo.getObjUtilities().logReporter("Response status : " + responseStatus + " || Application status : " + applicationStatus + " match",true);
		else
			objPojo.getObjUtilities().logReporter("Response status : " + responseStatus + " || Application status : "
					+ applicationStatus + " does not match", true);
		}else {
			objPojo.getObjUtilities().logReporter("Response status : " + responseStatus + " || Application status : "
					+ applicationStatus, true);
		}
		/*
		 * objPojo.getObjUtilities().logReporter("Verify following tags",
		 * applicationStatus, responseStatus.trim(),
		 * responseStatus.trim().equals(applicationStatus));
		 */
	}

	public void printMandateTypeTransactionReferNo(String transactionReferNo, String mandateType) {
		objPojo.getObjUtilities()
				.logReporter("Mandate Type : " + mandateType + "|| Transaction ReferNo : " + transactionReferNo, true);
	}
	
	public void printTransactionReferNo(String transactionReferNo) {
		objPojo.getObjUtilities()
				.logReporter("||Transaction ReferNo : " + transactionReferNo, true);
	}

	public Properties readConfig() {

		FileReader reader = null;
		try {
			reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

	public void loginCode(Map<String, String> data) {

		testData = data.get("USERNAME");
		if (!testData.equals(""))
			if (testData.toLowerCase().contains("runtime"))
				testData = objPojo.getObjUtilities().dpString("RuntimeCreatedAdminUserId");
		objLoginPage.setUserNameInAdminLoginPage(testData);
		testData = data.get("PASSWORD");
		if (!testData.equals(""))

			if (testData.toLowerCase().contains("runtime")) {
				testData = objPojo.getObjUtilities().dpString("RuntimeCreatedGenratedPassword");
				objLoginPage.setPasswordInAdminLoginPage(testData);
			} else {
				// testData = objDecryptionAlgo.decrypt(testData);
				objLoginPage.setPasswordInAdminLoginPage(testData);
			}
		objLoginPage.clickAdminLoginButton();
	}
	
	public String currentTime() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       
        return  sdf.format(cal.getTime());
	}
	
	public void staticWait(int waitTime) {
		//objPojo.getObjUtilities().logReporter("Before Delay time : "+currentTime(),true);
		objPojo.getObjUtilities().logReporter("Time Delay in minutes : "+waitTime,true);
		//waitTime = Integer.parseInt(readConfig().getProperty("waitTime")) * 60000;
		waitTime = waitTime * 60000;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//objPojo.getObjUtilities().logReporter("After Delay time : "+currentTime(),true);
	}
	
	public void staticWaitSeconds(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void compareTwoString(String responseStatus, String duplicateResponseStatus) {
		System.out.println("Common responseStatus : "+responseStatus+" Commom applicationStatus : "+duplicateResponseStatus);
		// Assert.assertEquals(applicationStatus, responseStatus.trim());
		if(responseStatus!=null && duplicateResponseStatus!=null) {
		if (responseStatus.trim().equalsIgnoreCase(duplicateResponseStatus.trim()))
			objPojo.getObjUtilities().logReporter(
					"Response status : " + responseStatus + " || duplicate response status : " + duplicateResponseStatus + " match",
					true);
		else if (responseStatus.trim().equalsIgnoreCase("Initiated") && duplicateResponseStatus.trim().equalsIgnoreCase("Success"))
			objPojo.getObjUtilities().logReporter(
					"Response status : " + responseStatus + " || duplicate response status : " + duplicateResponseStatus + " match",
					true);
		else
			objPojo.getObjUtilities().logReporter("Response status : " + responseStatus + " || duplicate response status : "
					+ duplicateResponseStatus + " does not match", true);
		}else {
			objPojo.getObjUtilities().logReporter("Response status : " + responseStatus + " || Application status : "
					+ duplicateResponseStatus, true);
		}
		/*
		 * objPojo.getObjUtilities().logReporter("Verify following tags",
		 * applicationStatus, responseStatus.trim(),
		 * responseStatus.trim().equals(applicationStatus));
		 */
	}
	
	public void compareTo(String actualValue, String expectedValue) {
		System.out.println("Common actual value : "+actualValue+" Commom Expected status : "+expectedValue);
		
		if(actualValue!=null && expectedValue!=null) {
		if (actualValue.trim().equalsIgnoreCase(expectedValue.trim()))
			objPojo.getObjUtilities().logReporter("Actual value : " + actualValue + " || Expected value : " + expectedValue + " match",true);
		else
			objPojo.getObjUtilities().logReporter("Actual value : " + actualValue + " || Expected value : "
					+ expectedValue + " does not match", true);
		}else
			objPojo.getObjUtilities().logReporter("Actual value : " + actualValue + " || Expected value : "+ expectedValue, true);
		
		
	}
}
