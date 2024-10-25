package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.cart;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class MyCartPage {

	public MyCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//button[text()='PROCCED TO CHEKOUT']")
	private WebElement proceedToCheckoutButton;

	public WebElement getProceedToCheckoutButton() {
		return proceedToCheckoutButton;
	}

	@FindBy(xpath = "//table/tbody/tr[*]/td[@class=\"cart-product-name-info\"]/descendant::a")
	private List<WebElement> productNames;

	@FindBy(xpath = "//a[text()='Apple iPhone 16 (Gold, 128 GB)']/parent::h4/../../../descendant::i[@class=\"icon fa fa-sort-asc\"]/..")
	private WebElement increaseIcon;

	@FindBy(xpath = "//input[@value=\"Update shopping cart\"]")
	private WebElement updateShoppingCartButton;

	public WebElement getUpdateShoppingCartButton() {
		return updateShoppingCartButton;
	}

	public WebElement getIncreaseIcon() {
		return increaseIcon;
	}

	public void removeProductFromCart(String productName, WebDriver driver) throws InterruptedException {
		WebElement removeProductCheckBoxElement = driver.findElement(By.xpath(
				"//table/tbody/tr[*]/td[@class=\"romove-item\" ]/following-sibling::td[@class=\"cart-product-name-info\"]/h4/a[text()='"
						+ productName
						+ "']/parent::h4/parent::td/preceding-sibling::td[@class=\"romove-item\"]/input[@type=\"checkbox\"]"));
		wLib.javascriptExecutorScrollIntoView(driver, removeProductCheckBoxElement);

		removeProductCheckBoxElement.click();

		wLib.javascriptExecutorScrollIntoView(driver, updateShoppingCartButton);
		updateShoppingCartButton.click();

		wLib.waitForAlertPresent(driver);
		wLib.switchToAlertAndAccept(driver);
		wLib.waitForAlertPresent(driver);
		wLib.switchToAlertAndAccept(driver);
	}

	public void validatingProductNameAddedInCart(String expectedProductName, WebDriver driver) {
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
				System.out.println("Found added  product: '" + actualProductNameText + "'");
				found = true;
				break; // Exit the loop once found
			}
		}

		// Assert and print informative message if the product was not found
		Assert.assertTrue(found, "addded product name '" + expectedProductName + "' not found.");
	}

	public void validateRemovedProductNameFromCart(String deletedProductName, WebDriver driver) {
		boolean found = false;

		// Loop through actual product names in the cart
		for (WebElement actualProductName : productNames) {
			wLib.javascriptExecutorScrollIntoView(driver, actualProductName);
			String actualProductNameText = actualProductName.getText().trim(); // Trim to remove any unwanted spaces
			System.out.println("Scrolling to actual product name: '" + actualProductNameText + "'");

			// Check if the actual product name matches the deleted product name
			// (case-insensitive)
			if (actualProductNameText.equalsIgnoreCase(deletedProductName)) {
				System.out.println("Deleted product found: '" + deletedProductName + "'");
				found = true;
				break; // Exit the loop once the product is found
			}
		}

		// Assert that the deleted product was not found in the cart
		Assert.assertFalse(found, "Deleted product name '" + deletedProductName
				+ "' was found in the cart, meaning it was not removed successfully.");
	}
}
