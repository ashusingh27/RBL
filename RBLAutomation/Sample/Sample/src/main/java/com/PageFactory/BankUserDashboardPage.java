package com.PageFactory;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class BankUserDashboardPage {


	private Pojo objPojo;
	
	private By selectCorporateDropDown=By.xpath("//select[@id='entityId']");
	private By selectFrequencyDropDown=By.xpath("//select[@id='frequency']");
	private By submitButton=By.xpath("//input[@value='Submit']");
	
	

	public BankUserDashboardPage(Pojo Pojo) {
		objPojo = Pojo;
	}
	
	public void selectCorporate(String option) {
		objPojo.getObjUtilities().logReporter(option+" as option in corporate dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectCorporateDropDown, option, "Text"));
	}
	
	public void selectFrequency(String option) {
		objPojo.getObjUtilities().logReporter(option+" as option in frquency dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectFrequencyDropDown, option, "Text"));
	}
	
	public void clickSubmitButton() {
		objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(submitButton));
	}
	
	public void switchToFrame(String frameName) {
		objPojo.getObjUtilities().logReporter("Swtich to frame",
				objPojo.getObjWrapperFunctions().switchToFrame(frameName));
	}
	
}
