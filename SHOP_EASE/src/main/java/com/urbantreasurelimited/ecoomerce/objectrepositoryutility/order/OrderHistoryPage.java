package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.order;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class OrderHistoryPage {

	public OrderHistoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//table/tbody/tr[*]/td[@class='cart-product-name-info']")
	private List<WebElement> productsName;

	public void validatingOrderedProductName(String expectedProductName, WebDriver driver) {
		boolean found = false;

		System.out.println("Number of products: " + productsName.size());

		for (WebElement actualProductName : productsName) {
			wLib.javascriptExecutorScrollIntoView(driver, actualProductName);
			String actualText = actualProductName.getText().trim().toLowerCase(); // Trim spaces and lower case
			String expectedText = expectedProductName.trim().toLowerCase(); // Trim spaces and lower case

			System.out.println("Scrolling to: " + actualText);
			System.out.println(" expected result is" + expectedText);

			if (actualText.equalsIgnoreCase(expectedText)) {
				System.out.println("Found product: " + actualProductName.getText());
				found = true;
				break;
			}

		}

		Assert.assertTrue(found, "Expected product name '" + expectedProductName + "' not found.");
	}

}
