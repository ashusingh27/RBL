package com.stepDefinition.API.RBL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.testng.Assert;

import com.PageFactory.BankUserDashboardPage;
import com.PageFactory.CorporateReferenceNoPage;
import com.PageFactory.ExceptionQueueCheckerPage;
import com.PageFactory.ExceptionQueueMakerPage;
import com.PageFactory.HomePage;
import com.PageFactory.LoginPage;
import com.PageFactory.StatusDashboardPage;
import com.PageFactory.TransactionInquiryPage;
import com.PageFactory.WUPPSFinalRequestNumberPage;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.generics.Utilities;
import com.rbl.util.CommonCode;
import com.rbl.util.ExcelUtil;
import com.rbl.util.PaymentModel;
import com.rbl.util.PaymentRequestBody;
import com.rbl.util.RestAssuredCode;
import com.rbl.util.StatusRequestBody;
import com.rbl.util.StatusRestAPI;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RBLStepDefinitionNSTP {

	private String BaseURI;
	private static int numberTimesDriverClose=0;
	private Response response;
	private Pojo objPojo;
	
	private JsonPath jsonPathEvaluator;

	private String testData;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private StatusDashboardPage objStatusDashPage;
	private CorporateReferenceNoPage objCorporateReferNoPage;
	public static PaymentModel objPaymentModel;
	private String transactionReferencesNo;
	private String applicationStatus;
	private String responseStatus;
	private String responseString;
	private String errorMsg;
	private String requestString;
	public static boolean technicalError=false;
	private StatusRequestBody objStatusRequestBody;
	private StatusRestAPI objStatusRestAPI;
	private CommonCode objCommonCode;

	private ExceptionQueueMakerPage objExceptionQueueMakerPage;
	private ExceptionQueueCheckerPage objExceptionQueueCheckerPage;

	private RestAssuredCode objRestAssuredCode;
	private PaymentRequestBody objPaymentRequestBody;
	private static Map<String, String> transactionReferNoMap;
	private static Map<String, JsonPath> statusResponseMap;
	private static Map<String, JsonPath> paymentResponseMap;
	Properties prop;
	private String transactionReferNo;
	private ExcelUtil objExeclUtil;

	private Integer delayBetPaymentStatus;

	private Boolean statusRestAPIFlag;

	private Boolean paymentRestAPIFlag;

	private String paymetRequestStr;

	private String baseUrl;

	private Object statusRequestBody;

	private LinkedHashMap<String, String> statusResponseStatusMap;

	private BankUserDashboardPage objBankUserDashPage;

	private WUPPSFinalRequestNumberPage objPPSFinalRequestNoPage;

	private TransactionInquiryPage objTransactionInquiryPage;

	private String testCaseName;
	
	private static Map<String,String> applicationStatusMap;

	public RBLStepDefinitionNSTP(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objLoginPage = new LoginPage(objPojo);
		objHomePage = new HomePage(objPojo);
		objStatusRestAPI = new StatusRestAPI();
		objBankUserDashPage = new BankUserDashboardPage(objPojo);
		objExceptionQueueMakerPage = new ExceptionQueueMakerPage(objPojo);
		objExceptionQueueCheckerPage = new ExceptionQueueCheckerPage(objPojo);
		objStatusDashPage = new StatusDashboardPage(objPojo);
		objPPSFinalRequestNoPage = new WUPPSFinalRequestNumberPage(objPojo);
		objCorporateReferNoPage = new CorporateReferenceNoPage(objPojo);
		objTransactionInquiryPage=new TransactionInquiryPage(objPojo);
		objStatusRequestBody = new StatusRequestBody();
		objRestAssuredCode = new RestAssuredCode(objPojo);
		transactionReferNoMap = new HashMap<>();
		objPaymentRequestBody = new PaymentRequestBody();
		statusResponseMap = new HashMap<>();
		objPaymentModel = new PaymentModel();
		objCommonCode = new CommonCode(objPojo);
		prop = new Properties();
		baseUrl = objCommonCode.readConfig().getProperty("BaseURI");
		statusRestAPIFlag = Boolean.valueOf(objCommonCode.readConfig().getProperty("StatusRestAPI"));
		paymentRestAPIFlag = Boolean.valueOf(objCommonCode.readConfig().getProperty("PaymentRestAPI"));
		delayBetPaymentStatus = Integer.valueOf(objCommonCode.readConfig().getProperty("waitTime"));
	}

	@Given("I am requesting NSTP {string}.")
	public void i_am_requesting(String baseUri) {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseURI = prop.getProperty(baseUri);
		System.out.println(BaseURI);
		statusRestAPIFlag = Boolean.valueOf(prop.getProperty("StatusRestAPI"));
		paymentRestAPIFlag = Boolean.valueOf(prop.getProperty("PaymentRestAPI"));
		delayBetPaymentStatus = Integer.valueOf(prop.getProperty("waitTime"));
		objExeclUtil = new ExcelUtil();
	}

	@When("I want to  {string} and {string} NSTP API Request saved in JSON File {string} without charset")
	public void i_want_to_and_NSTP_API_Request_saved_in_JSON_File_without_charset(String endPoint, String requestType,
			String jsonFileName, DataTable credentials) {

		if (paymentRestAPIFlag) {
			paymentResponseMap = new HashMap<>();
			List<Map<String, String>> credentialsMapList = credentials.asMaps(String.class, String.class);
			objExeclUtil.writeInExcel();
			if (jsonFileName.equalsIgnoreCase("Payment_I.json")
					|| jsonFileName.equalsIgnoreCase("TechnicalErrorNSTP.json")) {
				for (Map<String, String> data : credentialsMapList) {
					objPaymentModel.setMandateType(data.get("MANDATETYPE").trim());
					objPaymentModel.setAmount(data.get("AMOUNT").trim());
					objPaymentModel.setBeneficiaryAccountNo(data.get("BENEFICIARYACCOUNTNO").trim());
					objPaymentModel.setBeneficiaryIFSCCode(data.get("BENEFICIARYIFSC").trim());
					objPaymentModel.setBeneficiaryAccountHolderName(data.get("BENEFICIARYACCOUNTHOLDERNAME").trim());
					objPaymentModel.setRemitterName(data.get("REMITTERNAME").trim());

					paymetRequestStr = objPaymentRequestBody.requestBodyWrite(objPaymentModel);
					objPojo.getObjUtilities().logReporter("PaymentReqeust Body : " + paymetRequestStr + "<br><br>",
							true);
					responseString = objRestAssuredCode.restApiHit(BaseURI + endPoint, requestType, jsonFileName);

					objPojo.getObjUtilities()
							.logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>", true);
					objExeclUtil.addRow(objPaymentModel.getPaymentArray());
					transactionReferNoMap.put(objPaymentModel.getMandateType(), PaymentRequestBody.latestReferNo);
					paymentResponseMap.put(objPaymentModel.getMandateType(), objRestAssuredCode.jsonPathEvaluator);
				}

				// objExeclUtil.closeFileWrite();
				for (Map.Entry<String, String> entry : transactionReferNoMap.entrySet())
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
		}
	}

	@Then("I am validating object {string} for NSTP String value {string}.")
	public void i_am_validating_object_for_NSTP_String_value(String arg1, String arg2) {

		if (statusRestAPIFlag) {
			for (Map.Entry<String, JsonPath> entry : paymentResponseMap.entrySet()) {
				JsonPath paymentResponseInJsonPath = entry.getValue();
				responseStatus = paymentResponseInJsonPath.get(arg1);

				System.out.println(responseStatus);
				Assert.assertEquals(arg2, responseStatus.trim());
				if (responseStatus.equalsIgnoreCase(arg2))
					System.out.println("Response status : " + responseStatus + " test data : " + arg2 + " match");
				objPojo.getObjUtilities().logReporter("Verify following tags", arg2, responseStatus.trim(),
						responseStatus.trim().equals(arg2));
			}
		}

	}

	@When("I go to status {string} and {string} NSTP API Request saved in JSON File {string} without charset")
	public void i_go_to_status_and_NSTP_API_Request_saved_in_JSON_File_without_charset(String endPoint,
			String requestType, String jsonFileName, DataTable credentials) {

		if (statusRestAPIFlag) {

			if (paymentRestAPIFlag)
				objCommonCode.staticWait(delayBetPaymentStatus);
			else {

				objExeclUtil.readExcel();
				transactionReferNoMap = objExeclUtil.fetchColumn();
			}
			// objExeclUtil.writeInExcel();
			System.out.println("<@@@======================= Status ========================@@@>");
			List<Map<String, String>> credentialsMapList = credentials.asMaps(String.class, String.class);
			for (Map<String, String> data : credentialsMapList) {

				for (Map.Entry<String, String> entry : transactionReferNoMap.entrySet()) {
					if (data.get("MANDATETYPE").trim().equalsIgnoreCase(entry.getKey())) {
						requestString = objStatusRequestBody.requestBodyWrite(data.get("MANDATETYPE").trim(),
								entry.getValue());
						objPojo.getObjUtilities().logReporter("StatusReqeust : " + requestString, true);
						responseString = objRestAssuredCode.restApiHit(BaseURI + endPoint, requestType, jsonFileName);
						objExeclUtil.addCell(objRestAssuredCode.statusResponseProcess());
						statusResponseMap.put(entry.getValue(), objRestAssuredCode.jsonPathEvaluator);
						objPojo.getObjUtilities().logReporter("<b>StatusReqeust Response :</b>" + responseString, true);
						break;
					}
				}
				for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				}
			}
			objExeclUtil.closeFileWrite();
		}

	}

	@Then("Navigate To Maker RBL Portal")
	public void navigate_to_Maker_rbl_portal() {
		objPojo.getObjUtilities().logReporter("<b>Navigate To Maker RBL Portal</b>",true);
		if (statusRestAPIFlag) {
			BaseTest baseTest = new BaseTest();
			// objPojo = baseTest.initializeWebEnvironment();
			objPojo.getObjWrapperFunctions().loadBaseURL();
		}
	}

	@Then("Login to User Using Following Credentials :")
	public void login_to_User_Using_Following_Credentials(io.cucumber.datatable.DataTable credentials) {
		objPojo.getObjUtilities().logReporter("<b>Login to User Using Following Credentials : </b>",true);
		if (statusRestAPIFlag) {
			List<Map<String, String>> data1 = credentials.asMaps(String.class, String.class);
			for (Map<String, String> data : data1) {
				
			objCommonCode.loginCode(data);	
			}
		}
	}
	
	
	
	@Then("I am going to Check whether above transactions can be processed by checker")
	public void i_am_going_to_Check_whether_above_transactions_can_be_processed_by_checker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether above transactions can be processed by checker</b>",true);
		if (statusRestAPIFlag) {
			// System.out.println("Transaction log : Refer no : " + transactionReferNo);
			objHomePage.clickTransactionOpsCheckerButton();
			objHomePage.clickQueueChecker();
			objExceptionQueueCheckerPage.switchToFrame("myfrm");
			objExceptionQueueCheckerPage.fillAndSubmitCheckerTransactionForm();
			objExceptionQueueCheckerPage.approveTransactionByChecker(objPaymentModel.getTransactionReferNo());
			objExceptionQueueCheckerPage.clickSubmitButton();
			objCommonCode.staticWaitSeconds(3000);
			objExceptionQueueCheckerPage.clickBackButton();
			objExceptionQueueMakerPage.defaultFrame();
			objExceptionQueueMakerPage.clickLogoutButtonM();
			

		}
	}
	
	@Then("I am going to Check whether same  transaction can be authorised again  by checker")
	public void i_am_going_to_Check_whether_same_transaction_can_be_authorised_again_by_checker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether same  transaction can be authorised again  by checker</b>",true);
		objHomePage.clickTransactionOpsCheckerButton();
		objHomePage.clickQueueChecker();
		objExceptionQueueCheckerPage.switchToFrame("myfrm");
		objExceptionQueueCheckerPage.fillAndSubmitCheckerTransactionForm();
		objExceptionQueueCheckerPage.checkTransactionRefer(objPaymentModel.getTransactionReferNo());
		objExceptionQueueMakerPage.defaultFrame();
		objExceptionQueueCheckerPage.clickLogoutButtonM();
	}

	@Then("I am going to Check whether same  transaction can be again initiated by maker")
	public void i_am_going_to_Check_whether_same_transaction_can_be_again_initiated_by_maker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether same  transaction can be again initiated by maker</b>",true);
		objHomePage.clickTransactionOpsMakerButton();
		objHomePage.clickQueueMaker();
		objExceptionQueueCheckerPage.switchToFrame("myfrm");
		objExceptionQueueMakerPage.fillAndSubmitMakerForm();
		objExceptionQueueMakerPage.checkTransactionReferNo(objPaymentModel.getTransactionReferNo());
		objExceptionQueueMakerPage.defaultFrame();
		objExceptionQueueMakerPage.clickLogoutButtonM();
	}
	

	/*@Then("I go to Check whether above transactions can be processed by checker")
	public void i_go_to_Check_whether_above_transactions_can_be_processed_by_checker() {
		if (statusRestAPIFlag) {
			// System.out.println("Transaction log : Refer no : " + transactionReferNo);
			objHomePage.clickTransactionOpsCheckerButton();
			objHomePage.clickQueueChecker();
			objExceptionQueueCheckerPage.switchToFrame("myfrm");
			objExceptionQueueCheckerPage.selectGroup("RGEX-RG ExHouse");
			objExceptionQueueCheckerPage.selectCorporateChecker("AQMF001 - AQMF");
			objExceptionQueueCheckerPage.fillAndSubmitMakerTransactionForm();
			objExceptionQueueCheckerPage.clickSubmitButton();
			objCommonCode.staticWaitSeconds(3000);
			objExceptionQueueCheckerPage.clickBackButton();
			objExceptionQueueMakerPage.defaultFrame();
			objExceptionQueueCheckerPage.clickLogoutButtonM();

		}
	}
*/
	@Then("I am validating object {string} for NSTP Error String value {string}.")
	public void i_am_validating_object_for_NSTP_Error_String_value(String arg1, String arg2) {
		objPojo.getObjUtilities().logReporter("<b>I am validating object "+arg1+" for NSTP Error String value "+arg2+".</b>",true);
		if (statusRestAPIFlag) {
			String validateError = arg2;

			for (Map.Entry<String, JsonPath> entry : paymentResponseMap.entrySet()) {

				if (entry.getKey().equalsIgnoreCase("D")) {
					for (Map.Entry<String, JsonPath> entry1 : statusResponseMap.entrySet()) {

						jsonPathEvaluator = entry1.getValue();
						responseStatus = jsonPathEvaluator.get("ErrorDesc");
						arg2 = "Invalid account";
						// arg2="Account not allowed for credit";
					}

				} else {
					jsonPathEvaluator = entry.getValue();
					responseStatus = jsonPathEvaluator.get(arg1);
					arg2 = validateError;

				}

				System.out.println(responseStatus);
				Assert.assertEquals(arg2, responseStatus.trim());

				if (responseStatus.equalsIgnoreCase(arg2))
					System.out.println("Response status : " + responseStatus + " test data : " + arg2 + " match");
				objPojo.getObjUtilities().logReporter("Verify following tags", arg2, responseStatus.trim(),
						responseStatus.trim().equals(arg2));

			}
		}
	}

	/*
	 * @Then("I am validating object {string}") public void
	 * i_am_validating_object(String string) { for (Map.Entry<String, JsonPath>
	 * entry : statusResponseMap.entrySet()) { String status =
	 * entry.getValue().get(string); System.out.println("Response status : " +
	 * status); } }
	 */
	@Then("I am closing browser")
	public void i_am_closing_browser() {
		Set<String> windows = objPojo.getDriver().getWindowHandles();
		if (windows.size() > 1) {

			objPojo.getDriver().close();
			objPojo.getObjUtilities().logReporter("Fetch application Status", objPojo.getObjWrapperFunctions()
					.switchToWindowContainsURL("https://rbllastmile.remit.in/onlinetransfer/secure/home.jsp#"));
		}

		objPojo.getDriver().close();
	}

	/*public void staticWait(int waitTime) {
		waitTime = waitTime * 60000;
		System.out.println("Static wait : " + waitTime);
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	@When("I want to run payment rest api with {string}, {string} , {string} , {string} , {string} ,{string}")
	public void i_want_to_run_payment_rest_api_with(String mandateType, String amount, String beneficiaryAccountNo,
			String beneficiaryIFSCCode, String beneficiaryAccountHolderName, String remitterName) {
		objPojo.getObjUtilities().logReporter("<b>I want to run payment rest api with "+mandateType+", "+amount+" , "+beneficiaryAccountNo+
				" , "+beneficiaryIFSCCode+" , "+beneficiaryAccountHolderName+" , "+remitterName+"</b>", true);
		if (paymentRestAPIFlag) {

			objPaymentModel.setMandateType(mandateType);
			objPaymentModel.setAmount(amount);
			objPaymentModel.setBeneficiaryAccountNo(beneficiaryAccountNo);
			objPaymentModel.setBeneficiaryIFSCCode(beneficiaryIFSCCode);
			objPaymentModel.setBeneficiaryAccountHolderName(beneficiaryAccountHolderName);
			objPaymentModel.setRemitterName(remitterName);
			paymetRequestStr = objPaymentRequestBody.requestBodyWrite(objPaymentModel);
			objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Body : </b>" + paymetRequestStr + "<br><br>",
					true);
			responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/ADDTRANSACTIONTEST", "post",
					"Payment_I.json");
			objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>",
					true);
			objPaymentModel.setTransactionReferNo(PaymentRequestBody.latestReferNo);
			statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);

		}
	}

	@Then("I am going to run status rest api")
	public void i_am_going_to_run_status_rest_api() {
		if (statusRestAPIFlag) {

			if (paymentRestAPIFlag)
				objCommonCode.staticWait(delayBetPaymentStatus);
		objPojo.getObjUtilities().logReporter("<b>I am going to run status rest api</b>",true);
		statusRequestBody = objStatusRequestBody.requestBodyWrite(objPaymentModel.getMandateType(),
				objPaymentModel.getTransactionReferNo());
		objPojo.getObjUtilities().logReporter("<b>StatusReqeust Body : </b>" + statusRequestBody + "<br><br>", true);
		responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/STATUSCHECKTEST", "Post",
				"Status_I.json");

		statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
		objPojo.getObjUtilities().logReporter("<b>Status response : </b>" + responseString + "<br><br>", true);
		objPaymentModel.setStatusResponse(objRestAssuredCode.jsonPathEvaluator);
		}
	}

	@Then("I am validating jsonobject {string} for NSTP String value {string}.")
	public void i_am_validating_jsonobject_for_NSTP_String_value(String objForJsonPath, String verifyString) {
		objPojo.getObjUtilities().logReporter("<b>I am validating jsonobject "+objForJsonPath+" for NSTP String value "+verifyString+".</b>",true);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+objPaymentModel.getTransactionReferNo(), true);
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		String status = objRestAssuredCode.statusResponseProcess(objPaymentModel.getStatusResponse());
		objCommonCode.compareTo(status, verifyString);
		objPaymentModel.setStatusRestAPIStatus(status);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);
		
	}

	@Then("I check a transaction log inquiry using corporate reference number for NSTP")
	public void i_check_a_transaction_log_inquiry_using_corporate_reference_number_for_NSTP() {
		objPojo.getObjUtilities().logReporter("<b>I check a transaction log inquiry using corporate reference number for NSTP</b>",true);
		if (statusRestAPIFlag) {
			System.out.println("Transaction log : Refer no : " + transactionReferNo);
			objHomePage.clickLogButton();
			objHomePage.clickTransactionLogButton();
			objBankUserDashPage.switchToFrame("myfrm");
			for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {

				String applicationStatus = objCorporateReferNoPage.fillAndSubmitCorporateRefNo(entry1.getKey());
				objCommonCode.compareToMap(entry1.getValue(), applicationStatus);
				//applicationStatusMap.put(entry1.getKey(), applicationStatus);
			}

		}

	
		
	}

	@Then("I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP")
	public void i_check_a_PPS_log_inquiry_of_above_transaction_for_acknowledgement_sent_to_WU_for_NSTP() {

		objPojo.getObjUtilities().logReporter("<b>I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP</b>",true);
		if (statusRestAPIFlag) {
			objHomePage.clickMenuButton();
			
			//objHomePage.clickLogButton();
			objHomePage.clickPPSLogButton();
			objBankUserDashPage.switchToFrame("myfrm");
			for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {
			objPPSFinalRequestNoPage.fillAndSubmitRequestNo(entry1.getKey());
			}
		}
	
		
	}

	@Then("I Check whether the transaction appears in mandate wise Report or transaction count report under MIS for NSTP")
	public void i_Check_whether_the_transaction_appears_in_mandate_wise_Report_or_transaction_count_report_under_MIS_for_NSTP() {

		objPojo.getObjUtilities().logReporter("<b>I Check whether the transaction appears in mandate wise Report transaction count report under MIS for NSTP</b>",true);
		applicationStatusMap=new LinkedHashMap();
		objPojo.getDriver().switchTo().parentFrame();
		objHomePage.clickMISButton();
		objHomePage.clickTransactionInquiryMIS();
		objBankUserDashPage.switchToFrame("myfrm");
		
		for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {
			applicationStatus=objTransactionInquiryPage.fillTransactionInquiry(entry1.getKey());
			objCommonCode.compareToMap(entry1.getValue(), applicationStatus);
			applicationStatusMap.put(entry1.getKey(), applicationStatus);
		}
	
		
	}

	@Then("I go to Transaction Dashborad for validating response status with application status for NSTP")
	public void i_go_to_Transaction_Dashborad_for_validating_response_status_with_application_status_for_NSTP() {

		objPojo.getObjUtilities().logReporter("<b>I go to Transaction Dashborad for validating response status with application status</b>",true);
		if (statusRestAPIFlag) {
			//// if(responseStatus.equalsIgnoreCase("Success")||responseStatus.equalsIgnoreCase("Initiated"))
			//// {
			//objHomePage.clickMenuButton();
			objPojo.getDriver().switchTo().parentFrame();
			objHomePage.clickMenuButton();
			objHomePage.checkMISButton();
			objHomePage.clickTransactionDashButton();
			objBankUserDashPage.switchToFrame("myfrm");
			objBankUserDashPage.selectCorporate("AQMF001");
			objBankUserDashPage.selectFrequency("Today");
			objBankUserDashPage.clickSubmitButton();
			System.out.println("Parent Window : "+objPojo.getDriver().getWindowHandle());
			for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {
				
				for (Map.Entry<String, String> entry : applicationStatusMap.entrySet()) {
				if(entry.getKey().equalsIgnoreCase(entry1.getKey())) {
					
					objStatusDashPage.clickStatusLink(entry.getValue());
					
					System.out.println("Transaction ReferNo : "+entry.getKey()+" status : "+entry.getValue());
				applicationStatus = objStatusDashPage.fetchedTransactionStatus(entry1.getKey(),entry1.getValue());
				System.out.println("Response status : " + responseStatus + " Application status : " + applicationStatus);
				/*Assert.assertEquals(responseStatus, applicationStatus);
				objPojo.getObjUtilities().logReporter("Verify following tags", responseStatus, applicationStatus,
						applicationStatus.equals(responseStatus));*/
				
				break;
				}
				}
				objCommonCode.compareToMap(entry1.getValue(), applicationStatus);
			}
		}
	
		
	}
	

	@Then("I am going to Check whether above transactions can be processed by maker")
	public void i_am_going_to_Check_whether_above_transactions_can_be_processed_by_maker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether above transactions can be processed by maker</b>", true);
		if (statusRestAPIFlag) {
			// System.out.println("Transaction log : Refer no : " + transactionReferNo);
			objHomePage.clickTransactionOpsMakerButton();
			objHomePage.clickQueueMaker();
			objExceptionQueueMakerPage.switchToFrame("myfrm");
			objExceptionQueueMakerPage.fillAndSubmitMakerForm();
			objCommonCode.staticWait(2);
			objExceptionQueueMakerPage.clickFormSubmitButton1();
			objExceptionQueueMakerPage.fillAndSubmitMakerTransactionForm(objPaymentModel.getTransactionReferNo());
			objExceptionQueueMakerPage.clickSubmitButton();
			objExceptionQueueMakerPage.acceptPopup();
			//objCommonCode.staticWait(0);
			objExceptionQueueMakerPage.clickBackButton();
			objExceptionQueueMakerPage.defaultFrame();
			objExceptionQueueMakerPage.clickLogoutButtonM();
			
			

		}
	}
	
	
	@When("I want to run payment rest api with {string}, {string} , {string} , {string} , {string} ,{string} to check technical eror")
	public void i_want_to_run_payment_rest_api_with_to_check_technical_eror(String mandateType, String amount, String beneficiaryAccountNo,
			String beneficiaryIFSCCode, String beneficiaryAccountHolderName, String remitterName) {

		objPojo.getObjUtilities().logReporter("<b>I want to run payment rest api with "+mandateType+", "+amount+" , "+beneficiaryAccountNo+
				" , "+beneficiaryIFSCCode+" , "+beneficiaryAccountHolderName+" , "+remitterName+"</b>", true);
		if (paymentRestAPIFlag) {
			technicalError=true;
			objPaymentModel.setMandateType(mandateType);
			objPaymentModel.setAmount(amount);
			objPaymentModel.setBeneficiaryAccountNo(beneficiaryAccountNo);
			objPaymentModel.setBeneficiaryIFSCCode(beneficiaryIFSCCode);
			objPaymentModel.setBeneficiaryAccountHolderName(beneficiaryAccountHolderName);
			objPaymentModel.setRemitterName(remitterName);
			paymetRequestStr = objPaymentRequestBody.requestBodyWrite(objPaymentModel);
			objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Body : </b>" + paymetRequestStr+ "<br><br>",
					true);
			responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/ADDTRANSACTIONTEST", "post",
					"Payment_I.json");
			objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>",
					true);
			objPaymentModel.setTransactionReferNo(PaymentRequestBody.latestReferNo);
			objPaymentModel.setPaymentResponse(objRestAssuredCode.jsonPathEvaluator);
			statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
			technicalError=false;
			
		}
	
	}

		
	@When("I want to run payment rest api with {string}, {string} , {string} , {string} , {string} ,{string} for duplicate")
	public void i_want_to_run_payment_rest_api_with_for_duplicate(String mandateType, String amount, String beneficiaryAccountNo,
			String beneficiaryIFSCCode, String beneficiaryAccountHolderName, String remitterName) {
		PaymentModel.duplicateFlag = true;
		objPojo.getObjUtilities().logReporter("<b>I want to run payment rest api with "+mandateType+", "+amount+" , "+beneficiaryAccountNo+
				" , "+beneficiaryIFSCCode+" , "+beneficiaryAccountHolderName+" , "+remitterName+"</b>", true);
		if (paymentRestAPIFlag) {
			
			objPaymentModel.setMandateType(mandateType);
			objPaymentModel.setAmount(amount);
			objPaymentModel.setBeneficiaryAccountNo(beneficiaryAccountNo);
			objPaymentModel.setBeneficiaryIFSCCode(beneficiaryIFSCCode);
			objPaymentModel.setBeneficiaryAccountHolderName(beneficiaryAccountHolderName);
			objPaymentModel.setRemitterName(remitterName);
			paymetRequestStr = objPaymentRequestBody.requestBodyWrite(objPaymentModel);
			objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Body : </b>" + paymetRequestStr + "<br><br>",
					true);
			responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/ADDTRANSACTIONTEST", "post",
					"Payment_I.json");
			objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>",
					true);
			objPaymentModel.setTransactionReferNo(PaymentRequestBody.latestReferNo);
			statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
			objPaymentModel.setPaymentResponse(objRestAssuredCode.jsonPathEvaluator);
		}
		PaymentModel.duplicateFlag = false;
		RBLStepDefinition.statusRestAPIFlag=statusRestAPIFlag;
		RBLStepDefinition.paymentRestAPIFlag=paymentRestAPIFlag;
		RBLStepDefinition.objPaymentModel=objPaymentModel;
	}
	
	@Then("I am validating jsonobject {string} for error  String value {string}.")
	public void i_am_validating_jsonobject_for_error_String_value(String objForJsonPath, String verifyString) {
		objPojo.getObjUtilities().logReporter("<b>I am validating jsonobject "+objForJsonPath+" for error in NSTP String value "+verifyString+".</b>",true);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+objPaymentModel.getTransactionReferNo(), true);
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		String status = objRestAssuredCode.fetchError(objForJsonPath,verifyString,objPaymentModel.getPaymentResponse());
		
		objPaymentModel.setStatusRestAPIStatus(status);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);
		objPojo.getObjUtilities().logReporter("<br><br>",true);
	
	}
	
	
	@Then("I am going to execute status rest api")
	public void i_am_going_to_execute_status_rest_api() {
		if (statusRestAPIFlag) {
			
			if (paymentRestAPIFlag) {
				if(objPaymentModel.getMandateType().equalsIgnoreCase("R") && objPaymentModel.getRemitterName().equalsIgnoreCase("Dasi"))
					objCommonCode.staticWait(30);
				else
					objCommonCode.staticWait(delayBetPaymentStatus);
			}

		objPojo.getObjUtilities().logReporter("<b>I am going to run status rest api</b>",true);
		statusRequestBody = objStatusRequestBody.requestBodyWrite(objPaymentModel.getMandateType(),
				objPaymentModel.getTransactionReferNo());
		objPojo.getObjUtilities().logReporter("<b>StatusReqeust Body : </b>" + statusRequestBody + "<br><br>", true);
		responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/STATUSCHECKTEST", "Post",
				"Status_I.json");

		statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
		objPojo.getObjUtilities().logReporter("<b>Status response : </b>" + responseString + "<br><br>", true);
		objPaymentModel.setStatusResponse(objRestAssuredCode.jsonPathEvaluator);
		}
	
	   
	}

	@Then("I am going to Check whether same  transaction can be again initiated by another maker")
	public void i_am_going_to_Check_whether_same_transaction_can_be_again_initiated_by_another_maker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether same  transaction can be again initiated by another maker</b>",true);
		objHomePage.clickTransactionOpsMakerButton();
		objHomePage.clickQueueMaker();
		objExceptionQueueMakerPage.switchToFrame("myfrm");
		objExceptionQueueMakerPage.fillAndSubmitMakerForm();
		objExceptionQueueMakerPage.checkTransactionReferNo(objPaymentModel.getTransactionReferNo());
		objExceptionQueueMakerPage.defaultFrame();
		objExceptionQueueMakerPage.clickLogoutButtonM();
	}
	
	@Then("I am going to Check whether same  transaction can be again authorise by checker")
	public void i_am_going_to_Check_whether_same_transaction_can_be_again_authorise_by_checker() {
		objPojo.getObjUtilities().logReporter("<b>I am going to Check whether same  transaction can be again authorise by checker</b>",true);
		objHomePage.clickTransactionOpsCheckerButton();
		objHomePage.clickQueueChecker();
		objExceptionQueueCheckerPage.switchToFrame("myfrm");
		objExceptionQueueCheckerPage.fillAndSubmitCheckerTransactionForm();
		objExceptionQueueCheckerPage.checkTransactionRefer(objPaymentModel.getTransactionReferNo());
		objExceptionQueueMakerPage.defaultFrame();
		objExceptionQueueCheckerPage.clickLogoutButtonM();
	}
	
	@Then("I am going to validating response")
	public void i_am_going_to_validating_response() {

		objPojo.getObjUtilities().logReporter("<b>I am going to validating response.</b>",true);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+objPaymentModel.getTransactionReferNo(), true);
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		String status = objRestAssuredCode.statusResponseProcess(objPaymentModel.getStatusResponse());
		if(objPaymentModel.getRemitterName().equalsIgnoreCase("Chit"))
			objCommonCode.compareToMap("Hold", status);
		else
			objCommonCode.compareToMap(status,"Success");
		objPaymentModel.setStatusRestAPIStatus(status);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);
		objPojo.getObjUtilities().logReporter("<br><br>",true);
	
	
	}
	
	@When("I want to execute {string} for {string}")
	public void i_want_to_execute_for(String testcaseName, String mandateType) {
		this.testCaseName=testcaseName;
		objPojo.getObjUtilities().logReporter("<b>Check whether a "+mandateType+" transaction with "+testcaseName+"</b><br>",true);
	}

	@Then("I am going to validating error massage in response")
	public void i_am_going_to_validating_error_massage_in_response() {
		objPojo.getObjUtilities().logReporter("<b>I am going to validating error massage in response</b>",true);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+objPaymentModel.getTransactionReferNo(), true);
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		String status = objRestAssuredCode.errorForCautionWord(testCaseName, objPaymentModel.getStatusResponse());
		//objCommonCode.compareToMap("Success", status);
		objPaymentModel.setStatusRestAPIStatus(status);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);
		objPojo.getObjUtilities().logReporter("<br><br>",true);
	}
	
	@Then("I am going to compare duplicate response status with normal response")
	public void i_am_going_to_compare_duplicate_response_status_with_normal_response() {
		statusResponseStatusMap=new LinkedHashMap<>();
		objRestAssuredCode.fetchError("[0].ErrorDesc","Duplicate Transaction Id",objPaymentModel.getPaymentResponse());
		objPojo.getObjUtilities().logReporter("<br><br>",true);
	    String status = objRestAssuredCode.statusResponseProcess(objPaymentModel.getStatusResponse());
		statusRequestBody = objStatusRequestBody.requestBodyWrite(objPaymentModel.getMandateType(),
				objPaymentModel.getTransactionReferNo());
		objPojo.getObjUtilities().logReporter("<b>StatusReqeust Body : </b>" + statusRequestBody + "<br><br>", true);
		responseString = objRestAssuredCode.restApiHit(baseUrl + "/ILMAPI/LMAPI/STATUSCHECKTEST", "Post","Status_I.json");

		statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
		objPojo.getObjUtilities().logReporter("<b>Status response : </b>" + responseString + "<br><br>", true);
		objPaymentModel.setStatusResponse(objRestAssuredCode.jsonPathEvaluator);
		String duplicateResponseStatus = objRestAssuredCode.statusResponseProcess(objPaymentModel.getStatusResponse());
		objCommonCode.compareTwoString(status, duplicateResponseStatus);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), duplicateResponseStatus);
	}
	
	@Then("I am going to validate duplicate response status with normal response")
	public void i_am_going_to_validate_duplicate_response_status_with_normal_response() {
		objRestAssuredCode.fetchError("[0].ErrorDesc","Duplicate Transaction Id",objPaymentModel.getPaymentResponse());
	}
	
	@Then("I am going to validating error")
	public void i_am_going_to_validating_error() {
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		objPojo.getObjUtilities().logReporter("Transaction Refer No : "+objPaymentModel.getTransactionReferNo()+" MandateType : "+objPaymentModel.getMandateType(),true);
	   String status=objRestAssuredCode.errorForInvalidAccount(objPaymentModel.getMandateType(), objPaymentModel.getStatusResponse());
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);

	}
	
	@Then("I am going to validating status API response")
	public void i_am_going_to_validating_status_API_response() {

		objPojo.getObjUtilities().logReporter("<b>I am going to validating status API response.</b>",true);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+objPaymentModel.getTransactionReferNo(), true);
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		String status = objRestAssuredCode.statusResponseProcess(objPaymentModel.getStatusResponse());
		
			objCommonCode.compareToMap(status,"Success");
		objPaymentModel.setStatusRestAPIStatus(status);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);
		objPojo.getObjUtilities().logReporter("<br><br>",true);
	
	
	
	}
	
	@Then("I am validating status response for this jsonobject {string} for error  String value {string}.")
	public void i_am_validating_status_response_for_this_jsonobject_for_error_String_value(String objForJsonPath, String verifyString) {

		objPojo.getObjUtilities().logReporter("<b>I am validating status response for this jsonobject "+objForJsonPath+" for error in NSTP String value "+verifyString+".</b>",true);
		objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+objPaymentModel.getTransactionReferNo(), true);
		statusResponseStatusMap = new LinkedHashMap<String, String>();
		String errorMsg = objRestAssuredCode.fetchError(objForJsonPath,verifyString,objPaymentModel.getStatusResponse());
		String status=objRestAssuredCode.statusResponseProcess(objPaymentModel.getStatusResponse());
		objCommonCode.compareTo(errorMsg, verifyString);
		objPaymentModel.setStatusRestAPIStatus(status);
		statusResponseStatusMap.put(objPaymentModel.getTransactionReferNo(), status);
		objPojo.getObjUtilities().logReporter("<br><br>",true);
	
	
	}


	@Then("I am closing browser for NSTP")
	public void i_am_closing_browser_for_NSTP() {
		numberTimesDriverClose++;
		System.out.println("Number of times Close browser : "+numberTimesDriverClose);
		objPojo.getDriver().quit();
	
	}

}
