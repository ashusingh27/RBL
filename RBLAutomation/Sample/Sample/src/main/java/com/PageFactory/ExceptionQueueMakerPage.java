package com.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.generics.Pojo;
import com.rbl.util.CommonCode;
import com.rbl.util.PaymentModel;

public class ExceptionQueueMakerPage {

	private By selectGroupDropdown = By.xpath("//select[@id='strGroupId']");
	private By selectCorporateMakerDropdown = By.xpath("//select[@id='entityId']");
	private By btnBackButton = By.xpath("//input[@id='button']");
	private By btnlogoutButton = By.linkText("Logout");
	private By btnSubmitButton = By.xpath("//input[@name='showCount']//following::input[@type='submit'][2]");
	private By btnFormSubmitButton =By.xpath("//input[@name='showCount']//following::input[@type='submit'][1]");
	private By dateFromCalendar = By.xpath("//input[@name='dateFrom']//following::img[1]");
	private By toCalender = By.xpath("//input[@name='dateFrom']//following::img[2]");
	private Pojo objPojo;
	private CommonCode objCommonCode;

	public ExceptionQueueMakerPage(Pojo Pojo) {
		objPojo = Pojo;
		objCommonCode=new CommonCode(objPojo);
	}

	public ExceptionQueueMakerPage() {

	}

	public void selectGroup(String option) {
		objPojo.getObjUtilities().logReporter(option + " as option in Group dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectGroupDropdown, option, "Text"));
	}

	public void selectCorporateMaker(String option) {
		objPojo.getObjUtilities().logReporter(option + " as option in Corporate Maker dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectCorporateMakerDropdown, option, "Text"));
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
				objPojo.getObjWrapperFunctions().click(btnlogoutButton));
	}

	public void clickSubmitButton() {
		objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(btnSubmitButton));
	}
	
	public void clickFormSubmitButton() {
		objPojo.getObjUtilities().logReporter("Click on form submit Button",
				objPojo.getObjWrapperFunctions().click(btnFormSubmitButton));
	}
	
	public void clickFormSubmitButton1() {
		
				objPojo.getObjWrapperFunctions().click(btnFormSubmitButton);
		//objCommonCode.staticWait(1);
	}

	public void clickDateFromCalendar() {
		objPojo.getObjUtilities().logReporter("Click on Date from calendar Button",
				objPojo.getObjWrapperFunctions().click(dateFromCalendar));
	}

	public void clickToCalendar() {
		objPojo.getObjUtilities().logReporter("Click on to calendar Button",
				objPojo.getObjWrapperFunctions().click(toCalender));
	}
	
	
	
	public void clickDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		String todayStr = df.format(dateobj);
		String[] dateArray = todayStr.split("/");
		String dayInDigit=dateArray[0].replaceAll("^0+(?!$)","");
		By dateLink = By.xpath("//table[@class='days']//following::td[text()='"+dayInDigit+"']");
		List<WebElement> days=objPojo.getObjWrapperFunctions().locateElements(dateLink,"Days");
		for(WebElement dayElement:days) {
			String classString=objPojo.getObjWrapperFunctions().getAttribute(dayElement,"class");
			if(classString.contains("today"))
				dayElement.click();
			break;
		}
	}

	public void selectTodayDate() {

		clickDateFromCalendar();
		//By dateLink = By.xpath("//tr[@class='week week0']//following::td[text()='"+dateArray[0]+"']");
		clickDate();
		clickToCalendar();
		clickDate();
		
		//objCommonCode.staticWait(2);
	}
	
	public void checkTransactionReferNo(String trasactionReferNo) {
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+trasactionReferNo, true);
		By transactionLable = By.xpath("//td//a[text()='" + trasactionReferNo + "']");
		if(objPojo.getObjWrapperFunctions().checkElementDisplayed(transactionLable))
			objPojo.getObjUtilities().logReporter("Same transaction can initiate again", true);
		else
			objPojo.getObjUtilities().logReporter("Same transaction can't initiate again", true);
	}
	
	public void acceptPopup() {
		objPojo.getObjUtilities().logReporter("Accept Popup",true);
		objPojo.getObjWrapperFunctions().alertBoxAccept();
	}

	public void fillAndSubmitMakerTransactionForm(String trasactionReferNo) {
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+trasactionReferNo,true);
		By transactionLable = By.xpath("//td//a[text()='" + trasactionReferNo + "']");
		if (transactionLable != null) {
			By approveCheckBox = By.xpath("//td//a[text()='" + trasactionReferNo + "']//following::input[1]");
			
			//objPojo.getDriver().findElement(approveCheckBox).click();
			objPojo.getObjUtilities().logReporter("approve checkBox",objPojo.getObjWrapperFunctions().click(approveCheckBox));
			By remarkTextfield = By.xpath("//td//a[text()='" + trasactionReferNo + "']//following::input[5]");
			//objPojo.getDriver().findElement(remarkTextfield).sendKeys("automation");
			objPojo.getObjUtilities().logReporter("fill remark textfield : Automation",objPojo.getObjWrapperFunctions().setText(remarkTextfield, "Automation for maker"));
		}
	}
	
	public void fillAndSubmitMakerForm() {
		
		selectGroup("RGEX-RG ExHouse");
		selectCorporateMaker("AQMF001 - AQMF");
		selectTodayDate();
		clickFormSubmitButton();
	}

	
}
