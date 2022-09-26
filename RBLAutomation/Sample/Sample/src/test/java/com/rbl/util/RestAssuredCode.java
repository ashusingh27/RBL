package com.rbl.util;



import com.generics.Pojo;
import com.generics.Utilities;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class RestAssuredCode {
	
	PaymentRequestBody objPaymentRequestBody;
	private Response response;
	private CommonCode objCommonCode;
	private Pojo objPojo;
	private String statusmsg;
	
	public static JsonPath jsonPathEvaluator;

	
	public RestAssuredCode(Pojo objPojo) {
		this.objPojo=objPojo;
		objPaymentRequestBody=new PaymentRequestBody();
		objCommonCode=new CommonCode(objPojo);
		
	}

	public String restApiHit(String uri,String requestType,String jsonFileName) {
		
		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\" + jsonFileName;
		String file = Utilities.readLineByLineJava8(jsonFilePath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		//System.out.println(uri);
		RequestSpecification httpRequest = RestAssured.given().config(config).accept("application/json")
				//.header("Content-Type", "application/json").header("Media Type", "application/json")
				.body(file).log()
				.all();
		String P = "Post";
		String P1 = "Put";
		String D = "Delete";
		String G = "Get";

		if (P.equalsIgnoreCase(requestType)) {
			response = httpRequest.post(uri);
		} else if (P1.equalsIgnoreCase(requestType)) {
			response = httpRequest.put(uri);
		} else if (D.equalsIgnoreCase(requestType)) {
			response = httpRequest.delete(uri);
		} else if (G.equalsIgnoreCase(requestType)) {
			response = httpRequest.get(uri);
		}

		System.out.println("Status : "+response.getStatusCode());
		//System.out.println(response.headers());
		System.out.println("Response : "+response.asString());
		jsonPathEvaluator = response.jsonPath();
	
		return response.asString();

	}
	
	public String convertStrIntoJsonObject(String jsonString) {
		JSONParser parser = new JSONParser();
		JSONObject json=null;
		try {
			 json = (JSONObject) parser.parse(jsonString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// memberValue=json.getAsString("Status");
		JSONArray jsonArray=(JSONArray) json.get("Transactions");
		if(jsonArray!=null)
			json=(JSONObject) jsonArray.get(0);
		String memberValue=json.getAsString("Status");
		
		System.out.println("transaction status : "+memberValue);
		return memberValue;
	}
	
	public String statusResponseProcess() {
		String status=jsonPathEvaluator.get("PaymentStatus");
		if(status==null) 
			status=jsonPathEvaluator.getString("Status");
		return status;	
	}
	
	public String statusResponseProcess(JsonPath jsonPathEvaluator) {
		String status=jsonPathEvaluator.get("PaymentStatus");
		if(status==null) 
			status=jsonPathEvaluator.getString("Status");
		return status;	
	}
	
	public String paymentResponseProcess(String memberStr) {
		return jsonPathEvaluator.get(memberStr);
	}
	
	public String negativeStatusResponseProcess(Response response) {
		String errorDesc=jsonPathEvaluator.get("ErrorDesc");
		return errorDesc;
	}
	
	public String fetchError(String jsonObject,String expectedError,JsonPath jsonPathEvaluator) {
		String errorMsg=null;
		errorMsg = jsonPathEvaluator.get(jsonObject);
		objCommonCode.compareTo(errorMsg, expectedError);
		return errorMsg;
	}
	
	
		public String StatusResponseProcess(Response response) {
			String statusDesc=jsonPathEvaluator.get("Status");
			return statusDesc;
		}
	
	public String errorForInvalidData(String invalidData,JsonPath jsonPathEvaluator) {
		String errorMsg = null;
		String statusmsg = null;
		if (invalidData.toLowerCase().contains("beneficiary name")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);  
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Error in beneAccHolderName,", errorMsg);
		} else if (invalidData.toLowerCase().contains("remitter name")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Error in remitterName,", errorMsg);
		} else if (invalidData.toLowerCase().contains("balance")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Account require due diligence", errorMsg);
		} else if(invalidData.toLowerCase().contains("already used")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Duplicate Transaction Id", errorMsg);
		}
		return errorMsg;
	}
	
	public String errorForInvalidDataSTP(String invalidData,JsonPath jsonPathEvaluator) {
		String errorMsg = null;
		String statusmsg = null;
		if (invalidData.toLowerCase().contains("beneficiary name")) {
			statusmsg = jsonPathEvaluator.get("[0].Status");
			objCommonCode.compareToMap("Hold", statusmsg);  
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Error in beneAccHolderName,", errorMsg);
		} else if (invalidData.toLowerCase().contains("remitter name")) {
			statusmsg = jsonPathEvaluator.get("[0].Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Error in remitterName,", errorMsg);
		} else if (invalidData.toLowerCase().contains("balance")) {
			statusmsg = jsonPathEvaluator.get("[0].Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Error in beneAccHolderName,", errorMsg);
		} else if(invalidData.toLowerCase().contains("already used")) {
			statusmsg = jsonPathEvaluator.get("[0].Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Duplicate Transaction Id", errorMsg);
		}
		return errorMsg;
	}
	public String errorForCautionWord(String invalidData,JsonPath jsonPathEvaluator) {
		String errorMsg = null;
		String statusmsg = null;
		if (invalidData.toLowerCase().contains("beneficiary name")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);  
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
			objCommonCode.compareToMap("Account require due diligence", errorMsg);
		} else if (invalidData.toLowerCase().contains("remitter name")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
			objCommonCode.compareToMap("Account require due diligence", errorMsg);
		} else if (invalidData.toLowerCase().contains("balance")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
			objCommonCode.compareToMap("Amount greater than allowed limit for a purpose code", errorMsg);
		} else if(invalidData.toLowerCase().contains("already used")) {
			statusmsg = jsonPathEvaluator.get("Status");
			objCommonCode.compareToMap("Hold", statusmsg);
			errorMsg = jsonPathEvaluator.get("[0].ErrorDesc");
			objCommonCode.compareToMap("Duplicate Transaction Id", errorMsg);
		}
		return errorMsg;
	}
	
	
	
	public String errorForInvalidAccount(String mandateType,JsonPath jsonPathEvaluator) {
		
		String errorMsg = null;
		statusmsg = jsonPathEvaluator.get("Status");
		if(mandateType.equalsIgnoreCase("I")) {
			
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
		objCommonCode.compareToMap("Beneficiary bank is not enable for Inward Foreign Remittance", errorMsg);
		}else if(mandateType.equalsIgnoreCase("D")) {
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
		objCommonCode.compareToMap("Invalid account", errorMsg);
		}else if(mandateType.equalsIgnoreCase("N")) {
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
			objCommonCode.compareToMap("~Field must be entered.~Field must be entered.~Invalid BIC [SIMB0002233] and Paysys ID [NEFT] combination~Field must be entered.~Field must be entered.", errorMsg);
		}
		else{
			errorMsg = jsonPathEvaluator.get("ErrorDesc");
		objCommonCode.compareToMap("~Field must be entered.~Field must be entered.~Invalid BIC [SIMB0002233] and Paysys ID [RTGS] combination~Field must be entered.~Field must be entered.", errorMsg);
		}
		return statusmsg;
	}
}
