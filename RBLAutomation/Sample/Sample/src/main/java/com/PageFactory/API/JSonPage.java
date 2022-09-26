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
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromJsonInputFile(String user_id) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("user_id")) {
				String[] strAr = strCurrentLine.split("user_id");
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
			String newContent = oldContent.replaceAll(abc, user_id + "\",");
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
	@SuppressWarnings("resource")
	public void readRequiredPasswordFromJsonInputFile(String user_password) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("user_password")) {
				String[] strAr = strCurrentLine.split("user_password");
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
			String newContent = oldContent.replaceAll(abc, user_password + "\",");
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
	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromUserPasswordJsonInputFile(String user_password) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("user_password")) {
				String[] strAr = strCurrentLine.split("user_password");
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
			String newContent = oldContent.replaceAll(abc, user_password + "\",");
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

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromMember_CodeJsonInputFile(String member_code) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("member_code")) {
				String[] strAr = strCurrentLine.split("member_code");
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
			String newContent = oldContent.replaceAll(abc, member_code + "\",");
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

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromMember_KOBJsonInputFile(String member_KOB) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("member_KOB")) {
				String[] strAr = strCurrentLine.split("member_KOB");
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
			String newContent = oldContent.replaceAll(abc, member_KOB + "\",");
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

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromNameJsonInputFile(String name) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("company_name")) {
				strCurrentLine = br.readLine();

				if (strCurrentLine.contains("name")) {
					String[] strAr = strCurrentLine.split("name");
					abc = strAr[1].trim().substring(4);
					reader = new BufferedReader(new FileReader(fileToBeModified));
					String line = reader.readLine();
					while (line != null) {
						oldContent = oldContent + line + System.lineSeparator();
						line = reader.readLine();
					}
					String newContent = oldContent.replaceAll(abc, name + "\"");
					writer = new FileWriter(fileToBeModified);
					writer.write(newContent);
					reader.close();
					writer.close();
					break;
				}
			}
		}

	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")

	public void readRequiredDataFromaddressLine1JsonInputFile(String addressLine1) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("addressLine1")) {
				String[] strAr = strCurrentLine.split("addressLine1");
				abc = strAr[1].trim().substring(4);
				reader = new BufferedReader(new FileReader(fileToBeModified));
				String line = reader.readLine();
				while (line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					line = reader.readLine();
				}
				String newContent = oldContent.replaceAll(abc, addressLine1 + "\",");
				writer = new FileWriter(fileToBeModified);
				writer.write(newContent);
				reader.close();
				writer.close();
				break;
			}
		}

	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromCityJsonInputFile(String city) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("city")) {
				String[] strAr = strCurrentLine.split("city");
				abc = strAr[1].trim().substring(4);
				reader = new BufferedReader(new FileReader(fileToBeModified));
				String line = reader.readLine();
				while (line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					line = reader.readLine();
				}
				String newContent = oldContent.replaceAll(abc, city + "\",");
				writer = new FileWriter(fileToBeModified);
				writer.write(newContent);
				reader.close();
				writer.close();
				break;
			}
		}

	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromstateJsonInputFile(String state) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("state")) {
				String[] strAr = strCurrentLine.split("state");
				abc = strAr[1].trim().substring(4);
				reader = new BufferedReader(new FileReader(fileToBeModified));
				String line = reader.readLine();
				while (line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					line = reader.readLine();
				}
				String newContent = oldContent.replaceAll(abc, state + "\",");
				writer = new FileWriter(fileToBeModified);
				writer.write(newContent);
				reader.close();
				writer.close();
				break;
			}
		}

	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")

	public void readRequiredDataFromPinCodeJsonInputFile(String pinCode) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("pinCode")) {
				String[] strAr = strCurrentLine.split("pinCode");
				abc = strAr[1].trim().substring(4);
				reader = new BufferedReader(new FileReader(fileToBeModified));
				String line = reader.readLine();
				while (line != null) {
					oldContent = oldContent + line + System.lineSeparator();
					line = reader.readLine();
				}
				String newContent = oldContent.replaceAll(abc, pinCode + "\",");
				writer = new FileWriter(fileToBeModified);
				writer.write(newContent);
				reader.close();
				writer.close();
				break;
			}
		}

	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 14-04-2020
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromPanJsonInputFile(String pan) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\JsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("id")) {
				strCurrentLine = br.readLine();
				if (strCurrentLine.contains("pan")) {
					String[] strAr = strCurrentLine.split("pan");
					abc = strAr[1].trim().substring(4);
					reader = new BufferedReader(new FileReader(fileToBeModified));
					String line = reader.readLine();
					while (line != null) {
						oldContent = oldContent + line + System.lineSeparator();
						line = reader.readLine();
					}
					String newContent = oldContent.replaceAll(abc, pan + "\",");
					writer = new FileWriter(fileToBeModified);
					writer.write(newContent);
					reader.close();
					writer.close();
					break;
				}
			}
		}
	}

	
	@SuppressWarnings("resource")
	public void insertSpecificDataInJsonInputFile(String field,String testData) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\CCIRJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains(field)) {
				String[] strAr = strCurrentLine.split(field);
				abc = strAr[1].trim().substring(3);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, testData + "\",");
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
	public void insertSpecificLastFieldsDataInJsonInputFile(String field,String testData) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\CCIRJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains(field)) {
				String[] strAr = strCurrentLine.split(field);
				abc = strAr[1].trim().substring(3);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, testData + "\"");
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