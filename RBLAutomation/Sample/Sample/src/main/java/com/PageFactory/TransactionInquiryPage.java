package com.PageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.generics.Pojo;

public class TransactionInquiryPage {

	private Pojo objPojo;

	private By selectGroupDropDown = By.xpath("//select[@id='strGroupId']");
	private By corporateDropDown = By.xpath("//select[@id='entityId']");
	private By mandateTypeDropDown = By.xpath("//select[@id='mandateType']");
	private By btnSubmit = By.xpath("//input[@name=\"Submit\"]");

	public TransactionInquiryPage(Pojo Pojo) {
		objPojo = Pojo;
	}

	public String fetchedTransactionStatus(String transactionReferenceNo) {
		String status = null;
		objPojo.getObjUtilities().logReporter("Fetch application Status",
				objPojo.getObjWrapperFunctions().switchToWindowUsingTitle("RBL Last Mile Remit - Direct"));
		// By applicationStatus =
		// By.xpath("//td//a[text()='"+transactionReferenceNo.trim()+"']//following::td[10]");
		By totalRow = By.xpath("//table[@class='listingtable']//tbody//tr");
		List<String> statusList = objPojo.getObjWrapperFunctions().locateElementsWithOutSwitch(totalRow);
		for (int i = statusList.size(); i >= 0; i++) {
			String referNo = objPojo.getObjWrapperFunctions().getText(By.xpath("//*[@id='" + i + "']/td[4]"), "Text");
			if (referNo.equalsIgnoreCase(transactionReferenceNo)) {
				System.out.println("Transaction inquery refer No : " + referNo);
				status = objPojo.getObjWrapperFunctions().getText(By.xpath("//*[@id='" + i + "']/td[14]"), "Text");
			}
		}
		// String status=statusList.get(0);

		return status;
	}

	public void selectGroup(String option) {
		objPojo.getObjUtilities().logReporter(option + " as option in selectgroup dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(selectGroupDropDown, option, "Text"));
	}

	public void selectCorporate(String option) {
		objPojo.getObjUtilities().logReporter(option + " as option in corporate dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(corporateDropDown, option, "Text"));
	}

	public void selectMandateType(String option) {
		objPojo.getObjUtilities().logReporter(option + " as option in mandate dropdown",
				objPojo.getObjWrapperFunctions().selectDropDownOption(mandateTypeDropDown, option, "Text"));
	}

	public void clickSubmitButton() {
		objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(btnSubmit));
	}

	public String fillTransactionInquiry(String transactionReferenceNo) {
		selectGroup("RGEX-RG ExHouse");
		selectCorporate("AQMF001 - AQMF");
		String applicationStatus=null;
		objPojo.getObjUtilities().logReporter("Transaction Refer No : "+transactionReferenceNo, true);
		List<String> statusList=new ArrayList<String>();
		System.out.println(objPojo.getObjWrapperFunctions().getText(By.xpath("//*[@id='2']/td[2]"), "Text"));
		List<WebElement> elements=objPojo.getDriver().findElements(By.xpath("//td//a[text()='"+transactionReferenceNo.trim()+"']//following::td[10]"));
		for(WebElement element:elements)
			statusList.add(element.getText());
		System.out.println(statusList.get(0));
		
		return statusList.get(0);
	}

}
