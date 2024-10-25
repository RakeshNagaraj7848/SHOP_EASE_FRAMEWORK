package com.urbantreasurelimited.ecommerce.generic.baseclass;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.urbantreasurelimited.ecommerce.generic.databaseutility.DataBaseUtility;
import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;
import com.urbantreasurelimited.ecommerce.generic.fileutility.FileUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.JavaUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.UtilityClassObject;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.login.AdminLoginPage;

public class BaseClass {

	public DataBaseUtility dLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriver driver;
	public static WebDriver sdriver;

	@BeforeSuite
	public void bsConfig() {

		dLib.getDataBaseConnection();

	}

	@BeforeClass
	public void bcConfig() throws IOException {

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		if (BROWSER.equals("chrome")) {

			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {

			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);

	}

	@BeforeMethod
	public void bmConfig() throws IOException {
		String URL = fLib.getDataFromPropertiesFile("url");
		String adminUSERNAME = fLib.getDataFromPropertiesFile("adminUsername");
		String adminPASSWORD = fLib.getDataFromPropertiesFile("adminPassword");
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		driver.get(URL);
		wLib.maximizeBrowserWindow(driver);
		wLib.pageLoadTimeout(driver);
		wLib.waitForPageLoad(driver);
		adminLoginPage.loginToApplicationAsAdmin(adminUSERNAME, adminPASSWORD, driver);

	}

	@AfterMethod
	public void amConfig() {
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		adminHomePage.logoutAsAdmin();
	}

	@AfterClass
	public void acConfig() {
		driver.quit();

	}

	@AfterSuite
	public void asConfig() {
		dLib.closeDataBaseConnection();

	}
}