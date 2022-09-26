package com.PageFactory.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.generics.Pojo;

public class JSonPage {

	private Pojo objPojo;

	public JSonPage(Pojo pojo) {
		objPojo = pojo;
	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 27-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromJsonInputFile(String deliveryFlag) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("deliveryFlag")) {
				String[] strAr = strCurrentLine.split("deliveryFlag");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, deliveryFlag + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}