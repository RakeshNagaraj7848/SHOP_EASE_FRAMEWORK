package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class TodaysOrderPage {

	public TodaysOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//table/tbody/tr[*]/td[5]")
	private List<WebElement> productsName;

	public void validatingTodaysOrderedProductName(String orderedProductName, WebDriver driver) {
		boolean found = false;

		System.out.println("Number of products: " + productsName.size());

		for (WebElement actualProductName : productsName) {
			wLib.javascriptExecutorScrollIntoView(driver, actualProductName);
			System.out.println("Scrolling to: " + actualProductName.getText());

			if (actualProductName.getText().toLowerCase().contains(orderedProductName.toLowerCase())) {
				System.out.println("Found ordered product: " + actualProductName.getText());
				found = true;
				break;
			}
		}

		// Final assertion with an informative message if not found
		Assert.assertTrue(found, "Ordered product name '" + orderedProductName + "' not found.");
	}

}
