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

public class RBLStepDefinition {

	private String BaseURI;

	private Response response;
	 Pojo objPojo;

	private JsonPath jsonPathEvaluator;

	private String testData;
	private LoginPage objLoginPage;
	private HomePage objHomePage;
	private BankUserDashboardPage objBankUserDashPage;
	private StatusDashboardPage objStatusDashPage;
	private CorporateReferenceNoPage objCorporateReferNoPage;
	private WUPPSFinalRequestNumberPage objPPSFinalRequestNoPage;
	private TransactionInquiryPage objTransactionInquiryPage;
	public static  PaymentModel objPaymentModel;
	private String transactionReferencesNo;
	private String applicationStatus;
	private String responseStatus;
	private String responseString;
	private StatusRequestBody objStatusRequestBody;
	private StatusRestAPI objStatusRestAPI;
	private RestAssuredCode objRestAssuredCode;
	private PaymentRequestBody objPaymentRequestBody;
	private CommonCode objCommonCode;
	public static Map<String, String> transactionReferNoMap;
	public static Map<String, JsonPath> statusResponseMap;
	private static Map<String, JsonPath> paymentResponseMap;
	public static Map<String, String> statusResponseStatusMap;
	private static Map<String,String> applicationStatusMap;
	public static Map<String,String> mandateTypeTransactionReferMap;
	public static boolean technicalEror=false;
	Properties prop;
	private String transactionReferNo;
	private ExcelUtil objExeclUtil;

	public static Integer delayBetPaymentStatus;

	public static Boolean statusRestAPIFlag;

	public static  Boolean paymentRestAPIFlag;

	private String paymetRequestStr;

	private String statusRequestBody;

	public RBLStepDefinition(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objLoginPage = new LoginPage(objPojo);
		objHomePage = new HomePage(objPojo);
		objStatusRestAPI = new StatusRestAPI();
		objBankUserDashPage = new BankUserDashboardPage(objPojo);
		objStatusDashPage = new StatusDashboardPage(objPojo);
		objCorporateReferNoPage = new CorporateReferenceNoPage(objPojo);
		objPPSFinalRequestNoPage = new WUPPSFinalRequestNumberPage(objPojo);
		objTransactionInquiryPage=new TransactionInquiryPage(objPojo);
		objStatusRequestBody = new StatusRequestBody();
		objRestAssuredCode = new RestAssuredCode(objPojo);

		objPaymentRequestBody = new PaymentRequestBody();
		statusResponseMap = new LinkedHashMap();
		objPaymentModel = new PaymentModel();
		prop = new Properties();
		objCommonCode = new CommonCode(objPojo);
	}

	@Given("I am requesting {string}.")
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
		BaseURI = prop.getProperty("BaseURI");
				
