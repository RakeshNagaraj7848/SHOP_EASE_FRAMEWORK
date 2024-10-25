package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.insertproduct;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class ManageProductPage {

	public ManageProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//table/tbody/tr[*]/td[2]")
	private List<WebElement> productNames;

	@FindBy(xpath = "//input[@aria-controls=\"DataTables_Table_0\"]")
	private WebElement searchBar;

	public WebElement getSearchBar() {
		return searchBar;
	}

	public void validatingInsertedProductName(String expectedProductName, WebDriver driver) {
		boolean found = false;

		// Trim the expected product name to avoid mismatch due to extra spaces
		expectedProductName = expectedProductName.trim();
		System.out.println("Number of products: " + productNames.size());
		System.out.println("Expected product name: '" + expectedProductName + "'");

		// Loop through actual product names
		for (WebElement actualProductName : productNames) {
			wLib.javascriptExecutorScrollIntoView(driver, actualProductName);
			String actualProductNameText = actualProductName.getText().trim(); // Trim to remove any unwanted spaces
			System.out.println("Scrolling to actual product name: '" + actualProductNameText + "'");

			// Check if the actual product name contains the expected name
			// (case-insensitive)
			if (actualProductNameText.toLowerCase().contains(expectedProductName.toLowerCase())) {
				System.out.println("Found inserted product: '" + actualProductNameText + "'");
				found = true;
				break; // Exit the loop once found
			}
		}

		// Assert and print informative message if the product was not found
		Assert.assertTrue(found, "Inserted product name '" + expectedProductName + "' not found.");
	}

}
