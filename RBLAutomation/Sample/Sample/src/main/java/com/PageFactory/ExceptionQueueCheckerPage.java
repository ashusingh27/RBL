package com.PageFactory;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class ExceptionQueueCheckerPage {
	
	
	private By selectGroupDropdown = By.xpath("//select[@id='strGroupId']");
	private By selectCorporateCheckerDropdown = By.xpath("//select[@id='entityId']");
	private By btnBackButton = By.xpath("//input[@id='button']");
	private By btnlogout = By.linkText("Logout");
	private By btnSubmit= By.xpath("//input[@name='showCount']//following::input[@type='submit'][2]");
	private Pojo objPojo;

	public ExceptionQueueCheckerPage(Pojo Pojo) {
		objPojo = Pojo;
	}

	
	public void selectGroup(String option) {
		objPojo.getObjUtilities().logReporter(option+" as option in Group dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectGroupDropdown, option, "Text"));
	}
	
	public void selectCorporateChecker(String option) {
		objPojo.getObjUtilities().logReporter(option+" as option in Corporate Checker dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectCorporateCheckerDropdown, option, "Text"));
	}
	
	public void switchToFrame(String frameName) {
		objPojo.getObjUtilities().logReporter("Swtich to frame",
				objPojo.getObjWrapperFunctions().switchToFrame(frameName));
	}
	
	public void defaultFrame() {
		objPojo.getDriver().switchTo().parentFrame();
	
	}

	
	public void clickBackButton() {
		objPojo.getObjUtilities().logReporter("Click on Back Button",
				objPojo.getObjWrapperFunctions().click(btnBackButton));
	}
	
	public void clickLogoutButtonM() {
		objPojo.getObjUtilities().logReporter("Click on logout Button",
				objPojo.getObjWrapperFunctions().click(btnlogout));
	}
	
	public void clickSubmitButton() {
		objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(btnSubmit));
	}
	
	public void clickApproveCheckBox(String trasactionReferNo) {
		By approveCheckBox= By.xpath("//td//a[text()='"+trasactionReferNo+"']//preceding::input[2]");
		
		objPojo.getObjUtilities().logReporter("Click on approve checkbox ",
				objPojo.getObjWrapperFunctions().click(approveCheckBox));
		
	}
	
	public void setRemarkTextfield(String trasactionReferNo) {
		By remarkTextfield= By.xpath("//td//a[text()='"+trasactionReferNo+"']//following::input[1]");
		objPojo.getObjUtilities().logReporter("Enter remark as Automation",
				objPojo.getObjWrapperFunctions().setText(remarkTextfield, "Automation"));
	}
	
	public void fillAndSubmitCheckerTransactionForm() {
		
		selectGroup("RGEX-RG ExHouse");
		selectCorporateChecker("AQMF001 - AQMF");
	}
	
	public void checkTransactionRefer(String trasactionReferNo) {
		By transactionLabel=By.xpath("//td//a[text()='"+trasactionReferNo+"']");
		if(objPojo.getObjWrapperFunctions().checkElementDisplayed(transactionLabel))
			objPojo.getObjUtilities().logReporter("Same transaction can authorized again by checker", true);
		else
			objPojo.getObjUtilities().logReporter("Same transaction can not authorized again by checker", true);
	}
	
	public void approveTransactionByChecker(String trasactionReferNo) {
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+trasactionReferNo,true);
		By transactionLabel=By.xpath("//td//a[text()='"+trasactionReferNo+"']");
		if(transactionLabel!=null) {
			clickApproveCheckBox(trasactionReferNo);
			setRemarkTextfield(trasactionReferNo);
		}
	}

}
