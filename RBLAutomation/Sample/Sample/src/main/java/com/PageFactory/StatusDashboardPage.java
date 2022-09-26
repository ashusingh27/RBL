package com.PageFactory;

import java.util.List;

import org.openqa.selenium.By;

import com.generics.Pojo;
import com.rbl.util.CommonCode;

public class StatusDashboardPage {

	private Pojo objPojo;

	private By transactionBookedlink = By.xpath("//*[@id=\"list\"]/tbody/tr[2]/td[2]/a");
	private By failureLink = By.xpath("//*[@id=\"list\"]/tbody/tr[2]/td[10]/a");
	private By successfullyPaidLink = By.xpath("//*[@id=\"list\"]/tbody/tr[2]/td[11]/a");
	private By deemedSuccessLink = By.xpath("//*[@id=\"list\"]/tbody/tr[2]/td[8]/a");
	private By paymentAPIFailureLink=By.xpath("//*[@id=\"list\"]/tbody/tr[2]/td[7]/a");

	private String baseWindow;

	private String status;
	private CommonCode objCommonCode;

	public StatusDashboardPage(Pojo Pojo) {
		objPojo = Pojo;
		objCommonCode=new CommonCode(objPojo);
	}

	public void clickTransactionBooked() {
		objPojo.getObjUtilities().logReporter("Click on Transaction Booked link",
				objPojo.getObjWrapperFunctions().click(transactionBookedlink));
	}

	public String fetchedTransactionStatus(String transactionReferencesNo, String statusStr) {
		//objPojo.getObjUtilities().logReporter("Switch window",
			//	objPojo.getObjWrapperFunctions().switchToWindowUsingTitle("RBL Last Mile Remit - Direct"));
		objPojo.getObjWrapperFunctions().SwitchToWindow(1);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+transactionReferencesNo, true);
		System.out.println("Child window : "+objPojo.getDriver().getWindowHandle());
		By applicationStatus = By.xpath("//td[text()=" + transactionReferencesNo.trim() + "]//following::td[10]");
		boolean statusFlag = false;
		List<String> statusList = objPojo.getObjWrapperFunctions().locateElements(applicationStatus);
		if(!statusList.isEmpty())
		 status = statusList.get(0);
		
		//objPojo.getObjWrapperFunctions().switchToWindowContainsURL("https://rbllastmile.remit.in/onlinetransfer/secure/home.jsp#");
		objPojo.getObjWrapperFunctions().SwitchToWindow(0);
		
		objCommonCode.staticWait(1);
		objPojo.getObjWrapperFunctions().switchToFrame("myfrm");
		
		
		return status;
	}

	public void clickFailureLink() {
		objPojo.getObjUtilities().logReporter("Click on failure link",
				objPojo.getObjWrapperFunctions().click(failureLink));
	}

	public void clickSuccessfullyPaidLink() {
		objPojo.getObjUtilities().logReporter("Click on Successfully paid link",
				objPojo.getObjWrapperFunctions().click(successfullyPaidLink));
	}

	public void clickDeemedSuccessLink() {
		objPojo.getObjUtilities().logReporter("Click on DeemedSuccess paid link",
				objPojo.getObjWrapperFunctions().click(deemedSuccessLink));
	}
	
	public void clickPaymentAPIFailure() {
		objPojo.getObjUtilities().logReporter("Click on paymentAPIFailure paid link",
				objPojo.getObjWrapperFunctions().click(paymentAPIFailureLink));
	}

	public void clickStatusLink(String status) {
		
		if (status.equalsIgnoreCase("Failure")||status.equalsIgnoreCase("Failed"))
			clickFailureLink();
		else if (status.equalsIgnoreCase("Success") || status.equalsIgnoreCase("Initiated"))
			clickSuccessfullyPaidLink();
		else if (status.equalsIgnoreCase("Deemed Success"))
			clickDeemedSuccessLink();
		else if(status.equalsIgnoreCase("Technical Failure"))
			clickPaymentAPIFailure();
		else
			clickTransactionBooked();
	}
}
