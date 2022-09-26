package com.PageFactory;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class HomePage {

	private By btnMISButton = By.xpath("//font[text()='MIS']");
	private By btnTransactionDashButton = By.xpath("//font[text()='Transaction Dashboard']");
	private By btnLogs = By.xpath("//font[contains(text(),'Logs')]");
	private By btnPPSLogs = By.xpath("//font[contains(text(),'PPS Log')]");
	private By btnTransactionLogs = By.xpath("//font[contains(text(),'Transaction Log')]");
	private By tabMenu = By.xpath("/html/body/table[1]/tbody/tr[3]/td/table/tbody/tr/td[2]/div/a/img");
	private By btntransactionInquiryMIS = By.xpath("//a[@id=\"activity77\"]");

	private By btnTransactionOpsMaker = By.xpath("//font[contains(text(),'Transaction Ops Maker')]");
	private By btnQueueMaker = By.xpath("//font[contains(text(),'Exception Transaction Ops Queue Maker')]");

	private By btnTransactionOpsChecker = By.xpath("//font[contains(text(),'Transaction Ops Checker')]");
	private By btnQueueChecker = By.xpath("//font[contains(text(),'Exception Transaction Ops Queue Checker')]");
	private Pojo objPojo;

	public HomePage(Pojo Pojo) {
		objPojo = Pojo;
	}

	public void clickMISButton() {

		objPojo.getObjUtilities().logReporter("Click on MIS Button",
				objPojo.getObjWrapperFunctions().click(btnMISButton));
	}

	public void checkMISButton() {
		if (!objPojo.getObjWrapperFunctions().checkElementDisplayed(btnMISButton)) {
			// click on menu tab
			objPojo.getObjUtilities().logReporter("Click on menu bar", objPojo.getObjWrapperFunctions()
					.actionClick(By.xpath("/html/body/table[1]/tbody/tr[3]/td/table/tbody/tr/td[2]/div/a/img")));
		}
	}

	public void clickTransactionDashButton() {
		if (objPojo.getObjWrapperFunctions().checkElementDisplayed(btnTransactionDashButton)) {
			objPojo.getObjUtilities().logReporter("Click on TransactionDashBoard Button",
					objPojo.getObjWrapperFunctions().click(btnTransactionDashButton));
		} else {
			clickMISButton();
			objPojo.getObjUtilities().logReporter("Click on TransactionDashBoard Button",
					objPojo.getObjWrapperFunctions().click(btnTransactionDashButton));
		}
	}

	public void clickLogButton() {
		objPojo.getObjUtilities().logReporter("Click on Logs button", objPojo.getObjWrapperFunctions().click(btnLogs));

	}

	public void clickTransactionLogButton() {
		objPojo.getObjUtilities().logReporter("Click on Transaction Logs button",
				objPojo.getObjWrapperFunctions().click(btnTransactionLogs));

	}

	public void clickPPSLogButton() {
		objPojo.getObjUtilities().logReporter("Click on PPS Logs button",
				objPojo.getObjWrapperFunctions().click(btnPPSLogs));

	}

	public void clickTransactionInquiryMIS() {

		objPojo.getObjUtilities().logReporter("Click on Transaction Inquiry MIS",
				objPojo.getObjWrapperFunctions().actionClick(btntransactionInquiryMIS));

	}

	public void clickMenuButton() {
		objPojo.getDriver().switchTo().parentFrame();
		objPojo.getObjUtilities().logReporter("Click on Menu tab",
				objPojo.getObjWrapperFunctions().actionClick(tabMenu));

	}

	public void clickTransactionOpsMakerButton() {
		objPojo.getObjUtilities().logReporter("click TransactionOps Maker Button",
				objPojo.getObjWrapperFunctions().click(btnTransactionOpsMaker));

	}

	public void clickQueueMaker() {
		objPojo.getObjUtilities().logReporter("click Queue Maker Button",
				objPojo.getObjWrapperFunctions().click(btnQueueMaker));

	}

	public void clickTransactionOpsCheckerButton() {
		objPojo.getObjUtilities().logReporter("click TransactionOps Checker Button",
				objPojo.getObjWrapperFunctions().click(btnTransactionOpsChecker));

	}

	public void clickQueueChecker() {
		objPojo.getObjUtilities().logReporter("click Queue Checker Button",
				objPojo.getObjWrapperFunctions().click(btnQueueChecker));

	}

}
