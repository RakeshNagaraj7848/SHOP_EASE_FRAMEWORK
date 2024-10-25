package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class UserHomePage {

	public UserHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement userLogoutLink;

	public WebElement getHomeTabLink() {
		return homeTabLink;
	}

	@FindBy(xpath = "//a[text()='Home']")
	private WebElement homeTabLink;

	@FindBy(name = "product")
	private WebElement searchBar;

	@FindBy(xpath = "//button[@class='search-button']")
	private WebElement searchIcon;

	public void addProductToCart(String productName, WebDriver driver) throws InterruptedException {
		homeTabLink.click();
		WebElement productAddToCartButton = driver
				.findElement(By.xpath("(//div[@class='product']/descendant::a[contains(text(),'" + productName
						+ "')])[1]/../../../descendant::a[text()='Add to Cart']"));
		wLib.javascriptExecutorScrollIntoView(driver, productAddToCartButton);
		productAddToCartButton.click();
		wLib.waitForAlertPresent(driver);
		wLib.switchToAlertAndAccept(driver);
	}

	public void addProductToCartBySearchingInSearchBar(String productName, WebDriver driver)
			throws InterruptedException {
		searchBar.sendKeys(productName);

		searchIcon.click();
		WebElement productAddToCartButton = driver
				.findElement(By.xpath("//div[@class=\"product\"]/descendant::button[text()='Add to cart']"));
		wLib.javascriptExecutorScrollIntoView(driver, productAddToCartButton);

		productAddToCartButton.click();

		wLib.waitForAlertPresent(driver);
		wLib.switchToAlertAndAccept(driver);
	}

	public void addProductToCartBySelectingCategory(String categoryName, String productName, WebDriver driver)
			throws InterruptedException {

		homeTabLink.click();

		WebElement categoryElemenet = driver
				.findElement(By.xpath("//div[@class=\"header-nav animate-dropdown\"]/descendant::a[contains(text(),'"
						+ categoryName + "')]"));

		categoryElemenet.click();

		WebElement productAddToCartButton = driver.findElement(By.xpath(
				"(//a[contains(text(),'" + productName + "')]/../../../descendant::button[text()='Add to cart'])[1]"));
		wLib.javascriptExecutorScrollIntoView(driver, productAddToCartButton);

		productAddToCartButton.click();

		wLib.waitForAlertPresent(driver);
		wLib.switchToAlertAndAccept(driver);
	}

	public void logoutFromApplicationAsUser(WebDriver driver) {
		wLib.javascriptExecutorScrollIntoView(driver, userLogoutLink);
		userLogoutLink.click();
	}

}
