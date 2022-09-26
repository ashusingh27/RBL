package com.PageFactory;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class WUPPSFinalRequestNumberPage {

	
	private Pojo objPojo;
	
	private By requestNotxtfiled = By.xpath("//input[@name='finalreqres']");
	private By submitbtn = By.xpath("//input[@name='Submit']");
	
	
	
	public WUPPSFinalRequestNumberPage(Pojo Pojo) {
		objPojo = Pojo;
	}
	
	
	public void fillAndSubmitRequestNo(String transactionReferNo) {
		objPojo.getObjUtilities().logReporter("fill RequestNo : "+transactionReferNo,
				objPojo.getObjWrapperFunctions().setText(requestNotxtfiled,transactionReferNo));
		
		objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(submitbtn));
		
		By finalResponse=By.xpath("//*[@id=\"list\"]/tbody/tr[2]/td[4]");
		
		objPojo.getObjUtilities().logReporter("Final response : "+objPojo.getObjWrapperFunctions().getText(finalResponse,"text"),true);
	}
	
	
}
