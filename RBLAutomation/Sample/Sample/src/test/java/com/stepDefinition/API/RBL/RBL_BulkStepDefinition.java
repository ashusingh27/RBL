package com.stepDefinition.API.RBL;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.PageFactory.BankUserDashboardPage;
import com.PageFactory.ExceptionQueueCheckerPage;
import com.PageFactory.ExceptionQueueMakerPage;
import com.PageFactory.HomePage;
import com.PageFactory.TransactionInquiryPage;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.rbl.util.CommonCode;
import com.rbl.util.ExcelUtil;
import com.rbl.util.PaymentModel;
import com.rbl.util.PaymentRequestBody;
import com.rbl.util.RestAssuredCode;
import com.rbl.util.StatusRequestBody;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;

public class RBL_BulkStepDefinition {

	ExcelUtil objExcelUtil;
	PaymentRequestBody objPaymentRequestBody;
	RestAssuredCode objRestAssuredCode;
	private StatusRequestBody objStatusRequestBody;
	private Map<String, PaymentModel> referNoPaymentModelObjMap;
	private String paymetRequestStr;
	private String responseString;
	private Pojo objPojo;
	private CommonCode objCommonCode;
	private String baseUrl;
	private String statusRequestBody;
	private HomePage objHomePage;
	private BankUserDashboardPage objBankUserDashPage;
	public Map<String, JsonPath> statusResponseMap;
	private Map<String,JsonPath> paymentResponseMap;
	private LinkedHashMap applicationStatusMap;
	private Map<String, JsonPath> transactionNoResponseMap;
	private String applicationStatus;
	private TransactionInquiryPage objTransactionInquiryPage;
	private Map<String, String> statusResponseStatusMap;
	private String responseStatus;
	private Boolean statusRestAPIFlag;
	private Boolean paymentRestAPIFlag;
	private Integer delayBetPaymentStatus;
	private Boolean executionForR=false;
	private ExceptionQueueMakerPage objExceptionQueueMakerPage;
	private ExceptionQueueCheckerPage objExceptionQueueCheckerPage;

	public RBL_BulkStepDefinition(BaseTest pojo) {
		objExcelUtil = new ExcelUtil();

		objPojo = pojo.initializeWebEnvironment();
		objHomePage = new HomePage(objPojo);
		objPaymentRequestBody = new PaymentRequestBody();
		objRestAssuredCode = new RestAssuredCode(objPojo);
		objCommonCode = new CommonCode(objPojo);
		objBankUserDashPage = new BankUserDashboardPage(objPojo);
		objStatusRequestBody = new StatusRequestBody();
		objTransactionInquiryPage = new TransactionInquiryPage(objPojo);
		objExceptionQueueMakerPage = new ExceptionQueueMakerPage(objPojo);
		objExceptionQueueCheckerPage = new ExceptionQueueCheckerPage(objPojo);
		statusResponseStatusMap = new LinkedHashMap<>();
		transactionNoResponseMap = new LinkedHashMap<>();
		
		baseUrl = objCommonCode.readConfig().getProperty("BaseURI");
		statusRestAPIFlag = Boolean.valueOf(objCommonCode.readConfig().getProperty("StatusRestAPI"));
		paymentRestAPIFlag = Boolean.valueOf(objCommonCode.readConfig().getProperty("PaymentRestAPI"));
		delayBetPaymentStatus = Integer.valueOf(objCommonCode.readConfig().getProperty("waitTime"));
	}

