package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AdminHomePage {

	public AdminHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//b[@class=\"caret\"]")
	private WebElement adminDropDown;

	@FindBy(xpath = "//a[@class=\"collapsed\"]/descendant::i[@class=\"icon-chevron-down pull-right\"]")
	private WebElement orderManagementDropDown;

	@FindBy(xpath = "//a[@href=\"pending-orders.php\"]/descendant::i")
	private WebElement pendingOrdersLink;

	public WebElement getPendingOrdersLink() {
		return pendingOrdersLink;
	}

	public WebElement getOrderManagementDropDown() {
		return orderManagementDropDown;
	}

	@FindBy(xpath = "//a[@href=\"todays-orders.php\"]/descendant::i")
	private WebElement todayOrdersLink;

	@FindBy(xpath = "//a[contains(text(),'Create Category')]")
	private WebElement createCategoryLink;

	@FindBy(xpath = "//a[@href=\"subcategory.php\"]/descendant::i")
	private WebElement SubCategoryLink;

	@FindBy(xpath = "//a[@href=\"manage-products.php\"]/descendant::i")
	private WebElement manageProductsLink;

	public WebElement getManageProductsLink() {
		return manageProductsLink;
	}

	public WebElement getSubCategoryLink() {
		return SubCategoryLink;
	}

	public WebElement getInsertProductLink() {
		return insertProductLink;
	}

	@FindBy(xpath = "//a[@href=\"insert-product.php\"]/descendant::i")
	private WebElement insertProductLink;

	public WebElement getCreateCategoryLink() {
		return createCategoryLink;
	}

	public WebElement getTodayOrdersLink() {
		return todayOrdersLink;
	}

	public WebElement getAdminDropDown() {
		return adminDropDown;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutLink;

	@FindBy(xpath = "//span[contains(text(),'Shopping | Admin')]")
	private WebElement adminHomePageVerificationText;

	public WebElement getAdminHomePageVerificationText() {
		return adminHomePageVerificationText;
	}

	public void logoutAsAdmin() {
		adminDropDown.click();
		logoutLink.click();
	}

	public void validatingAdminHomePageText(String expectedAdminHomePageText) {
		String actualAdminHomePageText = adminHomePageVerificationText.getText();
		Assert.assertEquals(actualAdminHomePageText.contains(expectedAdminHomePageText), true,
				"Admin HomePage Not  verified successfully");
	}

}
