package com.stepDefinition.IMPS;

import com.PageFactory.API.JSonPage;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.generics.ScriptMetaData;
import com.generics.Utilities;
import com.generics.ValidationUtils;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import cucumber.api.java.en.Then;
import net.minidev.json.parser.ParseException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
@ScriptMetaData(productionReady = true)
public class IMPS_Json {
	private Pojo objPojo;
	private String TCID = "";
	private Collection<String> Tagname ;
	private JSonPage objJSonPage;
	String JsonPath = (System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\");
	String XmlSinglePath = (System.getProperty("user.dir") + "\\src\\test\\resources\\ApiTestData\\TC_01.xml");
	private String BaseUri;
	private File filepath;
	private String testData;
	private Response response;
	private Hashtable<String, String> objHashTable = new Hashtable<String, String>();
	@ScriptMetaData(productionReady=true)
	private com.jayway.restassured.path.json.JsonPath jsonPathEvaluator;
	public IMPS_Json(BaseTest pojo) {
		objPojo = pojo.initializeWebEnvironment();
		objJSonPage = new JSonPage(objPojo);
	}

	@ScriptMetaData(productionReady = true)
	@Before
	public void initializeScenario(Scenario scenario)  {
		TCID = scenario.getName();
		Tagname=scenario.getSourceTagNames();
		
	}
	/**
	 * @ScriptName : Excel Sheet Test Data Loader
	 * @Description :Get url From Config properties file
	 * @Author : Vikash Thakur.
	 **/
	@ScriptMetaData(productionReady = true)
	@Given("I am requesting {string}")
	public void i_am_requesting(String arg1) {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseUri = prop.getProperty(arg1);
		System.out.println(BaseUri);
	}

	/**
	 * @ScriptName : Excel Sheet Test Data Loader
	 * @Description :Load test Data and get testcase details under excel sheet
	 * @Author : Vikash Thakur.
	 **/
	@ScriptMetaData(productionReady = true)
	@Given("Load TestData Form Specific Api sheet {string}")
	public void load_TestData_Form_Specific_Api_sheet(String string) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/Excel/TestData.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(string);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(TCID)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
									objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
											sheet.getRow(j).getCell(i).getStringCellValue()));
							System.out.print(sheet);
						} catch (IllegalStateException e) {
							try {
								objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
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
	 * @ScriptName : Replace base json file to run time json file
	 * @Description :Replace base json file to run time json file
	 * @Author : Vikash Thakur.
	 **/
	@ScriptMetaData(productionReady = true)
	@Given("Base Test {string} data Replace with RunTime Test Data{string}")
	public void base_Test_data_Replace_with_RunTime_Test_Data(String string, String string2) throws IOException {
		String DynamicfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\";
		String Filepath1 = System.getProperty("user.dir") + "\\src\\test/resources\\ApiJsonData\\JsonTestData.json";
		filepath = new File(DynamicfilePath + string);
		FileInputStream FileRead = new FileInputStream(filepath);
		FileOutputStream FileWrite = new FileOutputStream(Filepath1);
		System.out.print("File Is Copied");
		int c;
		while ((c = FileRead.read()) != -1)
			FileWrite.write((char) c);
		FileRead.close();
		FileWrite.close();
	}
	@ScriptMetaData(productionReady = true)
	@Then("Create Input File for json from Excel Sheet")
	public void create_Input_File_for_json_from_Excel_Sheet() throws IOException, ParseException {
		testData = objHashTable.get("Name");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromJsonInputFile(testData);
		}
	}
	@ScriptMetaData(productionReady = true)
	@Then("I go to {string} and {string} Api Request Saved in Json File {string}without charset")
	public void i_go_to_and_Api_Request_Saved_in_Json_File_without_charset(String string, String string2,
			String string3) {
		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByLineJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.print(BaseUri + string);
		RequestSpecification httprequest = RestAssured.given().config(config).accept("application/json")
				.header("Content-Type", "application/json").body(file).log().all();
		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.post(BaseUri + string);
		}
		if (P1.equalsIgnoreCase(string2)) {
			response = httprequest.put(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			response = httprequest.delete(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}
		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print(response.asString());
		response.jsonPath();
	}
	@ScriptMetaData(productionReady = true)
	@Then("Save Final Response in {string} output File.")
	public void save_Final_Response_in_output_File(String string) throws IOException {
		FileWriter file = new FileWriter(JsonPath + string);
		System.out.print(response.asString());
		String json = response.asString();
		System.out.print(json);
		file.write(json);
		file.flush();
		file.close();
	}
	@ScriptMetaData(productionReady = true)
	@Then("I compare Json Schema file {string} with Response File {string}.")
	public void i_compare_Json_Response_file_with_Schema_File(String Schema, String JsonReponse) throws IOException {
		File Schemafile = new File(JsonPath + Schema);
		File jsonFile = new File(JsonPath + JsonReponse);
		String Value = "This is  ";
		BufferedReader reader = new BufferedReader(new FileReader(Schemafile));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String Is = System.getProperty("line.seprator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(Is);
		}
		// delete last new line seprator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();
		String content = stringBuilder.toString();
		response.then().assertThat().body(matchesJsonSchema(content));
		try {
			if (ValidationUtils.isJsonValid(Schemafile, jsonFile)) {
				Value = Value + "Valid";
				System.out.print(Value);
			} else {
				Value = Value + "No Valid";
				System.out.print("Not Valid");
			}
		} catch (ProcessingException | IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(Value, "This Is Valid ");
	}
	/**
	 * @ScriptName : Verify response value
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/

	@Then("I am validating object {string} for string value {string} For {string}.")
	public void i_am_validating_object_for_string_value_For(String xpath, String data, String Module) {
		String value = jsonPathEvaluator.get(xpath);
		System.out.print(value);
		Assert.assertEquals(data, value.trim());
		objPojo.getObjUtilities().logReporter("Verify the response value in" + Module + " Api", data, value.trim(),
				value.trim().equals(data));
		objPojo.getObjUtilities().setDataPool("RuntimeValue", value);
		String testdata=objPojo.getObjUtilities().dpString("RuntimeValue");
	}
	
}
