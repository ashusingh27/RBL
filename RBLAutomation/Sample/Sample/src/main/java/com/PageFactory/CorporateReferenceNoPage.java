package com.PageFactory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import com.generics.Pojo;
import com.rbl.util.CommonCode;
import com.rbl.util.RestAssuredCode;

public class CorporateReferenceNoPage {

	private Pojo objPojo;
	private RestAssuredCode objRestAssuredCode;

	private By refernceNotxtField = By.xpath("//input[@name='CrpRefNumber']");
	private By submitCorpButton = By.xpath("//input[@value='Submit']");
	private By fetchStatusLable = By.xpath("//form[@name=\"frmModifyTxnStatus\"]//table[@class=\"listingtable\"]//td[6]");
	private Map<String, String> applicationStatusMap;
	private String applicationStatus;
	private String noDataLabelStr;
	private CommonCode objCommonCode;

	public CorporateReferenceNoPage(Pojo Pojo) {
		objPojo = Pojo;
		objRestAssuredCode = new RestAssuredCode(objPojo);
		applicationStatusMap = new HashMap<String, String>();
		objCommonCode=new CommonCode(objPojo);
	}

	public String fetchStatus() {
		String fetchStatusLabelText="No";
		if(objPojo.getObjWrapperFunctions().checkElementDisplayed(fetchStatusLable))
			fetchStatusLabelText=objPojo.getObjWrapperFunctions().getText(fetchStatusLable, "text");
		return fetchStatusLabelText;
	}
	
	public void fillCorporateReferencNoForm(String transactionReferNo) {
		objPojo.getObjUtilities().logReporter("fill RequestNo : ", transactionReferNo,
				objPojo.getObjWrapperFunctions().setText(refernceNotxtField, transactionReferNo));
		
		objCommonCode.staticWait(2);

		objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(submitCorpButton));
	}

	public String fillAndSubmitCorporateRefNo(String transactionReferNo) {

		fillCorporateReferencNoForm(transactionReferNo);

		
		
		

		/*objPojo.getObjUtilities().logReporter("Click on submit Button",
				objPojo.getObjWrapperFunctions().click(submitCorpButton));*/
		String fetchStatus=fetchStatus();
		if(!fetchStatus.isEmpty()) {
			try {
		 applicationStatus = objRestAssuredCode.convertStrIntoJsonObject(fetchStatus);
			}catch(ClassCastException e) {
				By nodataLabel=By.xpath("//td[@valign=\"top\" ]//following::center[text()='No Data Found']");
				noDataLabelStr=objPojo.getObjWrapperFunctions().getText(nodataLabel, "text");
				objPojo.getObjUtilities().logReporter(noDataLabelStr+" found", true);
			}
		}
		else {
			fillCorporateReferencNoForm(transactionReferNo);
			fetchStatus=fetchStatus();
			if(!fetchStatus.isEmpty())
			applicationStatus = objRestAssuredCode.convertStrIntoJsonObject(fetchStatus);
		}
		return applicationStatus;
	}
}
