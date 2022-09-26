package com.PageFactory;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class LoginPage {

	// Input Fields
	private By inpUserName = By.xpath("//input[@name='userId']");
	private By inpPassword = By.name("password");

	// Buttons
	private By btnLoginButton = By.id("toggle");
	private By btnLogoutButton = By.xpath("//span[contains(text(),' Logout')]");
	
	private Pojo objPojo;

	public LoginPage(Pojo Pojo) {
		objPojo = Pojo;
	}

	public void setUserNameInAdminLoginPage(String testData) {
		objPojo.getObjUtilities().logReporter("Set User Name in Login Page",testData,
				objPojo.getObjWrapperFunctions().setText(inpUserName,testData));
	}

	public void setPasswordInAdminLoginPage(String testData) {
		objPojo.getObjUtilities().logReporter("Set Password in Login Page",testData,
				objPojo.getObjWrapperFunctions().setText(inpPassword,testData));
	}

	public void clickAdminLogoutButton() {
		objPojo.getObjUtilities().logReporter("Click on logout Button",
				objPojo.getObjWrapperFunctions().click(btnLogoutButton));
	}

	public void clickAdminLoginButton() {
		objPojo.getObjUtilities().logReporter("Click on login Button on Login Page",
				objPojo.getObjWrapperFunctions().click(btnLoginButton));
	}
	
	

	
}
