package com.rbl.util;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.generics.Pojo;
import com.generics.Utilities;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stepDefinition.API.RBL.RBLStepDefinition;
import com.stepDefinition.API.RBL.RBLStepDefinitionNSTP;

public class PaymentRequestBody {
	
	ReadJSONFile objReadJSONFile;
	private Pojo objPojo;
	private String requestBodyString;
	private Utilities objUtilities;
	
	public static String latestReferNo;
	
	public String paymentRequestBodyGenerator(PaymentModel objPaymentModel) {
		JsonObject ch2=new JsonObject();
		ch2.addProperty("BeneficiaryAccountNo",""+objPaymentModel.getBeneficiaryAccountNo()+"");
		ch2.addProperty("BeneficiaryIFSC", ""+objPaymentModel.getBeneficiaryIFSCCode()+"");
		ch2.addProperty("MandateType", ""+objPaymentModel.getMandateType()+"");
		ch2.addProperty("PurposeCode", "P1301");
		ch2.addProperty("PurposeDescription", "Family M");
		ch2.addProperty("TransactionDate", ""+objPaymentModel.getTransactionDate()+"");
		ch2.addProperty("TransactionReferenceNo", ""+objPaymentModel.getTransactionReferNo()+"");
		ch2.addProperty("TransferAmount", ""+objPaymentModel.getAmount()+"");
		
		JsonObject ch3=new JsonObject();
		ch3.addProperty("RemitterAddress1", "Goregaon");
		ch3.addProperty("RemitterCity", "MumbaiCity");
		ch3.addProperty("RemitterState", "MHCity");
		ch3.addProperty("RemitterCountry", "AO");
		ch3.addProperty("RemitterEmailId", "SBINHAMMADI@GMAIL.COM");
		ch3.addProperty("RemitterId", "AQMF001");
		ch3.addProperty("RemitterMobileNo", "27367222");
		ch3.addProperty("RemitterName", ""+objPaymentModel.getRemitterName()+"");
		ch3.addProperty("RemitterZipCode", "629192890");
		
		JsonObject ch4=new JsonObject();
		ch4.addProperty("BeneficiaryAccountHolderName", ""+objPaymentModel.getBeneficiaryAccountHolderName()+"");
		ch4.addProperty("BeneficiaryAddress1", "Airoli");
		ch4.addProperty("BeneficiaryAddress2", "AD");
		ch4.addProperty("BeneficiaryAddress3", "IN");
		ch4.addProperty("BeneficiaryEmailId", "abcdef@sign.com");
		ch4.addProperty("BeneficiaryMobileNo", "2736728");
		ch4.addProperty("BeneficiaryZipNo", "2313333");
		
		
		JsonObject ch5=new JsonObject();
		ch5.add("TransactionDetails", ch2);
		ch5.add("RemitterDetailsJobj", ch3);
		ch5.add("BeneficiaryDetailsJobj", ch4);
		
		JsonArray jsonArray=new JsonArray();
		jsonArray.add(ch5);
		
		JsonObject ch1=new JsonObject();
		ch1.addProperty("CorporateId", "AQMF001");
		ch1.addProperty("TimeStamp", "1560859427549");
		ch1.addProperty("UserId", "7485136194");
		ch1.addProperty("Token", "2420246456");
		ch1.add("Transaction", jsonArray);
		
		System.out.println(ch1.toString());
		//objPojo.getObjUtilities().logReporter(ch1.toString(),true);
		return ch1.toString();

	}
	
	public String paymentRequestBodyforDefault(PaymentModel objPaymentModel) {
		String beneficiaryAccountHolderName="Dasi";
		String remitterName="Dasi";
		requestBodyString=paymentRequestBodyGenerator(objPaymentModel);
		return requestBodyString;
	}
	
	
	

	
	
	
	public String requestBodyWrite(PaymentModel objPaymentModel) {
		objReadJSONFile=new ReadJSONFile();
		//objPojo=new Pojo();
		
		if(objPaymentModel.duplicateFlag==false) {
			latestReferNo=objReadJSONFile.transactionReferNoAppend();
			objPaymentModel.setTransactionReferNo(latestReferNo);
		}
		requestBodyString=paymentRequestBodyGenerator(objPaymentModel);
		if(RBLStepDefinitionNSTP.technicalError)
			requestBodyString=requestBodyString+".";
		objReadJSONFile.requestBodyWrite(requestBodyString,"\\Payment_I.json");
		return requestBodyString;
	}
}