	@When("I want to execute payment {string} using excel file {string}")
	public void i_want_to_execute_payment_using_excel_file(String bulkTransaction, String excelFile) {
		
		objPojo.getObjUtilities().logReporter(
				"<b>I want to execute payment " + bulkTransaction + " using excel file " + excelFile + "</b>", true);
		paymentResponseMap=new LinkedHashMap<>();
		referNoPaymentModelObjMap = objExcelUtil.fetchDataFromExcel(excelFile);
		if (paymentRestAPIFlag) {
			statusResponseMap = new LinkedHashMap<String, JsonPath>();
			for (Map.Entry<String, PaymentModel> entry : referNoPaymentModelObjMap.entrySet()) {
				paymetRequestStr = objPaymentRequestBody.requestBodyWrite(entry.getValue());
				objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Body : </b>" + paymetRequestStr + "<br><br>",
						true);
				responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/ADDTRANSACTIONTEST", "post",
						"Payment_I.json");
				objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>",
						true);
				entry.getValue().setTransactionReferNo(PaymentRequestBody.latestReferNo);
				statusResponseMap.put(entry.getValue().getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
				paymentResponseMap.put(entry.getValue().getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
				referNoPaymentModelObjMap.put(entry.getKey(), entry.getValue());
			}
			
		}
	}

	@Then("I want to execute status rest api")
	public void i_want_to_execute_status_rest_api() {
		objPojo.getObjUtilities().logReporter("<b>I want to execute status rest api</b>", true);
		statusResponseMap = new LinkedHashMap<String, JsonPath>();
		if (statusRestAPIFlag) {

			if (paymentRestAPIFlag) {
		
					objCommonCode.staticWait(delayBetPaymentStatus);
			}
			System.out.println("<@@@======================= Status ========================@@@>");

			for (Map.Entry<String, PaymentModel> entry : referNoPaymentModelObjMap.entrySet()) {

				statusRequestBody = objStatusRequestBody.requestBodyWrite(entry.getValue().getMandateType(),
						entry.getValue().getTransactionReferNo());
				objPojo.getObjUtilities().logReporter("<b>StatusReqeust Body : </b>" + statusRequestBody + "<br><br>",
						true);
				responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/STATUSCHECKTEST", "Post",
						"Status_I.json");

				statusResponseMap.put(entry.getValue().getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
				objPojo.getObjUtilities().logReporter("<b>Status response : </b>" + responseString + "<br><br>", true);

			}
			RBLStepDefinition.statusResponseMap = statusResponseMap;
			for (Map.Entry<String, JsonPath> entry1 : statusResponseMap.entrySet()) {
				System.out.println("Key = " + entry1.getKey() + ", Value = " + entry1.getValue());
			}

		}
	}

	@Then("I want to validate error massage for {string}")
	public void i_want_to_validate_error_massage_for(String bulkTransaction) {
		objPojo.getObjUtilities().logReporter("<b>I want to validate error massage for </b>" + bulkTransaction, true);
		if (statusRestAPIFlag) {

			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				JsonPath statusResponseInJsonPath = entry.getValue();
				responseStatus = objRestAssuredCode.errorForCautionWord(bulkTransaction, statusResponseInJsonPath);
				statusResponseStatusMap.put(entry.getKey(), responseStatus);
			}

		}

	}
	
	@Then("I want to validate error massage for {string} in STP")
	public void i_want_to_validate_error_massage_for_in_STP(String bulkTransaction) {

		objPojo.getObjUtilities().logReporter("<b>I want to validate error massage for </b>" + bulkTransaction, true);
		if (statusRestAPIFlag) {

			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				JsonPath statusResponseInJsonPath = entry.getValue();
				responseStatus = objRestAssuredCode.errorForInvalidDataSTP(bulkTransaction, statusResponseInJsonPath);
				statusResponseStatusMap.put(entry.getKey(), responseStatus);
			}

		}

	}

	@When("I want to execute payment {string} using excel file {string} for duplicate")
	public void i_want_to_execute_payment_using_excel_file_for_duplicate(String bulkTransaction, String excelFile) {
		objPojo.getObjUtilities().logReporter(
				"<b>I want to execute payment " + bulkTransaction + " using excel file " + excelFile + "for duplicate</b>", true);
		PaymentModel.duplicateFlag = true;
		paymentResponseMap=new LinkedHashMap<>();
		// referNoPaymentModelObjMap = objExcelUtil.fetchDataFromExcel(excelFile);
		if (paymentRestAPIFlag) {
			statusResponseMap = new LinkedHashMap<String, JsonPath>();
			for (Map.Entry<String, PaymentModel> entry : referNoPaymentModelObjMap.entrySet()) {
				paymetRequestStr = objPaymentRequestBody.requestBodyWrite(entry.getValue());
				objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Body : </b>" + paymetRequestStr + "<br><br>",
						true);
				responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/ADDTRANSACTIONTEST", "post",
						"Payment_I.json");
				objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>",
						true);
				// entry.getValue().setTransactionReferNo(Pay);
				statusResponseMap.put(entry.getValue().getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
				referNoPaymentModelObjMap.put(entry.getKey(), entry.getValue());
				paymentResponseMap.put(entry.getValue().getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
			}
		}
		PaymentModel.duplicateFlag = false;

	}

	@Then("I am fetch Status response for duplicate")
	public void i_am_fetch_Status_response_for_duplicate() {
		statusResponseStatusMap = RBLStepDefinition.statusResponseStatusMap;

		Map<String, String> duplicateStatusMap = new LinkedHashMap<>();
		for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {
			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				if(entry1.getKey().equalsIgnoreCase(entry.getKey())) {
				JsonPath statusResponseInJsonPath = entry.getValue();
				responseStatus = objRestAssuredCode.statusResponseProcess(statusResponseInJsonPath);
				duplicateStatusMap.put(entry.getKey(), responseStatus);
				break;
				}
			}
			objCommonCode.printTransactionReferNo(entry1.getKey());
			objCommonCode.compareTwoString(entry1.getValue(), responseStatus);
		}
	}
	
	@Then("I am fetch Status response then validate")
	public void i_am_fetch_Status_response_then_validate() {
		objPojo.getObjUtilities().logReporter("<b>I am fetch Status response then validate</b>", true);
		
		if (statusRestAPIFlag) {
		for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
			JsonPath statusResponseInJsonPath = entry.getValue();
			responseStatus = objRestAssuredCode.statusResponseProcess(statusResponseInJsonPath);
			statusResponseStatusMap.put(entry.getKey(), responseStatus);
			objPojo.getObjUtilities().logReporter("TransactionRefer No : "+entry.getKey(),true);
			objCommonCode.compareToMap(responseStatus, "Hold");
		}
	}
	}

	@Then("I am going to check whether above transactions in bulk can be initiated by maker")
	public void i_am_going_to_check_whether_above_transactions_in_bulk_can_be_initiated_by_maker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to check whether above transactions in bulk can be initiated by maker</b>", true);
		
		
		if (statusRestAPIFlag) {
			objHomePage.clickTransactionOpsMakerButton();
			objHomePage.clickQueueMaker();
			objExceptionQueueMakerPage.switchToFrame("myfrm");
			objExceptionQueueMakerPage.fillAndSubmitMakerForm();
			objCommonCode.staticWait(2);
			objExceptionQueueMakerPage.clickFormSubmitButton1();
			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				
			objExceptionQueueMakerPage.fillAndSubmitMakerTransactionForm(entry.getKey());
			}
			objExceptionQueueMakerPage.clickSubmitButton();
			objExceptionQueueMakerPage.acceptPopup();
			//objCommonCode.staticWait(0);
			objExceptionQueueMakerPage.clickBackButton();
			objExceptionQueueMakerPage.defaultFrame();
			objExceptionQueueMakerPage.clickLogoutButtonM();

		}

	}

	@Then("I am going to check whether above transactions in bulk can be authorised by checker")
	public void i_am_going_to_check_whether_above_transactions_in_bulk_can_be_authorised_by_checker() {

		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether above transactions can be processed by checker</b>",true);
		if (statusRestAPIFlag) {
			objHomePage.clickTransactionOpsCheckerButton();
			objHomePage.clickQueueChecker();
			objExceptionQueueCheckerPage.switchToFrame("myfrm");
			objExceptionQueueCheckerPage.fillAndSubmitCheckerTransactionForm();
			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				
				objExceptionQueueCheckerPage.approveTransactionByChecker(entry.getKey());
			
			}
			objExceptionQueueCheckerPage.clickSubmitButton();
			objCommonCode.staticWaitSeconds(3000);
			objExceptionQueueCheckerPage.clickBackButton();
			objExceptionQueueMakerPage.defaultFrame();
			objExceptionQueueMakerPage.clickLogoutButtonM();
		}
	
	}

	@Then("I am fetch Status response in bulk then validate")
	public void i_am_fetch_Status_response_in_bulk_then_validate() {

		if (statusRestAPIFlag) {
			statusResponseStatusMap = new LinkedHashMap<String, String>();
			
			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				JsonPath statusResponseInJsonPath = entry.getValue();
				responseStatus = objRestAssuredCode.statusResponseProcess(statusResponseInJsonPath);
				statusResponseStatusMap.put(entry.getKey(), responseStatus);
			}

		}
	
	    
	}
	
	@Then("I want to validate object {string} for this string {string} in payment response")
	public void i_want_to_validate_object_for_this_string_in_payment_response(String jsonObject, String verifyString) {
		objPojo.getObjUtilities().logReporter("<b>I want to validate object "+jsonObject+" for this string "+verifyString+" in payment response</b>",true);
		for (Map.Entry<String, JsonPath> entry : paymentResponseMap.entrySet()) {
			JsonPath responseInJsonPath = entry.getValue();
			String errorMsg=objRestAssuredCode.fetchError(jsonObject, verifyString, responseInJsonPath);
			objCommonCode.compareTo(verifyString, errorMsg);
		}
	}
	
	@Then("I am close browser for bulk")
	public void i_am_close_browser_for_bulk() {
		System.out.println("Close browser");
		objPojo.getDriver().quit();
	}
}
