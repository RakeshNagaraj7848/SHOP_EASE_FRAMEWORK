package com.urbantreasurelimited.ecommerce.ordertest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;
import com.urbantreasurelimited.ecommerce.generic.fileutility.FileUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.UtilityClassObject;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement.PendingOrdersPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement.TodaysOrderPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement.UpdateOrderInformationPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement.UpdateOrderPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.cart.MyCartPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.UserHomePage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.login.AdminLoginPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.login.UserLoginPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.order.OrderHistoryPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.payment.PaymentMethodPage;

@Listeners(com.urbantreasurelimited.ecommerce.generic.listenerutility.ListenerImplementationClass.class)
public class OrderTest {
	@Test
	public void testOrderCreationAndAdminApprovalVerification()
			throws EncryptedDocumentException, IOException, InterruptedException

	{

		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriver driver;

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String userEmailId = fLib.getDataFromPropertiesFile("userEmailId");
		String userPassword = fLib.getDataFromPropertiesFile("userPassword");
		String productName = eLib.getDataFromExcel("homePage", 1, 2);
		String adminUSERNAME = fLib.getDataFromPropertiesFile("adminUsername");
		String adminPASSWORD = fLib.getDataFromPropertiesFile("adminPassword");

		if (BROWSER.equals("chrome")) {

			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {

			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		UtilityClassObject.setDriver(driver);

		UserLoginPage userLoginPage = new UserLoginPage(driver);
		UserHomePage userHomePage = new UserHomePage(driver);
		MyCartPage myCartPage = new MyCartPage(driver);
		PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		TodaysOrderPage todaysOrderPage = new TodaysOrderPage(driver);

		driver.get(URL);
		wLib.maximizeBrowserWindow(driver);
		wLib.pageLoadTimeout(driver);
		wLib.waitForPageLoad(driver);

		userLoginPage.loginToApplicationAsUser(userEmailId, userPassword, driver);

		userHomePage.addProductToCart(productName, driver);

		wLib.javascriptExecutorScrollIntoView(driver, myCartPage.getProceedToCheckoutButton());
		myCartPage.getProceedToCheckoutButton().click();

		paymentMethodPage.getSubmitButton().click();

		orderHistoryPage.validatingOrderedProductName(productName, driver);

		userHomePage.logoutFromApplicationAsUser(driver);

		adminLoginPage.loginToApplicationAsAdmin(adminUSERNAME, adminPASSWORD, driver);

		adminHomePage.getOrderManagementDropDown().click();
		adminHomePage.getTodayOrdersLink().click();

		todaysOrderPage.validatingTodaysOrderedProductName(productName, driver);
		wLib.javascriptExecutorScrollIntoView(driver, adminHomePage.getLogoutLink());

		adminHomePage.logoutAsAdmin();
		driver.quit();
	}

	@Test
	public void testPlaceOrderForExistingProductInCartTest()
			throws EncryptedDocumentException, IOException, InterruptedException

	{

		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriver driver;

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String userEmailId = fLib.getDataFromPropertiesFile("userEmailId");
		String userPassword = fLib.getDataFromPropertiesFile("userPassword");
		String productName = eLib.getDataFromExcel("cartPage", 1, 2);

		if (BROWSER.equals("chrome")) {

			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {

			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		UtilityClassObject.setDriver(driver);

		UserLoginPage userLoginPage = new UserLoginPage(driver);
		UserHomePage userHomePage = new UserHomePage(driver);
		MyCartPage myCartPage = new MyCartPage(driver);
		PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);

		driver.get(URL);
		wLib.maximizeBrowserWindow(driver);
		wLib.pageLoadTimeout(driver);
		wLib.waitForPageLoad(driver);

		userLoginPage.loginToApplicationAsUser(userEmailId, userPassword, driver);

		userHomePage.addProductToCart(productName, driver);

		wLib.javascriptExecutorScrollIntoView(driver, myCartPage.getProceedToCheckoutButton());
		myCartPage.getProceedToCheckoutButton().click();

		paymentMethodPage.getSubmitButton().click();

		orderHistoryPage.validatingOrderedProductName(productName, driver);
		userHomePage.logoutFromApplicationAsUser(driver);
		driver.quit();

	}

	@Test
	public void adminApprovalOfUserCreatedOrderTest()
			throws EncryptedDocumentException, IOException, InterruptedException

	{

		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriver driver;

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String userEmailId = fLib.getDataFromPropertiesFile("userEmailId");
		String userPassword = fLib.getDataFromPropertiesFile("userPassword");
		String productName = eLib.getDataFromExcel("orderPage", 1, 2);
		String adminUSERNAME = fLib.getDataFromPropertiesFile("adminUsername");
		String adminPASSWORD = fLib.getDataFromPropertiesFile("adminPassword");
		String status = eLib.getDataFromExcel("orderPage", 1, 3);
		String remark = eLib.getDataFromExcel("orderPage", 1, 4);

		if (BROWSER.equals("chrome")) {

			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {

			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		UtilityClassObject.setDriver(driver);

		UserLoginPage userLoginPage = new UserLoginPage(driver);
		UserHomePage userHomePage = new UserHomePage(driver);
		MyCartPage myCartPage = new MyCartPage(driver);
		PaymentMethodPage paymentMethodPage = new PaymentMethodPage(driver);
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		PendingOrdersPage pendingOrdersPage = new PendingOrdersPage(driver);
		UpdateOrderPage updateOrderPage = new UpdateOrderPage(driver);
		UpdateOrderInformationPage updateOrderInformationPage = new UpdateOrderInformationPage(driver);

		driver.get(URL);
		wLib.maximizeBrowserWindow(driver);
		wLib.pageLoadTimeout(driver);
		wLib.waitForPageLoad(driver);

		userLoginPage.loginToApplicationAsUser(userEmailId, userPassword, driver);
		userHomePage.addProductToCartBySearchingInSearchBar(productName, driver);

		wLib.javascriptExecutorScrollIntoView(driver, myCartPage.getProceedToCheckoutButton());
		myCartPage.getProceedToCheckoutButton().click();

		paymentMethodPage.getSubmitButton().click();

		// orderHistoryPage.validatingOrderedProductName(productName,driver);
		userHomePage.logoutFromApplicationAsUser(driver);

		adminLoginPage.loginToApplicationAsAdmin(adminUSERNAME, adminPASSWORD, driver);

		adminHomePage.getOrderManagementDropDown().click();
		adminHomePage.getPendingOrdersLink().click();

		pendingOrdersPage.getSearchBar().sendKeys(productName);
		pendingOrdersPage.getEditIcon().click();

		wLib.select(updateOrderPage.getSelectStatusDropDown(), status);
		updateOrderPage.getRemarkTextArea().sendKeys(remark);
		updateOrderPage.getUpdateButton().click();
		wLib.waitForAlertPresent(driver);
		wLib.switchToAlertAndAccept(driver);

		updateOrderInformationPage.validatingStatusOfUpdatedOrder(status);
		updateOrderInformationPage.validatingRemarkOfUpdatedOrder(remark);
		driver.quit();
	}

}
