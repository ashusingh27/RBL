package com.rbl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.path.json.JsonPath;

public class PaymentModel {

	private String  mandateType;
	private String amount;
	private String transactionDate=systemDate();
	private String transactionReferNo;
	private String beneficiaryAccountNo;
	private String beneficiaryIFSCCode;
	private String beneficiaryAccountHolderName="Dasi";
	private String remitterName="Dasi";
	private String statusRestAPIStatus;
	private JsonPath statusResponse;
	private JsonPath paymentResponse;
	public static boolean duplicateFlag=false;
	
	public PaymentModel() {}

	public PaymentModel(String mandateType, String amount, String transactionDate, String transactionReferNo,
			String beneficiaryAccountNo, String beneficiaryIFSCCode, String beneficiaryAccountHolderName,
			String remitterName) {
		super();
		this.mandateType = mandateType;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionReferNo = transactionReferNo;
		this.beneficiaryAccountNo = beneficiaryAccountNo;
		this.beneficiaryIFSCCode = beneficiaryIFSCCode;
		this.beneficiaryAccountHolderName = beneficiaryAccountHolderName;
		this.remitterName = remitterName;
	}

	public String getMandateType() {
		return mandateType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionReferNo() {
		return transactionReferNo;
	}

	public void setTransactionReferNo(String transactionReferNo) {
		this.transactionReferNo =transactionReferNo;
	}

	public String getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}

	public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}

	public String getBeneficiaryIFSCCode() {
		return beneficiaryIFSCCode;
	}

	public void setBeneficiaryIFSCCode(String beneficiaryIFSCCode) {
		this.beneficiaryIFSCCode = beneficiaryIFSCCode;
	}

	public String getBeneficiaryAccountHolderName() {
		return beneficiaryAccountHolderName;
	}

	public void setBeneficiaryAccountHolderName(String beneficiaryAccountHolderName) {
		this.beneficiaryAccountHolderName = beneficiaryAccountHolderName;
	}

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}
	
	public String systemDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		return df.format(dateobj);
	}
	

	public String getStatusRestAPIStatus() {
		return statusRestAPIStatus;
	}

	public void setStatusRestAPIStatus(String statusRestAPIStatus) {
		this.statusRestAPIStatus = statusRestAPIStatus;
	}
	
	

	public JsonPath getStatusResponse() {
		return statusResponse;
	}

	public void setStatusResponse(JsonPath statusResponse) {
		this.statusResponse = statusResponse;
	}
	
	

	public JsonPath getPaymentResponse() {
		return paymentResponse;
	}

	public void setPaymentResponse(JsonPath paymentResponse) {
		this.paymentResponse = paymentResponse;
	}

	@Override
	public String toString() {
		return "PaymentModel [mandateType=" + mandateType + ", amount=" + amount + ", transactionDate="
				+ transactionDate + ", transactionReferNo=" + transactionReferNo + ", beneficiaryAccountNo="
				+ beneficiaryAccountNo + ", beneficiaryIFSCCode=" + beneficiaryIFSCCode
				+ ", beneficiaryAccountHolderName=" + beneficiaryAccountHolderName + ", remitterName=" + remitterName
				+ ", statusRestAPIStatus=" + statusRestAPIStatus + "]";
	}

	public String [] getPaymentArray() {
		String[] paymentResponseArray = { mandateType, amount, beneficiaryAccountNo, beneficiaryIFSCCode,
				PaymentRequestBody.latestReferNo };
		return paymentResponseArray;
	}
	
	
}
