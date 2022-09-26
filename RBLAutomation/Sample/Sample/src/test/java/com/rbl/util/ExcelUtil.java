package com.rbl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {

	File file;
	FileOutputStream fout;
	FileInputStream fin;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	static int rowNo = 0;
	static int cellNo=0;
	String filePath;
	Properties prop;
	CommonCode objCommonCode;
	private String bulkTestDataFolder;
	FormulaEvaluator objFormulaEvaluator;
	DataFormatter objDefaultFormat;


	public ExcelUtil() {
		Timestamp ts1 = Timestamp.from(Instant.now()); 
		Long val=ts1.getTime();  
		filePath="D:\\ShreeyaFolder\\PaymentResponse\\PaymentResponse_"+val+".xlsx";
		System.out.println("PaymentResponse file : "+filePath);
		objCommonCode=new CommonCode();
		objDefaultFormat = new DataFormatter();
	}
	
	public void writeInExcel() {

		file = new File(filePath); // creating a new file instance
		try {
			fout = new FileOutputStream(file);
			wb = new XSSFWorkbook();
			sheet = wb.createSheet("Payment_Response");
			if (rowNo == 0) {
				String[] headerRowArray = { "MANDATETYPE", "AMOUNT", "BENEFICIARYACCOUNTNO", "BENEFICIARYIFSC",
						"TRANSACTIONREFERNO","STATUSRESPONSE"};

				Row row = sheet.createRow(rowNo);
				for (int i = 0; i < headerRowArray.length; i++) {

					Cell cell = row.createCell(i);
					cell.setCellValue(headerRowArray[i]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	public void addRow(String[] paymentResponseArray) {
		rowNo++;
		Row row = sheet.createRow(rowNo);
		for (int i = 0; i < paymentResponseArray.length; i++) {

			Cell cell = row.createCell(i);
			cell.setCellValue(paymentResponseArray[i]);
		}
	}

	public void addCell(String cellString) {
		int rowCount = sheet.getLastRowNum();
		cellNo++;
		
			Row row =sheet.getRow(cellNo);
			Cell cell = row.createCell(5);
			cell.setCellValue(cellString);
		
	}

	public void closeFileWrite() {
		try {
			if(wb!=null||fout!=null) {
				wb.write(fout);
				fout.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readExcel() {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);
		
	}
	
	public void readExcel(String filePath) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(file);
			objFormulaEvaluator=new XSSFFormulaEvaluator(workbook);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);
		
	}

	public Map<String, String> fetchColumn() {
		Map<String, String> transactionReferNoMap = new HashMap<String, String>();

		int noOfRow = sheet.getLastRowNum();
		for (int i = 1; i <= noOfRow; i++) {
			Row row = sheet.getRow(i);
			String transactionReferNo = row.getCell(4).getStringCellValue();
			String mandateType = row.getCell(0).getStringCellValue();
			transactionReferNoMap.put(mandateType, transactionReferNo);
			System.out.println("Value : " + transactionReferNoMap.get(mandateType));
		}
		
		return transactionReferNoMap;
	}

	public Map<String,PaymentModel> fetchDataFromExcel(String excelFileName) {
		Map<String,PaymentModel> referNoPaymentObjMap=new LinkedHashMap<String, PaymentModel>();
		bulkTestDataFolder = objCommonCode.readConfig().getProperty("BulkTestData");
		readExcel(bulkTestDataFolder+"//"+excelFileName+".xlsx");
		int noOfRow=sheet.getLastRowNum();
		for(int i=1;i<=noOfRow;i++) {
			Row row=sheet.getRow(i);
			PaymentModel objPaymentModel=new PaymentModel();
			objPaymentModel.setMandateType(row.getCell(1).getStringCellValue());
			objPaymentModel.setAmount(objDefaultFormat.formatCellValue(row.getCell(2),objFormulaEvaluator));
			objPaymentModel.setBeneficiaryAccountNo(objDefaultFormat.formatCellValue(row.getCell(3),objFormulaEvaluator));
			objPaymentModel.setBeneficiaryIFSCCode(row.getCell(4).getStringCellValue());
			objPaymentModel.setBeneficiaryAccountHolderName(row.getCell(5).getStringCellValue());
			objPaymentModel.setRemitterName(row.getCell(6).getStringCellValue());
			referNoPaymentObjMap.put(objDefaultFormat.formatCellValue(row.getCell(0),objFormulaEvaluator), objPaymentModel);
		}
		return referNoPaymentObjMap;
	}
}