		System.out.println("Base Url in step definition : "+BaseURI);
		statusRestAPIFlag = Boolean.valueOf(prop.getProperty("StatusRestAPI"));
		paymentRestAPIFlag = Boolean.valueOf(prop.getProperty("PaymentRestAPI"));
		delayBetPaymentStatus = Integer.valueOf(prop.getProperty("waitTime"));
		objExeclUtil = new ExcelUtil();
	}

	@When("I go to {string} and {string} API Request saved in JSON File {string} without charset")
	public void i_go_to_and_API_Request_saved_in_JSON_File_without_charset(String string, String string2,
			String string3) {

		/*
		 * try { Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T"); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */

		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\" + string3;

		String file = Utilities.readLineByLineJava8(jsonFilePath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.println(BaseURI + string);
		RequestSpecification httpRequest = RestAssured.given().config(config).accept("application/json")
				// .header("Content-Type", "application/json").header("Media Type",
				// "application/json")
				.body(file).log().all();
		String P = "Post";
		String P1 = "Put";
		String D = "Delete";
		String G = "Get";

		if (P.equalsIgnoreCase(string2)) {
			response = httpRequest.post(BaseURI + string);
		} else if (P1.equalsIgnoreCase(string2)) {
			response = httpRequest.put(BaseURI + string);
		} else if (D.equalsIgnoreCase(string2)) {
			response = httpRequest.delete(BaseURI + string);
		} else if (G.equalsIgnoreCase(string2)) {
			response = httpRequest.get(BaseURI + string);
		}

		System.out.println("Status : " + response.getStatusCode());
		// System.out.println(response.headers());
		System.out.println("Response : " + response.asString());
		jsonPathEvaluator = response.jsonPath();

	}

	@Then("I am validating object {string} for String value {string}.")
	public void i_am_validating_object_for_String_value(String arg1, String arg2) {
		if (statusRestAPIFlag) {
			for (Map.Entry<String, JsonPath> entry : paymentResponseMap.entrySet()) {
				JsonPath paymentResponseInJsonPath = entry.getValue();
				// responseStatus = paymentResponseInJsonPath.get(arg1);
				responseStatus = objRestAssuredCode.paymentResponseProcess(arg1);

				System.out.println(responseStatus);
				Assert.assertEquals(arg2, responseStatus.trim());
				if (responseStatus.equalsIgnoreCase(arg2))
					System.out.println("Response status : " + responseStatus + " test data : " + arg2 + " match");
				objPojo.getObjUtilities().logReporter("Verify following tags", arg2, responseStatus.trim(),
						responseStatus.trim().equals(arg2));
			}
		}
	}

	@Then("I am validating object {string} for Error String value {string}.")
	public void i_am_validating_object_for_Error_String_value(String arg1, String arg2) {
		if (statusRestAPIFlag) {
			String validateError = arg2;
			for (Map.Entry<String, JsonPath> entry : paymentResponseMap.entrySet()) {
				
				if (entry.getKey().equalsIgnoreCase("D")) {
					for (Map.Entry<String, JsonPath> entry1 : statusResponseMap.entrySet()) {

						jsonPathEvaluator = entry1.getValue();
						responseStatus = jsonPathEvaluator.get("ErrorDesc");
						arg2 = "Invalid account";
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

	@Then("I am fetch Status response")
	public void i_am_fetch_Status_response() {
		if (statusRestAPIFlag) {
			statusResponseStatusMap = new LinkedHashMap<String, String>();

			for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
				JsonPath statusResponseInJsonPath = entry.getValue();
				responseStatus = objRestAssuredCode.statusResponseProcess(statusResponseInJsonPath);
				statusResponseStatusMap.put(entry.getKey(), responseStatus);
			}

		}
	}

	@Then("Navigate To Specific RBL Portal")
	public void navigate_to_specific_rbl_portal() {
		objPojo.getObjUtilities().logReporter("<b>Navigate To Specific RBL Portal</b>",true);
		if (statusRestAPIFlag) {
			//// if(responseStatus.equalsIgnoreCase("Success")||responseStatus.equalsIgnoreCase("Initiated"))
			//// {
			// BaseTest baseTest = new BaseTest();
			// objPojo = baseTest.initializeWebEnvironment();
			objPojo.getObjWrapperFunctions().loadBaseURL();
		}
	}
	// }

	@Then("Login to User Admin Using Following Credentials :")
	public void login_to_User_Admin_Using_Following_Credentials(io.cucumber.datatable.DataTable credentials) {
		if (statusRestAPIFlag) {
			
			List<Map<String, String>> data1 = credentials.asMaps(String.class, String.class);
			for (Map<String, String> data : data1) {

				objCommonCode.loginCode(data);
				}
			
		}
	}
	

	@Then("I go to {string}")
	public void i_go_to(String string) {

		objHomePage.clickMISButton();
		objHomePage.clickTransactionDashButton();
	}

	@Then("I go to Bank User Dashboard")
	public void i_go_to_Bank_User_Dashboard() {
		//// if(responseStatus.equalsIgnoreCase("Success")||responseStatus.equalsIgnoreCase("Initiated"))
		//// {
		objBankUserDashPage.switchToFrame("myfrm");
		objBankUserDashPage.selectCorporate("AQMF001");
		objBankUserDashPage.selectFrequency("Today");
		objBankUserDashPage.clickSubmitButton();
	}
	// }

	@Then("I am validating response status with application status.")
	public void i_am_validating_response_status_with_application_status() {
		
		objStatusDashPage.clickTransactionBooked();
		applicationStatus = objStatusDashPage.fetchedTransactionStatus(transactionReferencesNo, "Sent to Authorizer");
		System.out.println("Response status : " + responseStatus + " Application status : " + applicationStatus);
		Assert.assertEquals(responseStatus, applicationStatus);
		objPojo.getObjUtilities().logReporter("Verify following tags", responseStatus, applicationStatus,
				applicationStatus.equals(responseStatus));
	}

	@Then("I am close browser")
	public void i_am_close_browser() {
		Set<String> windows = objPojo.getDriver().getWindowHandles();
		/*if (windows.size() > 1) {

			objPojo.getDriver().close();
			//objPojo.getObjUtilities().logReporter("Fetch application Status", objPojo.getObjWrapperFunctions()
					//.switchToWindowContainsURL("https://rbllastmile.remit.in/onlinetransfer/secure/home.jsp#"));
		}
*/
		objPojo.getDriver().quit();
	}

	@When("I want to {string} and {string} API Request saved in JSON File {string} without charset")
	public void i_want_to_and_API_Request_saved_in_JSON_File_without_charset(String endPoint, String requestType,
			String jsonFileName, DataTable credentials) {
		transactionReferNoMap = new LinkedHashMap();
		
		if (paymentRestAPIFlag) {
			paymentResponseMap = new LinkedHashMap();
			List<Map<String, String>> credentialsMapList = credentials.asMaps(String.class, String.class);
			objExeclUtil.writeInExcel();
			if (jsonFileName.equalsIgnoreCase("Payment_I.json")||jsonFileName.equalsIgnoreCase("TechnicalError.json")) {
				for (Map<String, String> data : credentialsMapList) {
					objPaymentModel.setMandateType(data.get("MANDATETYPE").trim());
					objPaymentModel.setAmount(data.get("AMOUNT").trim());
					objPaymentModel.setBeneficiaryAccountNo(data.get("BENEFICIARYACCOUNTNO").trim());
					objPaymentModel.setBeneficiaryIFSCCode(data.get("BENEFICIARYIFSC").trim());
					objPaymentModel.setBeneficiaryAccountHolderName(data.get("BENEFICIARYACCOUNTHOLDERNAME").trim());
					objPaymentModel.setRemitterName(data.get("REMITTERNAME").trim());
					if(jsonFileName.equalsIgnoreCase("TechnicalError.json"))
						technicalEror=true;
					paymetRequestStr = objPaymentRequestBody.requestBodyWrite(objPaymentModel);
					
					objPojo.getObjUtilities().logReporter("<b>PaymentReqeust Body : </b>" + paymetRequestStr + "<br><br>",
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

	@When("I go to status {string} and {string} API Request saved in JSON File {string} without charset")
	public void i_go_to_status_and_API_Request_saved_in_JSON_File_without_charset(String endPoint, String requestType,
			String jsonFileName, DataTable credentials) {
		
		if (statusRestAPIFlag) {

			if (paymentRestAPIFlag)
				objCommonCode.staticWait(delayBetPaymentStatus);
			else {

				objExeclUtil.readExcel();
				transactionReferNoMap = objExeclUtil.fetchColumn();
			}
			//objExeclUtil.writeInExcel();
			System.out.println("<@@@======================= Status ========================@@@>");
			List<Map<String, String>> credentialsMapList = credentials.asMaps(String.class, String.class);
			for (Map<String, String> data : credentialsMapList) {

				for (Map.Entry<String, String> entry : transactionReferNoMap.entrySet()) {
					if (data.get("MANDATETYPE").trim().equalsIgnoreCase(entry.getKey())) {
						statusRequestBody=objStatusRequestBody.requestBodyWrite(data.get("MANDATETYPE").trim(), entry.getValue());
						objPojo.getObjUtilities().logReporter("<b>StatusReqeust Body : </b>" +statusRequestBody+ "<br><br>",true);
						responseString = objRestAssuredCode.restApiHit(BaseURI + endPoint, requestType, jsonFileName);
						//objExeclUtil.addCell(objRestAssuredCode.statusResponseProcess());
						
						statusResponseMap.put(entry.getValue(), objRestAssuredCode.jsonPathEvaluator);
						objPojo.getObjUtilities().logReporter("<b>Status response : </b>" +responseString+ "<br><br>",true);
						break;
					}
				}
				for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				}
			}
			//if(PaymentModel.duplicateFlag==false)
				objExeclUtil.closeFileWrite();
		}
		//PaymentModel.duplicateFlag = false;
	}

	@Then("I am validating object {string}")
	public void i_am_validating_object(String string) {
		for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
			String status = entry.getValue().get(string);
			System.out.println("Response status : " + status);
		}
	}

	@When("I want to payment api with {string},{string},{string},{string} option without charset")
	public void i_want_to_payment_api_with_option_without_charset(String mandateType, String amount,
			String beneficiaryAccountNo, String beneficiaryIFSCCode) {

		objPaymentModel.setMandateType(mandateType);
		objPaymentModel.setAmount(amount);
		objPaymentModel.setBeneficiaryAccountNo(beneficiaryAccountNo);
		objPaymentModel.setBeneficiaryIFSCCode(beneficiaryIFSCCode);

		objPaymentRequestBody.requestBodyWrite(objPaymentModel);
		objRestAssuredCode.restApiHit(BaseURI + "/ILMAPI/LMAPI/ADDTRANSACTIONTEST", "Post", "Payment_I.json");
		transactionReferNoMap.put(mandateType, PaymentRequestBody.latestReferNo);
		String[] paymentResponseArray = { mandateType, amount, beneficiaryAccountNo, beneficiaryIFSCCode,
				PaymentRequestBody.latestReferNo };
		objExeclUtil.writeInExcel();
		objExeclUtil.addRow(paymentResponseArray);
		objExeclUtil.closeFileWrite();
		//objPojo.getObjWrapperFunctions().staticWait();
	}

	@Then("I want to status api with {string} option without charset")
	public void i_want_to_status_api_with_option_without_charset(String mandateType) {
		System.out.println(
				"=================================================================@@@@@@@@@>  Status rest api");
		for (Map.Entry<String, String> entry : transactionReferNoMap.entrySet()) {
			if (mandateType.trim().equalsIgnoreCase(entry.getKey())) {
				objCommonCode.staticWait(1);
				transactionReferNo = entry.getValue();
				objStatusRequestBody.requestBodyWrite(mandateType.trim(), entry.getValue());
				responseString = objRestAssuredCode.restApiHit(BaseURI + "/ILMAPI/LMAPI/STATUSCHECKTEST", "Post",
						"Status_I.json");
				statusResponseMap.put(entry.getValue(), objRestAssuredCode.jsonPathEvaluator);
				break;
			}
		}
	}

	@When("I want to {string} and {string} API Request saved in JSON File {string} with duplicate data")
	public void i_want_to_and_API_Request_saved_in_JSON_File_with_duplicate_data(String endPoint, String requestType,
			String jsonFileName, io.cucumber.datatable.DataTable credentials) {
		//staticWait(5);
		if (paymentRestAPIFlag) {
			paymentResponseMap = new LinkedHashMap();
			List<Map<String, String>> credentialsMapList = credentials.asMaps(String.class, String.class);
			PaymentModel.duplicateFlag = true;
			//objExeclUtil.writeInExcel();
			// Map<String,String> duplicateReferNoMap=objExeclUtil.fetchColumn();
			for (Map<String, String> data : credentialsMapList) {
				objPaymentModel.setMandateType(data.get("MANDATETYPE").trim());
				objPaymentModel.setAmount(data.get("AMOUNT").trim());
				objPaymentModel.setBeneficiaryAccountNo(data.get("BENEFICIARYACCOUNTNO").trim());
				objPaymentModel.setBeneficiaryIFSCCode(data.get("BENEFICIARYIFSC").trim());

				for (Map.Entry<String, String> entry : transactionReferNoMap.entrySet()) {
					if (entry.getKey().equalsIgnoreCase(objPaymentModel.getMandateType())) {
						objPaymentModel.setTransactionReferNo(entry.getValue());
						paymetRequestStr = objPaymentRequestBody.requestBodyWrite(objPaymentModel);
						objPojo.getObjUtilities().logReporter("PaymentReqeust Body : " + paymetRequestStr + "<br><br>",
								true);
						responseString = objRestAssuredCode.restApiHit(BaseURI + endPoint, requestType, jsonFileName);

						objPojo.getObjUtilities()
								.logReporter("<b>PaymentReqeust Response :</b>" + responseString + "<br><br>", true);
					}
				}
				// transactionReferNoMap.put(objPaymentModel.getMandateType(),
				// PaymentRequestBody.latestReferNo);
				paymentResponseMap.put(objPaymentModel.getMandateType(),objRestAssuredCode.jsonPathEvaluator);

				
			}

			// objExeclUtil.closeFileWrite();
			for (Map.Entry<String, String> entry : transactionReferNoMap.entrySet())
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		PaymentModel.duplicateFlag = false;
	}

	@Then("I check a transaction log inquiry using corporate reference number")
	public void i_check_a_transaction_log_inquiry_using_corporate_reference_number() {
		objPojo.getObjUtilities().logReporter("<b>I check a transaction log inquiry using corporate reference number</b>",true);
		if (statusRestAPIFlag) {
			//// if(responseStatus.equalsIgnoreCase("Success")||responseStatus.equalsIgnoreCase("Initiated"))
			//// {
			//applicationStatusMap=new LinkedHashMap();
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

	@Then("I check a PPS log inquiry of above transaction, for acknowledgement sent to WU")
	public void i_check_a_PPS_log_inquiry_of_above_transaction_for_acknowledgement_sent_to_WU() {
		objPojo.getObjUtilities().logReporter("<b>I check a PPS log inquiry of above transaction, for acknowledgement sent to WU</b>",true);
		if (statusRestAPIFlag) {
			
			//// if(responseStatus.equalsIgnoreCase("Success")||responseStatus.equalsIgnoreCase("Initiated"))
			//// {
			objHomePage.clickMenuButton();
			
			//objHomePage.clickLogButton();
			objHomePage.clickPPSLogButton();
			objBankUserDashPage.switchToFrame("myfrm");
			for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {
			objPPSFinalRequestNoPage.fillAndSubmitRequestNo(entry1.getKey());
			}
		}
	}
	// }

	@Then("I go to Transaction Dashborad for validating response status with application status")
	public void i_go_to_Transaction_Dashborad_for_validating_response_status_with_application_status() {
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
				objCommonCode.printTransactionReferNo(entry1.getKey());
			}
		}
	}
	
	
	
	@Then("I Check whether the transaction appears in mandate wise Report or transaction count report under MIS")
	public void i_Check_whether_the_transaction_appears_in_mandate_wise_Report_or_transaction_count_report_under_MIS() {
		objPojo.getObjUtilities().logReporter("<b>I Check whether the transaction appears in mandate wise Report transaction count report under MIS</b>",true);
		applicationStatusMap=new LinkedHashMap();
		objPojo.getDriver().switchTo().parentFrame();
		objHomePage.clickMISButton();
		objHomePage.clickTransactionInquiryMIS();
		objBankUserDashPage.switchToFrame("myfrm");
		
		for (Map.Entry<String, String> entry1 : statusResponseStatusMap.entrySet()) {
			applicationStatus=objTransactionInquiryPage.fillTransactionInquiry(entry1.getKey());
			objCommonCode.compareToMap(entry1.getValue(), applicationStatus);
			//objCommonCode.printTransactionReferNo(entry1.getKey());
			applicationStatusMap.put(entry1.getKey(), applicationStatus);
		}
	}
	

	@When("resouerse {string} and {string}")
	public void resouerse_and(String string, String string2) {
	    objPojo.getObjUtilities().logReporter("Name : "+string+" clinet name : "+string2, true);
	}
	
	@Then("I am validating object {string} for Error for Mandate D String value {string}.")
	public void i_am_validating_object_for_Error_for_Mandate_D_String_value(String arg1, String arg2) {
		if (statusRestAPIFlag) {
			//String validateError=arg2;
			for (Map.Entry<String, JsonPath> entry : paymentResponseMap.entrySet()) {
				
				if (entry.getKey().equalsIgnoreCase("D"))
				{
					for (Map.Entry<String, JsonPath> entry1 : statusResponseMap.entrySet()) {
						
							jsonPathEvaluator=entry1.getValue();
							responseStatus = jsonPathEvaluator.get("ErrorDesc");
							
						}
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

	@Then("I am going to run status rest api for STP")
	public void i_am_going_to_run_status_rest_api_for_STP() {
		if (statusRestAPIFlag) {
			objPaymentModel=RBLStepDefinitionNSTP.objPaymentModel;
			if (paymentRestAPIFlag)
				objCommonCode.staticWait(delayBetPaymentStatus);


		objPojo.getObjUtilities().logReporter("<b>I am going to run status rest api</b>",true);
		statusRequestBody = objStatusRequestBody.requestBodyWrite(objPaymentModel.getMandateType(),
				objPaymentModel.getTransactionReferNo());
		objPojo.getObjUtilities().logReporter("<b>StatusReqeust Body : </b>" + statusRequestBody + "<br><br>", true);
		responseString = objRestAssuredCode.restApiHit(BaseURI + "/ILMAPI/LMAPI/STATUSCHECKTEST", "Post",
				"Status_I.json");

		statusResponseMap.put(objPaymentModel.getTransactionReferNo(), objRestAssuredCode.jsonPathEvaluator);
		objPojo.getObjUtilities().logReporter("<b>Status response : </b>" + responseString + "<br><br>", true);
		objPaymentModel.setStatusResponse(objRestAssuredCode.jsonPathEvaluator);
		}
	}
	
	@Then("I am validating error massage")
	public void i_am_validating_error_massage() {
		objPojo.getObjUtilities().logReporter("<b>I am validating error massage</b>",true);
		for (Map.Entry<String, JsonPath> entry : statusResponseMap.entrySet()) {
			for (Map.Entry<String, String> entry1 : transactionReferNoMap.entrySet()) {
				if(entry.getKey().equalsIgnoreCase(entry1.getValue())) {
					objPojo.getObjUtilities().logReporter("Transaction ReferNo : "+entry.getKey()+" MandateType : "+entry1.getKey(), true);
					objRestAssuredCode.errorForInvalidAccount(entry1.getKey(), entry.getValue());
				}
			}
		}
	}
}
