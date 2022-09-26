package com.stepDefinition.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This program read date values from XLSX file in Java using Apache POI.
 * 
 * @author WINDOWS 8
 *
 */
public class ExcelDateReader {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		readFromExcel("D:\\ShreeyaFolder\\RBLTestDataAutomation\\BrithDay.xlsx");
		writeIntoExcel("D:\\ShreeyaFolder\\RBLTestDataAutomation\\BrithDay.xlsx", 4);
		readFromExcel("D:\\ShreeyaFolder\\RBLTestDataAutomation\\BrithDay.xlsx");
	}

	public static void readFromExcel(String file) throws IOException {
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet myExcelSheet = myExcelBook.getSheet("Birthdays");
		XSSFRow row = myExcelSheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum()+1; i++) {
			row = myExcelSheet.getRow(i);
			if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_STRING) {

				String name = row.getCell(0).getStringCellValue();
				System.out.println("NAME : " + name);
			}

			if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				Date birthdate = row.getCell(1).getDateCellValue();
				System.out.println("DOB :" + birthdate);
			}
		}
		myExcelBook.close();

	}

	@SuppressWarnings("deprecation")
	public static void writeIntoExcel(String file, int rowNo) throws FileNotFoundException, IOException {
		Workbook book = new XSSFWorkbook();
		Sheet sheet = book.createSheet("Birthdays");
		Row row = sheet.createRow(rowNo);

		Cell name = row.createCell(0);
		name.setCellValue("Apurva");

		Cell birthdate = row.createCell(1);
		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		birthdate.setCellStyle(dateStyle);

		birthdate.setCellValue(new Date(115, 10, 10));

		sheet.autoSizeColumn(1);

		book.write(new FileOutputStream(file, true));
		book.close();
	}
}
