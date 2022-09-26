package com.rbl.util;

import com.google.gson.JsonObject;

public class StatusRequestBody {
	
	ReadJSONFile objReadJSONFile;
	private String requestBodyString;
	
	public static String latestReferNo;
	

	public String statusRequestBody(String transactionReferNo) {
		JsonObject ch2=new JsonObject();
		ch2.addProperty("TransactionReferenceNumber",""+transactionReferNo+"");
		ch2.addProperty("UserId", "7485136194");
		ch2.addProperty("CorporateId", "AQMF001");
		ch2.addProperty("Token", "2420246456");
		ch2.addProperty("TimeStamp", "1560859427549");
		System.out.println("Status Rest apit RequestBody:\n"+ch2.toString());
		return ch2.toString();
	}
	
	public String requestBodyWrite(String mandateType,String transactionReferNo) {
		objReadJSONFile=new ReadJSONFile();
		
		requestBodyString=statusRequestBody(transactionReferNo);
		objReadJSONFile.requestBodyWrite(requestBodyString,"\\Status_I.json");
		return requestBodyString;
	}
	
	public void statusResponseProcessed() {}
	
	
}
