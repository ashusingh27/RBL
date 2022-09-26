package com.rbl.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ReadJSONFile {
	
	static String strCurrentLine;
	private static String[] referNoArray;
	private static String oldReferNo;
	private static BufferedWriter writer;
	private String LatestTransactionReferNo;
	
	static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData";
	
	public String transactionReferNoAppend() {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath+"\\Payment_I.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((strCurrentLine = br.readLine()) != null) {
				if(strCurrentLine.contains("TransactionReferenceNo")){
					 referNoArray = strCurrentLine.split(":");
					 oldReferNo=referNoArray[13];
					 break;
				}
			}
		
		if(oldReferNo!=null)
		{
			if(oldReferNo.contains("\""))
			{
				oldReferNo=oldReferNo.replace("\"", "");
				String [] array=oldReferNo.split(",");
				oldReferNo=array[0];
			}
			System.out.println("oldReferNo : "+oldReferNo.trim());
			long referNo=Long.parseLong(oldReferNo.trim());
			referNo=referNo+1;
			System.out.println("LatestReferNo : "+referNo);
			LatestTransactionReferNo=String.valueOf(referNo);
		}
				
		
	
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return LatestTransactionReferNo;
		
	}
	
	public void requestBodyWrite(String requestBody,String jsonFileName) {
		try {
			FileOutputStream outputStream = new FileOutputStream(filePath+"\\"+jsonFileName);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
			writer.write(requestBody);
			writer.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	

	public static void main(String[] args) throws IOException {}
}
