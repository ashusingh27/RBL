package com.stepDefinition.API.RBL;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.PageFactory.BankUserDashboardPage;
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
import io.restassured.path.json.JsonPath;

public class RBL_NSTP_BulkDefinition {

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
	private Map<String, JsonPath> statusResponseMap;
	private LinkedHashMap applicationStatusMap;
	private Map<String,JsonPath> transactionNoResponseMap;
	private String applicationStatus;
	private TransactionInquiryPage objTransactionInquiryPage;
	private HashMap<String, String> statusResponseStatusMap;
	private String responseStatus;
	private Boolean statusRestAPIFlag;
	private Boolean paymentRestAPIFlag;
	private Integer delayBetPaymentStatus;

	public RBL_NSTP_BulkDefinition(BaseTest pojo) {
		objExcelUtil = new ExcelUtil();

		objPojo = pojo.initializeWebEnvironment();
		objHomePage = new HomePage(objPojo);
		objPaymentRequestBody = new PaymentRequestBody();
		objRestAssuredCode = new RestAssuredCode(objPojo);
		objCommonCode = new CommonCode(objPojo);
		objBankUserDashPage = new BankUserDashboardPage(objPojo);
		objStatusRequestBody = new StatusRequestBody();
		objTransactionInquiryPage = new TransactionInquiryPage(objPojo);
		
		statusResponseStatusMap = new LinkedHashMap<>();
		transactionNoResponseMap = new LinkedHashMap<>();
		baseUrl = objCommonCode.readConfig().getProperty("BaseURI");
		statusRestAPIFlag = Boolean.valueOf(objCommonCode.readConfig().getProperty("StatusRestAPI"));
		paymentRestAPIFlag = Boolean.valueOf(objCommonCode.readConfig().getProperty("PaymentRestAPI"));
		delayBetPaymentStatus = Integer.valueOf(objCommonCode.readConfig().getProperty("waitTime"));
	}
	
	}
