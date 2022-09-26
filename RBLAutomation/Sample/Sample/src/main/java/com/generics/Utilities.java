package com.generics;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.opencsv.CSVReader;

import io.qameta.allure.Attachment;

/**
 * @ScriptName : Utilities
 * @Description : This class contains utilities function
 * @Author : Nikhil Kumar Agarwal - AQM Technologies
 */
public class Utilities {
	private Pojo objPojo;
	private int futuredate;
	private Object testData;
	private String TriggertestDataPath;
	private String crn;
	private String tin;
	private String AccountNo;
	private String CreditType;
	private String OUTSTANDINGBALANCE;
	private String CURRENTREPORTEDDATE;
	private String PREVIOUSOUTSTANDINGBALANCE;
	private String PREVIOUSREPORTEDDATE;
	private String SANCTIONAMOUNT;
	private String CURRENCY;
	private Object SANCTIONDATE;
	private Object PREVIOUSACCOUNTSTATUS;
	private Object CURRENTACCOUNTSTATUS;
	private Object CURRENTDPD;
	private Object PREVIOUSDPD;
	private String AMOUNTOVERDUE;
	private String CURRENTACDPD;
	private String PREVIOUSACDPD;
	private Object DATECLASSIFIEDASWILLFUL;
	private String CURRENTWILLFUL;
	private Object PREVIOUSWILLFUL;
	private Object WRITTENOFFAMOUNT;
	private Object PREVIOUSASSETCLASSIFICATION;
	private String CURRENTASSETCLASSIFICATION;
	private Object DATEOFREPORTING;
	private Object CURRENTSUITFILEDSTATUS;
	private Object PREVIOUSSUITFILEDSTATUS;
	private String DATEOFSUIT;
	private Object SUITAMOUNT;
	private Object SUITREFERENCENUMBER;
	private Object SETTLEDAMOUNT;

	public Utilities(Pojo pojo) {
		this.objPojo = pojo;
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param :
	 *            Step - Step description, resultLog - result log pass/fail
	 *            (true/false), includeMobile - result for mobile(true/false)
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 */
	// @Step("{0}")

	public void logReporter(String step, boolean resultLog) {
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param :
	 *            Step - Step description, inputValue - Input value, resultLog -
	 *            result log pass/fail (true/false), includeMobile - result for
	 *            mobile(true/false)
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 */
	// @Step("{0} - {1}")
	public void logReporter(String step, String inputValue, boolean resultLog) {
		String strLog = step + " || Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param :
	 *            Step - Step description, expectedValue - verification point
	 *            expected value, actualValue - verification point actual value,
	 *            resultLog - result log pass/fail (true/false), includeMobile -
	 *            result for mobile(true/false)
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 */
	// @Step("{0} - {1} - {2}")
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog) {
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog);
	}

	/**
	 * @Method : addAssertTakeScreenShot
	 * @Description :
	 * @param :
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 */
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
			String actualValue, boolean resultLog) {
		System.out.println(objPojo.getTestCaseID() + "--> " + strLog);
		Logger logger = Logger.getLogger(Utilities.class);
		if (resultLog) {
			Reporter.log("Step Description--> " + strLog);
			//logger.info("Step Description--> " + objPojo.getTestCaseID() + "-->" + strLog);
			logger.info("Step Description--> "+ strLog);
			//Assert.assertTrue(true, "Step Description--> " + objPojo.getTestCaseID() + strLog);
			Assert.assertTrue(true, "Step Description--> "+ strLog);
		} else {
			String fileName = getDateInSpecifiedFormat("dd_MMM_yyyy_HH_mm_ss") + "_TCID_" + objPojo.getTestCaseID()
					+ ".png";
			String fileWithPath = Pojo.reportPath + "\\target\\custom-reports\\surefire-reports\\ScreenShot\\"
					+ fileName;
			Reporter.log("Step Description--> " + strLog);
			//logger.error("Step Description--> " + objPojo.getTestCaseID() + "-->" + strLog);
			logger.error("Step Description--> "+ strLog);
			logger.error("Failure URl :-------------> "+ objPojo.getDriver().getCurrentUrl());
			System.out.println("URl :-------------> " + objPojo.getDriver().getCurrentUrl());
			this.takeScreenShot(objPojo.getDriver(), fileWithPath);
			objPojo.setTestFailedStep(step);
			Assert.assertTrue(false, "Step Description--> " + step);
		}
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given web driver.
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 * 
	 */
	public boolean takeScreenShot(WebDriver webDriver, String fileWithPath) {
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		try {
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);
			return true;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return false;

		}
	}

