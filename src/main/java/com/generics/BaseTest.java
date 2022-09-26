package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Stopwatch;

import cucumber.api.Scenario;
import cucumber.api.java.After;

/*
 * This is the Base test-class which initialize the global environment and control the same.
 */
public class BaseTest extends Pojo {
	private Properties objConfig;
	private ChromeOptions objChromeOptions;

	/*
	 * This is a POJO class for initializing the web environment without the config
	 * properties.
	 */

	public Pojo initializeWebEnvironment() {
		if (this.getDriver() == null) {
			this.setObjUtilities(new Utilities(this));
			this.setExecutionTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			this.setStopwatch(Stopwatch.createStarted());
			this.loadConfigProperties();
			this.setDriver(initializeWebEnvironment(this.objConfig));
			this.setAfterClickwait(Integer.parseInt(this.objConfig.getProperty("AfterClickWait")));
			this.setScriptTimeoutWait(Integer.parseInt(this.objConfig.getProperty("ScriptTimeoutWait")));
			this.setWebDriverWait(new WebDriverWait(this.getDriver(),
					(long) Integer.parseInt(this.objConfig.getProperty("driver.WebDriverWait").trim())));
			this.setObjWrapperFunctions(new WrapperFunctions(this));
		}
		return this;
	}

	/*
	 * This is a POJO class tear-down class for web environment.
	 */

	@SuppressWarnings("deprecation")
	public void tearDownWebEnvironment(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) this.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			}
			CustomReporterHelper.getException().add(new CustomReporterHelper(scenario, this));
			this.getDriver().quit();
			setObjUtilities(null);
			this.getObjWrapperFunctions().waitFor(2);
			System.out.println("Kill Chrome Browser.!!!!!!");
			this.getStopwatch().stop();
		} catch (Exception var4) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} catch (IOException var3) {
				var3.printStackTrace();
			}
			var4.printStackTrace();
			if (System.getProperty("web.browser").trim().equalsIgnoreCase("IE")
					|| System.getProperty("web.browser").trim().equalsIgnoreCase("Chrome")) {
				this.killBrowserAndDriver(this.objConfig);
			}
		}
		this.setObjWrapperFunctions(null);
	}

	/*
	 * This is a POJO class for utilizing the Config properties.
	 */

	private void loadConfigProperties() {
		try {
			this.objConfig = new Properties();
			this.objConfig.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
			this.setObjConfig(this.objConfig);
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	/*
	 * This is a POJO class killing the browser and driver
	 */

	@After
	private void killBrowserAndDriver(Properties objConfig) {
		this.getDriver().quit();

	}
	
	

	/*
	 * This is a POJO class for initializing the web environment with the config
	 * properties.
	 */

	private WebDriver initializeWebEnvironment(Properties objConfig) {
		WebDriver webDriver = null;
		@ScriptMetaData(productionReady = true)
		boolean execRemote = objConfig.getProperty("ExecutionMode").trim().equalsIgnoreCase("remote");
		try {
			String browser = objConfig.getProperty("web.browser").trim().toLowerCase();
			String Headless = objConfig.getProperty("Api.browser").trim().toLowerCase();
			if (browser.equalsIgnoreCase("chrome")) {
				if (execRemote)
					webDriver = new RemoteWebDriver(DesiredCapabilities.chrome());
				
			 if (Headless.equalsIgnoreCase("true")) {
					System.setProperty("webdriver.chrome.driver", objConfig.getProperty("webdriver.chrome.driver").trim());					
					ChromeOptions chromeOptions = new ChromeOptions();  
					chromeOptions.addArguments("--headless");
					chromeOptions.addArguments("--window-size=1920,1200");
					webDriver = new ChromeDriver(chromeOptions);
				}
			 else {					
					System.setProperty("webdriver.chrome.driver", objConfig.getProperty("webdriver.chrome.driver").trim());					
					webDriver = new ChromeDriver();				
				}
				}
		
				
			 else if (browser.equalsIgnoreCase("firefox")) {
				if (execRemote)
					webDriver = new RemoteWebDriver(DesiredCapabilities.firefox());
				else {
					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + objConfig.getProperty("webdriver.firefox.driver").trim());
					webDriver = new FirefoxDriver();
				}
			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", objConfig.getProperty("webdriver.ie.driver").trim());
				webDriver = new InternetExplorerDriver();
			}
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(
					(long) Integer.parseInt(objConfig.getProperty("driver.implicitlyWait").trim()), TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(
					(long) Integer.parseInt(objConfig.getProperty("driver.pageLoadTimeout").trim()), TimeUnit.SECONDS);
			webDriver.manage().deleteAllCookies();
			return webDriver;
		} catch (Exception var4) {
			var4.printStackTrace();
			return null;
		}
	}

}