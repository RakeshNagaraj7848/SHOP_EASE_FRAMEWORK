package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.subcategory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;

public class SubCategoryPage {

	public SubCategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(name = "category")
	private WebElement categoryDropDown;

	@FindBy(xpath = "//input[@name=\"subcategory\"]")
	private WebElement subCategoryNameTextField;

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement createButton;

	public void createSubCategory(String categoryName, String subCategoryName, WebDriver driver) {
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		adminHomePage.getSubCategoryLink().click();
		wLib.select(categoryDropDown, categoryName);
		subCategoryNameTextField.sendKeys(subCategoryName);
		createButton.click();

	}

}
