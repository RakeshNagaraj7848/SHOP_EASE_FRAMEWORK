package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.insertproduct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;

public class InsertProductPage {

	public InsertProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(name = "category")
	private WebElement categoryDropDown;

	@FindBy(name = "subcategory")
	private WebElement subCategoryDropDown;

	@FindBy(name = "productName")
	private WebElement productNameTextField;

	@FindBy(name = "productCompany")
	private WebElement productCompanyNameTextField;

	@FindBy(name = "productpricebd")
	private WebElement productPriceBeforeDiscountTextField;

	@FindBy(name = "productprice")
	private WebElement productPriceAfterDiscountTextField;

	@FindBy(xpath = "//div[@contenteditable=\"true\"]")
	private WebElement productDescriptionTextArea;

	@FindBy(name = "productShippingcharge")
	private WebElement productShippingChargeTextField;

	@FindBy(name = "productAvailability")
	private WebElement productAvailabilityDropDown;

	@FindBy(name = "productimage1")
	private WebElement productImage1ChoosenButton;

	@FindBy(name = "productimage2")
	private WebElement productImage2ChoosenButton;

	@FindBy(name = "productimage3")
	private WebElement productImage3ChoosenButton;

	@FindBy(xpath = "//button[text()='Insert']")
	private WebElement insertButton;

	public void inserProduct(String categoryName, String subCategoryName, String productName, String productCompanyName,
			String productPriceBeforeDiscount, String productPriceAfterDiscount, String productDescription,
			String productShippingCharge, String productAvailability, String productImage1Path,
			String productImage2Path, String productImage3Path, WebDriver driver) {
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		adminHomePage.getInsertProductLink().click();
		wLib.select(categoryDropDown, categoryName);
		wLib.select(subCategoryDropDown, subCategoryName);
		productNameTextField.sendKeys(productName);
		productCompanyNameTextField.sendKeys(productCompanyName);
		productPriceBeforeDiscountTextField.sendKeys(productPriceBeforeDiscount);
		productPriceAfterDiscountTextField.sendKeys(productPriceAfterDiscount);
		wLib.javascriptExecutorScrollIntoView(driver, productDescriptionTextArea);
		productDescriptionTextArea.sendKeys(productDescription);
		wLib.javascriptExecutorScrollIntoView(driver, productShippingChargeTextField);
		productShippingChargeTextField.sendKeys(productShippingCharge);
		wLib.javascriptExecutorScrollIntoView(driver, productAvailabilityDropDown);
		wLib.select(productAvailabilityDropDown, productAvailability);

		wLib.javascriptExecutorScrollIntoView(driver, productImage1ChoosenButton);
		productImage1ChoosenButton.sendKeys(productImage1Path);

		wLib.javascriptExecutorScrollIntoView(driver, productImage2ChoosenButton);
		productImage2ChoosenButton.sendKeys(productImage2Path);

		wLib.javascriptExecutorScrollIntoView(driver, productImage3ChoosenButton);
		productImage3ChoosenButton.sendKeys(productImage3Path);
		insertButton.click();

	}
}