	/**
	 * @Method : getDateInSpecifiedFormat
	 * @Description : This method takes parameter of your required DateFormat Type
	 *              Like: dd-mm-YYYY DD.MM.YYYY and in return it will give you
	 *              today's date in specified date format
	 * @param :
	 *            dateFormat like : dd-MM-YYYY
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 **/
	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		return current_date;
	}

	/**
	 * @Method : fileToByte
	 * @Description : Converts image file to byte array for allure.
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 * @throws :
	 *             IOException
	 */
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] fileToByte(File file) throws IOException {
		if (file != null)
			return Files.readAllBytes(Paths.get(file.getPath()));
		else
			return new byte[0];
	}

	/**
	 * Generate random string
	 * 
	 * @return String random string value
	 */
	public String getRandomString(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring.toUpperCase();
	}

	/**
	 * Generate random string with numbers
	 * 
	 * @return String random string value
	 */
	public String getRandomNumbers(int length) {
		String allowedChars = "123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * Generate random string with numbers
	 * 
	 * @return String random string value
	 */
	
	
	public String getRandomNumbers(String generator) {
		int length = Integer.parseInt(generator.toLowerCase().replace("dynamic", ""));
		String allowedChars = "123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * @author Nikhil Kumar Agarwal - AQM Technologies
	 * @Description : Use Below Method to get Random Number for Specific Range
	 */
	public int getRandomNumbersWithinRange(int minimumLimt, int maximumLimit) {
		try {
			Random rand = new Random();
			return rand.nextInt((maximumLimit - minimumLimt) + 1) + minimumLimt;
		} catch (Exception exception) {
			objPojo.setCustomException("Developer Side Issue");
			exception.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	 * NOTE : If you want future date : Pass No of Future day 's value as positive
	 * int i.e. 5 If you want Back date : Pass No of Future day 's value as Negative
	 * . i.e. -5
	 * 
	 * @Method : getFutureDateInSpecifiedFormat
	 * 
	 * @Description : This method takes parameter of your required DateFormat Type
	 *              Like: dd-mm-YYYY OR DD.MM.YYYY and in return it will give you
	 *              Future date in specified date format
	 * 
	 * @param1 : dateFormat like : dd-MM-YYYY
	 * 
	 * @param2 : No Of Future Day from now
	 * 
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 * 
	 *         i.e. : if you call getFutureDateInSpecifiedFormat("DD-MM-YYYY",5)
	 *         then it will return future date (current date + next 5 days)
	 * 
	 *         NOTE : If you want future date : Pass No of Future day 's value as
	 *         positive int i.e. 5 If you want Back date : Pass No of Future day 's
	 *         value as Negative . i.e. -5
	 * 
	 */
	public String getFutureOrBackDateInSpecifiedFormat(String dateFormat, int NoOfFutureDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, NoOfFutureDay);
		String futureDate = new SimpleDateFormat(dateFormat).format(c.getTime()).toString();
		return futureDate;
	}

	/**
	 * @Method: dpString
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 */
	public String dpString(String columnHeader) {
		try {
			if (objPojo.getObjHashTable().get(columnHeader) == null)
				return "";
			else {
				return objPojo.getObjHashTable().get(columnHeader).trim();
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: dpInteger
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 * 
	 */
	public Integer dpInteger(String columnHeader) {
		try {
			if (objPojo.getObjHashTable().get(columnHeader) == null)
				return 0;
			else {
				String value = objPojo.getObjHashTable().get(columnHeader).trim();
				StringTokenizer strTokenizer = new StringTokenizer(value, ".");
				value = strTokenizer.nextToken();
				return Integer.parseInt(value);
			}
		} catch (NoSuchElementException exception) {
			return 0;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: setDataPool
	 * @Description: this method returns data from the the previously loaded
	 *               setDataPool
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Nikhil Kumar Agarwal - AQM Technologies
	 */
	public void setDataPool(String Key, String value) {
		try {
			objPojo.getObjHashTable().put(Key, value);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 30-July-2019
	 * @return - ramdom values with alpha numeric values with special characters
	 */

	public String getRandomNumbers1(String generator) {
		int length = Integer.parseInt(generator.toLowerCase().replace("dynamic", ""));
		String allowedChars = "abcdefghijklmnopqrstuvxyz@#*$123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 30-July-2019
	 * @return - ramdom values with alpha numeric values with special characters
	 */

	public String getRandomNumbers11(String generator) {
		int length = Integer.parseInt(generator.toLowerCase().replace("dynamic", ""));
		String allowedChars = "abcdefghijklmnopqrstuvxyz@#*$123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 12-Aug-2019
	 */
	public static Document convertStringToXMLDocument(byte[] xml2String) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml2String)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 13-Aug-2019
	 */

	public static String readLineByLineJava8(String file) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = java.nio.file.Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for PAN Number
	 */
	public String panNoGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String panCharacters = "CPHFATBLJG";
		String numbers = "1234567890";
		String panNumber = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}
		char[] oneChar = panCharacters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char oneCharLetter = oneChar[random.nextInt(oneChar.length)];
			sb.append(oneCharLetter);
		}

		char[] tempFifthChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char fifthChar = tempFifthChar[random.nextInt(tempFifthChar.length)];
			sb.append(fifthChar);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		char[] tempLastChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char lastChar = tempLastChar[random.nextInt(tempLastChar.length)];
			sb.append(lastChar);
		}

		panNumber = sb.toString();
		return panNumber;

	}

	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for TIN Number
	 */
	public String tinNoGenerator() {
		String numbers = "1234567890";
		String tinNumber = null;

		StringBuilder sb = new StringBuilder();
		sb.append("27");
		char[] tempNineNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 9; i++) {
			char nineNumbers = tempNineNumbers[random.nextInt(tempNineNumbers.length)];
			sb.append(nineNumbers);
		}
		tinNumber = sb.toString();
		return tinNumber;

	}

	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for Address
	 */
	public String randomAddressGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String address = null;

		StringBuilder sb = new StringBuilder();

		char[] tempThreeNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char threeNumbers = tempThreeNumbers[random.nextInt(tempThreeNumbers.length)];
			sb.append(threeNumbers);
		}
		sb.append("/");

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		char[] tempEightChars = characters.toCharArray();
		for (int i = 0; i < 8; i++) {
			char eightChars = tempEightChars[random.nextInt(tempEightChars.length)];
			sb.append(eightChars);
		}

		sb.append(" ");

		char[] tempTenChars = characters.toCharArray();
		for (int i = 0; i < 10; i++) {
			char tenChars = tempTenChars[random.nextInt(tempTenChars.length)];
			sb.append(tenChars);
		}
		address = sb.toString();
		return address;

	}

	/**
	 * @author : Gaffar Shaikh
	 * @Date of Creation : 27-September-2019
	 * @return - ramdom Char for service tax id
	 */

	public String serviceTaxNoGenerator() {
		String numbers = "1234567890";
		String panNumber = null;

		StringBuilder sb = new StringBuilder("BEEPS");
		Random random = new Random();

		char[] oneChar = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char oneCharLetter = oneChar[random.nextInt(oneChar.length)];
			sb.append(oneCharLetter);
		}
		sb.append("NSD001");
		panNumber = sb.toString();
		return panNumber;
	}

	/**
	 * @author : Gaurav kumar
	 * @Date of Creation : 10-October-2019
	 * @return - ramdom values for Universal ID Number
	 */
	public String universalIdNumberGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String universalIdNumber = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 9; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}
		universalIdNumber = sb.toString();
		return universalIdNumber;
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 25-September-2019
	 * @Updated by : Shwetha Talapanty
	 * @Updated on: 09-October-2019
	 * @return - Random Name generator
	 */
	public String getRandomNameGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomname = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstEightChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char firstEightChars = tempFirstEightChars[random.nextInt(tempFirstEightChars.length)];
			sb.append(firstEightChars);
		}
		sb.append(" ");

		char[] tempFirstSevenChars = characters.toCharArray();
		for (int i = 0; i < 7; i++) {
			char firstSevenChars = tempFirstSevenChars[random.nextInt(tempFirstSevenChars.length)];
			sb.append(firstSevenChars);
		}

		randomname = sb.toString();
		return randomname;

	}

	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 09-October-2019
	 * @return - ramdom values for Address
	 */
	public String getRandomBusinessEntityNameGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomname = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstEightChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char firstEightChars = tempFirstEightChars[random.nextInt(tempFirstEightChars.length)];
			sb.append(firstEightChars);
		}
		sb.append(" ");

		char[] tempFirstSevenChars = characters.toCharArray();
		for (int i = 0; i < 7; i++) {
			char firstSevenChars = tempFirstSevenChars[random.nextInt(tempFirstSevenChars.length)];
			sb.append(firstSevenChars);
		}
		sb.append(" ");
		sb.append("PVT LTD");
		randomname = sb.toString();
		return randomname;

	}

	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 09-October-2019
	 * @return - ramdom values for Address
	 */
	public String getRandomPrefixNameGenerator() {
		String characters = "rs";
		String randomname = null;

		StringBuilder sb = new StringBuilder();
		sb.append("M");
		char[] tempFirsttChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 1; i++) {
			char firstChars = tempFirsttChars[random.nextInt(tempFirsttChars.length)];
			sb.append(firstChars);
		}
		randomname = sb.toString();
		return randomname;

	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-July-2019
	 * @param :
	 *            Sheet Name from the excel file
	 * @return - Load the test-data according to the scenario name.
	 */

	synchronized public void loadDataProvider(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/Excel/TestData.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							System.out.println(value);
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-Oct-2019
	 * @param :
	 *            Excel file path for test-data
	 * @return - Read the test-data according to the scenario name from the issue
	 *         report for UDA scenarios.
	 */

	synchronized public void loadDataProviderIssueReportForUDA(String fieldName, String fileName) {
		XSSFWorkbook workBook;
		String udaFileName = objPojo.getObjUtilities().dpString("RuntimeCreatedUDAFile");
		try {
			String testDataPath = "Z:\\AQM - Combat Automation\\UDA_Test_Cases_IssueReportsAndStats_Files\\"
					+ udaFileName + "_issueReport.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(3).getStringCellValue().equalsIgnoreCase(fieldName)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 29-October-2019
	 * @return - ramdom values for Customize PAN Number
	 */
	public String panNoGeneratorCustomize(String Custom) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String panNumber = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}
		sb.append(Custom);

		char[] tempFifthChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char fifthChar = tempFifthChar[random.nextInt(tempFifthChar.length)];
			sb.append(fifthChar);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		char[] tempLastChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char lastChar = tempLastChar[random.nextInt(tempLastChar.length)];
			sb.append(lastChar);
		}

		panNumber = sb.toString();
		return panNumber;

	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 29-October-2019
	 * @return - ramdom values for Customize PAN Number where repetition is expected
	 *         about the characters.
	 */
	public String panNoGeneratorRepetation(String Custom) {
		String numbers = "1234567890";
		String panNumber = null;

		StringBuilder sb = new StringBuilder();

		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			sb.append(Custom);
		}
		sb.append(Custom);

		for (int i = 0; i < 1; i++) {
			sb.append(Custom);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		for (int i = 0; i < 1; i++) {
			sb.append(Custom);
		}

		panNumber = sb.toString();
		return panNumber;

	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 29-October-2019
	 * @return - ramdom values for Customize PAN Number where repetition is expected
	 *         about the integers.
	 */
	public String panNoGeneratorIntergerRepetation(String Custom) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String panCharacters = "CPHFATBLJG";
		String panNumber = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}
		char[] oneChar = panCharacters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char oneCharLetter = oneChar[random.nextInt(oneChar.length)];
			sb.append(oneCharLetter);
		}

		char[] tempFifthChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char fifthChar = tempFifthChar[random.nextInt(tempFifthChar.length)];
			sb.append(fifthChar);
		}

		for (int i = 0; i < 4; i++) {
			sb.append(Custom);
		}

		char[] tempLastChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char lastChar = tempLastChar[random.nextInt(tempLastChar.length)];
			sb.append(lastChar);
		}

		panNumber = sb.toString();
		return panNumber;
	}

	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for CIN Number
	 * @Date of Updation : 01-11-2019
	 */
	public String cinNoGenerator() {
		String numbers = "1234567890";
		String cinNumber = null;

		StringBuilder sb = new StringBuilder();
		sb.append("U");

		char[] tempFiveNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char fiveNumbers = tempFiveNumbers[random.nextInt(tempFiveNumbers.length)];
			sb.append(fiveNumbers);
		}
		sb.append("MH");
		sb.append("2014");
		sb.append("PTC");

		char[] tempSixNumbers = numbers.toCharArray();
		for (int i = 0; i < 6; i++) {
			char sixNumbers = tempSixNumbers[random.nextInt(tempSixNumbers.length)];
			sb.append(sixNumbers);
		}

		cinNumber = sb.toString();
		return cinNumber;

	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 28-October-2019
	 * @param Filename
	 * @return - new file
	 * @Date of Updation : 01-11-2019
	 */

	public static Document getDocument(String fileName) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);
		return doc;
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @return - Values from the xpath
	 */

	public static String evaluateXPath(Document document, String xpathExpression) throws Exception {
		// Create XPathFactory object
		XPathFactory xpathFactory = XPathFactory.newInstance();

		// Create XPath object
		XPath xpath = xpathFactory.newXPath();

		String values = "";
		try {
			// Create XPathExpression object
			XPathExpression expr = xpath.compile(xpathExpression);

			// Evaluate expression result on XML document
			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodes.getLength(); i++) {
				values = values + (nodes.item(i).getNodeValue());
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return values;
	}

	/**
	 * @author : Gaurav Kumar
	 * @Date of Creation : 05-11-2019
	 * @param Length
	 *            and name of the string.
	 * @return Generate random string
	 * @return String random string value
	 */
	public String getRandomStringForNoiseWords(int lenght, String Word) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		String randomstringfornoisewords;
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		randomstringfornoisewords = randomstring + Word;
		return randomstringfornoisewords.toUpperCase();
	}

	/**
	 * @author : Saurabh Shekhar
	 * @Date of Creation : 05-Nov-2019
	 * @return - ramdom 4 digit values
	 */

	public String getFourDigit(int length) {

		String numbers = "1234567890";
		String FourDigit = null;

		StringBuilder sb = new StringBuilder();
		sb.append("1");

		char[] tempFiveNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char fiveNumbers = tempFiveNumbers[random.nextInt(tempFiveNumbers.length)];
			sb.append(fiveNumbers);
		}

		FourDigit = sb.toString();
		return FourDigit;

	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-July-2019
	 * @param -Sheet
	 *            Name from the excel file
	 * @return - Load the test-data according to the scenario name for the OLB
	 *         scenarios.
	 */

	synchronized public void loadDataProviderForOLB(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir")
					+ "/src/test/resources/testData/Excel/TestData_OLB.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Gaurav Kumar
	 * @Date of Creation : 05-11-2019
	 * @param Length
	 *            of the spaces
	 * @return String random number of spaces
	 */
	public String getRandomSpaces(int length) {
		String allowedChars = " ";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-Oct-2019
	 * @param :
	 *            Excel file path for test-data
	 * @return - Read the test-data according to the scenario name from the issue
	 *         report for UDA scenarios.
	 */

	synchronized public Hashtable<String, String> loadDataProviderIssueReportForUDA(String columnValue, String fileName,
			String columnNumber) {
		XSSFWorkbook workBook;
		String udaFileName = objPojo.getObjUtilities().dpString("RuntimeFileName");
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();
		try {
			String testData1 = objPojo.getObjConfig().getProperty("driveForUda");
			String testData2 = objPojo.getObjConfig().getProperty("pathForUda");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
			String folderForVerification = br.readLine();
			String testDataPath = testData1 + "\\" + testData2 + "\\" + folderForVerification
					+ "\\PostFolderForUDA\\Issue Report\\" + udaFileName + "_issueReport.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(Integer.parseInt(columnNumber)).getStringCellValue()
						.equalsIgnoreCase(columnValue)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-Nov-2019
	 * @param :
	 *            Excel file path for test-data
	 * @return - Read the test-data according to the scenario name from the issue
	 *         report for Trigger scenarios.
	 */
	synchronized public Hashtable<String, String> loadDataProviderIssueReportForTrigger(String columnValue,
			String fileName, String columnNumber) {
		XSSFWorkbook workBook;
		String udaFileName = objPojo.getObjUtilities().dpString("RuntimeFileName");
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();
		try {

			String filePath = "Z:\\AQM - Combat Automation\\UDA_Test_Cases_IssueReportsAndStats_Files" + "\\"
					+ udaFileName + ".xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(Integer.parseInt(columnNumber)).getStringCellValue()
						.equalsIgnoreCase(columnValue)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-Nov-2019
	 * @param -Sheet
	 *            Name from the excel file
	 * @return - Load the test-data according to the scenario name for the Trigger
	 *         scenarios.
	 */

	synchronized public void loadDataProviderTrigger(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir")
					+ "/src/test/resources/testData/Excel/TestData_UDA.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-Nov-2019
	 * @param -Sheet
	 *            Name from the excel file
	 * @return - Load the test-data according to the scenario name for the OLB
	 *         scenarios.
	 */

	@SuppressWarnings({ "unused", "resource" })
	synchronized public void loadDataProviderForUDA(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testData1 = objPojo.getObjConfig().getProperty("driveForUda");
			String testData2 = objPojo.getObjConfig().getProperty("pathForUda");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = (dtf.format(now));
			BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
			String folderForVerification = br.readLine();
			String testDataPath = testData1 + "\\" + testData2 + "\\" + folderForVerification + "\\TestData_UDA.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Abhishek Kawathekar
	 * @Date of Creation : 09-July-2019
	 * @param -Sheet
	 *            Name from the excel file
	 * @return - Load the test-data according to the scenario name for the Trigger
	 *         scenarios.
	 */

	@SuppressWarnings({ "unused", "resource" })
	synchronized public void loadDataProviderForTRIGGER(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testData1 = objPojo.getObjConfig().getProperty("driveForTrigger");
			String testData2 = objPojo.getObjConfig().getProperty("pathForTrigger");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = (dtf.format(now));
			BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
			String folderForVerification = br.readLine();
			String testDataPath = testData1 + "\\" + testData2 + "\\" + folderForVerification
					+ "\\TestData_TRIGGER.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Gaurav kumar
	 * @Date of Creation : 11-September-2019
	 * @return - ramdom values for Voter ID
	 * @throws AWTException
	 */
	public String VoterIdNoGeneratorRandom() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String voterIdNumber = null;
		StringBuilder sb = new StringBuilder();
		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}
		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 6; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}
		voterIdNumber = sb.toString();
		return voterIdNumber;
	}
	
	public String VoterIdNoGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String voterIdNumber = null;
		StringBuilder sb = new StringBuilder();
		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}
		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 7; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}
		voterIdNumber = sb.toString();
		return voterIdNumber;
	}

	/**
	 * @author : Vinayak Waghchaure
	 * @Date of Creation : 07-JAN-2020
	 * @param -Sheet
	 *            Name from the excel file
	 * @return - Load the test-data according to the scenario name for the Bulk OLM
	 *         scenarios.
	 */
	@SuppressWarnings({ "unused", "resource" })
	synchronized public void loadDataProviderForOLMBulkUpdate(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testData1 = objPojo.getObjConfig().getProperty("driveForUda");
			String testData2 = objPojo.getObjConfig().getProperty("pathForOLM");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = (dtf.format(now));
			BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
			String folderForVerification = br.readLine();
			String testDataPath = testData1 + "\\" + testData2 + "\\" + folderForVerification + "\\TestData_UDA.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author : Shwetha Talapanty
	 * @return
	 * @Date of Creation : 17-FEB-2020
	 * 
	 **/
	synchronized public Hashtable<String, String> loadDataProviderForFlexiCIR(String fileName) {
		XSSFWorkbook workBook;
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();
		String testData = objPojo.getObjUtilities().dpString("COMPANY-NAME");
		String testData1 = objPojo.getObjUtilities().dpString("RuntimeCreatedCIRWorkOrderNumber");
		objPojo.getObjUtilities().logReporter("Work order Number: " + testData1, true);
		String testDataPath = System.getProperty("user.dir") + "\\target\\downloadFiles\\" + testData + ".xlsx";
		try {
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(testData1)) {
					for (int i = 1; i <= sheet.getRow(1).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	/**
	 * @author : Shwetha Talapanty
	 * @return
	 * @Date of Creation : 30-March-2020
	 * 
	 **/
	synchronized public Hashtable<String, String> loadDataProviderForFlexiCIRScenarioOutline(String fileName,
			String testData) {
		XSSFWorkbook workBook;
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();

		String testData1 = objPojo.getObjUtilities().dpString("RuntimeCreatedCIRWorkOrderNumber");
		objPojo.getObjUtilities().logReporter("Work order Number: " + testData1, true);
		String testDataPath = System.getProperty("user.dir") + "\\target\\downloadFiles\\" + testData + ".xlsx";
		try {
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(testData1)) {
					for (int i = 1; i <= sheet.getRow(1).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	/**
	 * @author : Shwetha Talapanty
	 * @return
	 * @Date of Creation : 28-04-2020
	 * 
	 **/
	synchronized public Hashtable<String, String> loadDataProviderForExcelDownload() {
		XSSFWorkbook workBook;
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();
		String testData = objPojo.getObjUtilities().dpString("RuntimeCreatedUserCodeToBeVerified");
		String testData2 = "User_" + testData;
		String testData1 = objPojo.getObjUtilities().dpString("RuntimeCreatedUserCodeToBeVerified");
		String testDataPath = System.getProperty("user.dir") + "\\target\\downloadFiles\\" + testData2 + ".xlsx";
		try {
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(testData)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	/**
	 * @author : Shwetha Talapanty
	 * @return
	 * @Date of Creation : 12-05-2020
	 * 
	 **/
	synchronized public Hashtable<String, String> loadDataProviderForExcelDownloadMember() {
		XSSFWorkbook workBook;
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();
		String testData = objPojo.getObjUtilities().dpString("RuntimeCreatedMemberCode");
		String testData2 = "Member_" + testData;
		String testData1 = objPojo.getObjUtilities().dpString("RuntimeCreatedMember");
		String testDataPath = System.getProperty("user.dir") + "\\target\\downloadFiles\\" + testData2 + ".xlsx";
		try {
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(testData1)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {

							Cell cell1 = sheet.getRow(j).getCell(i);
							cell1.setCellType((Cell.CELL_TYPE_STRING));
							String value = cell1.getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(0).getCell(i).getStringCellValue(), value);
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	synchronized public Hashtable<String, String> loadDataProviderOutputFileForTrigger(String fileName) {
		XSSFWorkbook workBook;
		String udaFileName = objPojo.getObjUtilities().dpString("RuntimeSubscriptionFileName");
		String columnValue = udaFileName + ".csv";
		Hashtable<String, String> objHashtable = new Hashtable<String, String>();
		try {
			String testData1 = objPojo.getObjConfig().getProperty("driveForTrigger");
			String testData2 = objPojo.getObjConfig().getProperty("pathForTrigger");
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
			String folderForVerification = br.readLine();
			String testDataPath = testData1 + "\\" + testData2 + "\\" + folderForVerification
					+ "\\PostFolderForTrigger\\SubscriptionOutputFile\\" + udaFileName + "_audit_report.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(columnValue)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objHashtable.put(sheet.getRow(1).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(1).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objHashtable.put("", "");
						}
					}
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(objHashtable);
		return objHashtable;
	}

	public void readFinalOutputFilesForTriggers() throws IOException {

		String testData1 = objPojo.getObjConfig().getProperty("driveForTrigger");
		String testData2 = objPojo.getObjConfig().getProperty("pathForTrigger");
		// @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
		@SuppressWarnings("unused")
		String folderForVerification = br.readLine();
		String subjectName = objPojo.getObjUtilities().dpString("RunatimeNameBS");
		Reader reader = Files.newBufferedReader(Paths.get(TriggertestDataPath));
		System.out.println(TriggertestDataPath);
		CSVParser csvParser = new CSVParser(reader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		for (CSVRecord csvRecord : csvParser) {
			if (csvRecord.get("SUBJECT NAME").equalsIgnoreCase(subjectName)) {
				testData = objPojo.getObjUtilities().dpString("RuntimeCINNumber");
				if (!testData.equals("")) {
					testData1 = csvRecord.get("CIN");
					objPojo.getObjUtilities().logReporter("Verify Cin no in Trigger  csv file:", testData1,
							testData.equals(testData1.trim()));
				}
				testData = objPojo.getObjUtilities().dpString("PRODUCTCOMPONENTNAME");
				if (!testData.equals("")) {
					testData1 = csvRecord.get("PRODUCT COMPONENT NAME");
					objPojo.getObjUtilities().logReporter("Verify Product Component Name in Trigger  csv file:", testData1,
							testData.equals(testData1.trim()));
				}
				crn = objPojo.getObjUtilities().dpString("RuntimeCRNNumber");
				tin = objPojo.getObjUtilities().dpString("RuntimeTINNumber");
				String otherId = "CRN = " + crn + "|TIN = " + tin;
				if (!otherId.equals("")) {
					testData1 = csvRecord.get("OTHER IDENTIFIERS");
					objPojo.getObjUtilities().logReporter("Verify other identifiers CRN and TIN in Trigger  csv file:",
							otherId, testData1, otherId.equals(testData1.trim()));

				}

				testData1 = csvRecord.get("ALERT DETAILS");
				String[] alertDetails = null;

				String Replace = testData1.replace('|', '#');
				alertDetails = Replace.split("#");

				int i = 0;
				while (i < alertDetails.length) {
					if (testData1.contains("ACCOUNT NUMBER")) {
						AccountNo = objPojo.getObjUtilities().dpString("RunatimeAccountNoCR");
						AccountNo = "ACCOUNT NUMBER = " + AccountNo;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								AccountNo, AccountNo.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CREDIT FACILITY TYPE")) {

						CreditType = objPojo.getObjUtilities().dpString("CREDITFACILITYTYPE");
						if(!CreditType.equals(""))
						{
						CreditType = "CREDIT FACILITY TYPE = " + CreditType;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CreditType, CreditType.equalsIgnoreCase(alertDetails[i].trim()));
						}
						i++;

					}
					if (testData1.contains("CURRENT OUTSTANDING BALANCE (INR)")) {
						OUTSTANDINGBALANCE = objPojo.getObjUtilities().dpString("RuntimeOUTSTANDINGBALANCE(INR)");
						OUTSTANDINGBALANCE = "CURRENT OUTSTANDING BALANCE (INR) = " + OUTSTANDINGBALANCE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								OUTSTANDINGBALANCE, OUTSTANDINGBALANCE.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT REPORTED DATE")) {
						CURRENTREPORTEDDATE = objPojo.getObjUtilities().dpString("CURRENTREPORTEDDATE");
						CURRENTREPORTEDDATE = "CURRENT REPORTED DATE = " + CURRENTREPORTEDDATE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTREPORTEDDATE, CURRENTREPORTEDDATE.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("PREVIOUS OUTSTANDING BALANCE")) {
						PREVIOUSOUTSTANDINGBALANCE = objPojo.getObjUtilities()
								.dpString("RuntimeOUTSTANDINGBALANCE(INR)");
						PREVIOUSOUTSTANDINGBALANCE = "PREVIOUS OUTSTANDING BALANCE = " + PREVIOUSOUTSTANDINGBALANCE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSOUTSTANDINGBALANCE, PREVIOUSOUTSTANDINGBALANCE.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("PREVIOUS REPORTED DATE")) {
						PREVIOUSREPORTEDDATE = objPojo.getObjUtilities().dpString("PREVIOUSREPORTEDDATE");
						PREVIOUSREPORTEDDATE = "PREVIOUS REPORTED DATE = " + PREVIOUSREPORTEDDATE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSREPORTEDDATE, PREVIOUSREPORTEDDATE.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("OUTSTANDING BALANCE (INR)")&&(!testData1.contains("CURRENT OUTSTANDING BALANCE (INR)"))) {
						OUTSTANDINGBALANCE = objPojo.getObjUtilities().dpString("RuntimeOUTSTANDINGBALANCE(INR)");
						OUTSTANDINGBALANCE = "OUTSTANDING BALANCE (INR) = " + OUTSTANDINGBALANCE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								OUTSTANDINGBALANCE, OUTSTANDINGBALANCE.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("SANCTION AMOUNT")) {
						SANCTIONAMOUNT = objPojo.getObjUtilities().dpString("RuntimeSanctionedAmount");
						SANCTIONAMOUNT = "SANCTION AMOUNT = " + SANCTIONAMOUNT;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								SANCTIONAMOUNT, SANCTIONAMOUNT.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENCY")) {
						CURRENCY = objPojo.getObjUtilities().dpString("CURRENCY");
						CURRENCY = "CURRENCY = " + CURRENCY;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENCY, CURRENCY.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("SANCTION DATE")) {
						SANCTIONDATE = objPojo.getObjUtilities().dpString("SANCTION DATE");
						if(!SANCTIONDATE.equals(""))
						{
						SANCTIONDATE = "SANCTION DATE = " + SANCTIONDATE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								 SANCTIONDATE.equals(alertDetails[i].trim()));
						
						i++;
						}
					}
					if (testData1.contains("WRITTEN OFF AMOUNT (INR)")) {
						WRITTENOFFAMOUNT = objPojo.getObjUtilities().dpString("WRITTENOFFAMOUNT(INR)");
						if(!WRITTENOFFAMOUNT.equals(""))
						{
						WRITTENOFFAMOUNT = "WRITTEN OFF AMOUNT (INR) = " + WRITTENOFFAMOUNT;
						
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								WRITTENOFFAMOUNT.equals(alertDetails[i].trim()));
						
						}
						i++;
					}
					if (testData1.contains("SETTLED AMOUNT (INR)")) {
						SETTLEDAMOUNT = objPojo.getObjUtilities().dpString("SETTLEDAMOUNT(INR)");
						SETTLEDAMOUNT = "SETTLED AMOUNT (INR) = " + SETTLEDAMOUNT;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								SETTLEDAMOUNT.equals(alertDetails[i].trim()));
						i++;

					}

					if (testData1.contains("PREVIOUS ACCOUNT STATUS")) {
						PREVIOUSACCOUNTSTATUS = objPojo.getObjUtilities().dpString("PREVIOUSACCOUNTSTATUS");
						PREVIOUSACCOUNTSTATUS = "PREVIOUS ACCOUNT STATUS = " + PREVIOUSACCOUNTSTATUS;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSACCOUNTSTATUS.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT ACCOUNT STATUS")) {
						CURRENTACCOUNTSTATUS = objPojo.getObjUtilities().dpString("CURRENTACCOUNTSTATUS");
						CURRENTACCOUNTSTATUS = "CURRENT ACCOUNT STATUS = " + CURRENTACCOUNTSTATUS;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTACCOUNTSTATUS.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("PREVIOUS DPD")) {
						PREVIOUSDPD = objPojo.getObjUtilities().dpString("PREVIOUSDPD");
						PREVIOUSDPD = "PREVIOUS DPD = " + PREVIOUSDPD;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSDPD.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT DPD")) {
						CURRENTDPD = objPojo.getObjUtilities().dpString("CURRENTDPD");
						CURRENTDPD = "CURRENT DPD = " + CURRENTDPD;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTDPD.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("PREVIOUS ASSET CLASSIFICATION")) {
						PREVIOUSASSETCLASSIFICATION = objPojo.getObjUtilities().dpString("PREVIOUSASSETCLASSIFICATION");
						PREVIOUSASSETCLASSIFICATION = "PREVIOUS ASSET CLASSIFICATION = " + PREVIOUSASSETCLASSIFICATION;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSASSETCLASSIFICATION.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT ASSET CLASSIFICATION")) {
						CURRENTASSETCLASSIFICATION = objPojo.getObjUtilities().dpString("CURRENTASSETCLASSIFICATION");
						CURRENTASSETCLASSIFICATION = "CURRENT ASSET CLASSIFICATION = " + CURRENTASSETCLASSIFICATION;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTASSETCLASSIFICATION.equals(alertDetails[i].trim()));
						i++;

					}
					
					if (testData1.contains("PREVIOUS AC/DPD")) {
						PREVIOUSACDPD = objPojo.getObjUtilities().dpString("PREVIOUSAC/DPD");
						PREVIOUSACDPD = "PREVIOUS AC/DPD = " + PREVIOUSACDPD;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSACDPD.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT AC/DPD")) {
						CURRENTACDPD = objPojo.getObjUtilities().dpString("CURRENTAC/DPD");
						CURRENTACDPD = "CURRENT AC/DPD = " + CURRENTACDPD;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTACDPD.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("AMOUNT OVERDUE")) {
						AMOUNTOVERDUE = objPojo.getObjUtilities().dpString("RuntimeAmountOverdueCR");
						AMOUNTOVERDUE = "AMOUNT OVERDUE = " + AMOUNTOVERDUE;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								AMOUNTOVERDUE.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("PREVIOUS WILLFUL DEFAULT STATUS")) {
						PREVIOUSWILLFUL = objPojo.getObjUtilities().dpString("PREVIOUSWILLFULDEFAULTSTATUS");
						PREVIOUSWILLFUL = "PREVIOUS WILLFUL DEFAULT STATUS = " + PREVIOUSWILLFUL;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSWILLFUL.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT WILLFUL DEFAULT STATUS")) {
						CURRENTWILLFUL = objPojo.getObjUtilities().dpString("CURRENTWILLFULDEFAULTSTATUS");
						CURRENTWILLFUL = "CURRENT WILLFUL DEFAULT STATUS = " + CURRENTWILLFUL;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTWILLFUL.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("DATE CLASSIFIED AS WILLFUL DEFAULT")) {
						DATECLASSIFIEDASWILLFUL = objPojo.getObjUtilities().dpString("DATECLASSIFIEDASWILLFULDEFAULT");
						DATECLASSIFIEDASWILLFUL = "DATE CLASSIFIED AS WILLFUL DEFAULT = " + DATECLASSIFIEDASWILLFUL;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								DATECLASSIFIEDASWILLFUL.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("DATE OF REPORTING")) {
						DATEOFREPORTING = objPojo.getObjUtilities().dpString("DATEOFREPORTING");
						DATEOFREPORTING = "DATE OF REPORTING = " + DATEOFREPORTING;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								DATEOFREPORTING.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("SUIT REFERENCE NUMBER")) {
						SUITREFERENCENUMBER = objPojo.getObjUtilities().dpString("SUITREFERENCENUMBER");
						SUITREFERENCENUMBER = "SUIT REFERENCE NUMBER = " + SUITREFERENCENUMBER;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								SUITREFERENCENUMBER.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("SUIT AMOUNT (INR)")) {
						SUITAMOUNT = objPojo.getObjUtilities().dpString("SUITAMOUNT(INR)");
						SUITAMOUNT = "SUIT AMOUNT (INR) = " + SUITAMOUNT;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								SUITAMOUNT.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("DATE OF SUIT")) {
						DATEOFSUIT = objPojo.getObjUtilities().dpString("DATEOFSUIT");
						DATEOFSUIT = "DATE OF SUIT = " + DATEOFSUIT;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								DATEOFSUIT.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("PREVIOUS SUIT FILED STATUS")) {
						PREVIOUSSUITFILEDSTATUS = objPojo.getObjUtilities().dpString("PREVIOUSSUITFILEDSTATUS");
						PREVIOUSSUITFILEDSTATUS = "PREVIOUS SUIT FILED STATUS = " + PREVIOUSSUITFILEDSTATUS;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								PREVIOUSSUITFILEDSTATUS.equals(alertDetails[i].trim()));
						i++;

					}
					if (testData1.contains("CURRENT SUIT FILED STATUS ")) {
						CURRENTSUITFILEDSTATUS = objPojo.getObjUtilities().dpString("CURRENTSUITFILEDSTATUS");
						CURRENTSUITFILEDSTATUS = "CURRENT SUIT FILED STATUS = " + CURRENTSUITFILEDSTATUS;
						objPojo.getObjUtilities().logReporter(
								"Verify Account number of Alert Details in Trigger csv file:", alertDetails[i],
								CURRENTSUITFILEDSTATUS.equals(alertDetails[i].trim()));
						i++;

					}
					break;

				}
				
			}
			break;

		}
	}

	public void readTriggerFinalOutputFilesForTriggers(String FeatureDate, String valuef0rlast) throws IOException {
		String udaFileName = objPojo.getObjUtilities().dpString("RuntimeSubscriptionFileName");
		String testData1 = objPojo.getObjConfig().getProperty("driveForTrigger");
		String testData2 = objPojo.getObjConfig().getProperty("pathForTrigger");
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(testData1 + "\\" + testData2 + "\\ReadMe.txt"));
		String folderForVerification = br.readLine();
		TriggertestDataPath = testData1 + "\\" + testData2 + "\\" + folderForVerification
				+ "\\PostFolderForTrigger\\TriggerFinalOutput\\" + udaFileName + "_cons_alerts_" + FeatureDate + ""
				+ valuef0rlast + ".csv";
	}
	/**
	 * @author : Shwetha Talapanty
	 * @Date of Creation : 23-06-202
	 * @param :
	 *            Sheet Name from the excel file
	 * @return - Load the test-data for OLB according to the scenario name.
	 */

	synchronized public void loadDataProviderForOLBInputFile(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/Excel/TestData_OLBFile.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
