package com.urbantreasurelimited.ecommerce.carttest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.urbantreasurelimited.ecommerce.generic.dataproviderutility.DataProviderTest;
import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;
import com.urbantreasurelimited.ecommerce.generic.fileutility.FileUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.UtilityClassObject;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.cart.MyCartPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.UserHomePage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.login.UserLoginPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.order.OrderHistoryPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.payment.PaymentMethodPage;

@Listeners(com.urbantreasurelimited.ecommerce.generic.listenerutility.ListenerImplementationClass.class)
public class CartTest {

	@Test(dataProvider = "getGenericData", dataProviderClass = DataProviderTest.class)
	public void addMultipleProductsToCartTest(String actualProductName)
			throws EncryptedDocumentException, IOException, InterruptedException

	{

		if (actualProductName == null || actualProductName.isEmpty()) {
			throw new IllegalArgumentException("Product name is null or empty");
		}

		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		WebDriver driver;

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String userEmailId = fLib.getDataFromPropertiesFile("userEmailId");
		String userPassword = fLib.getDataFromPropertiesFile("userPassword");

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

		UserHomePage userHomePage = new UserHomePage(driver);
		UserLoginPage userLoginPage = new UserLoginPage(driver);
		MyCartPage myCartPage = new MyCartPage(driver);

		driver.get(URL);
		wLib.maximizeBrowserWindow(driver);
		wLib.pageLoadTimeout(driver);
		wLib.waitForPageLoad(driver);

		userLoginPage.loginToApplicationAsUser(userEmailId, userPassword, driver);

		userHomePage.addProductToCartBySearchingInSearchBar(actualProductName, driver);

		myCartPage.validatingProductNameAddedInCart(actualProductName, driver);
		userHomePage.logoutFromApplicationAsUser(driver);
		driver.quit();
	}

	@Test
	public void addUpdateAndDeleteMultipleProductsInCartTest()
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
		String productName1 = eLib.getDataFromExcel("addToCart", 5, 2);
		String productName2 = eLib.getDataFromExcel("addToCart", 6, 2);
		String categoryName = eLib.getDataFromExcel("addToCart", 5, 3);
		String categoryproductName3 = eLib.getDataFromExcel("addToCart", 5, 4);
		String deleteProductName = eLib.getDataFromExcel("addToCart", 5, 5);

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

		userHomePage.addProductToCart(productName1, driver);

		myCartPage.getIncreaseIcon().click();

		myCartPage.getIncreaseIcon().click();

		wLib.javascriptExecutorScrollIntoView(driver, myCartPage.getUpdateShoppingCartButton());
		myCartPage.getUpdateShoppingCartButton().click();
		wLib.switchToAlertAndAccept(driver);
		myCartPage.validatingProductNameAddedInCart(productName1, driver);

		userHomePage.addProductToCartBySearchingInSearchBar(productName2, driver);
		myCartPage.validatingProductNameAddedInCart(productName2, driver);

		userHomePage.addProductToCartBySelectingCategory(categoryName, categoryproductName3, driver);
		myCartPage.validatingProductNameAddedInCart(categoryproductName3, driver);

		myCartPage.removeProductFromCart(deleteProductName, driver);
		myCartPage.validateRemovedProductNameFromCart(deleteProductName, driver);

		wLib.javascriptExecutorScrollIntoView(driver, myCartPage.getProceedToCheckoutButton());
		myCartPage.getProceedToCheckoutButton().click();

		paymentMethodPage.getSubmitButton().click();

		orderHistoryPage.validatingOrderedProductName(productName1, driver);
		orderHistoryPage.validatingOrderedProductName(productName2, driver);

		userHomePage.logoutFromApplicationAsUser(driver);
		driver.quit();
	}

}
