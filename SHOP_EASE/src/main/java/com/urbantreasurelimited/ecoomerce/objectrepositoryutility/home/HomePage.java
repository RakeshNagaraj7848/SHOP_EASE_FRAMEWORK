package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//a[text()='Login']")
	private WebElement loginLink;

	public WebElement getLoginLink() {
		return loginLink;
	}

	public WebElement getAdminLoginLink() {
		return adminLoginLink;
	}

	@FindBy(xpath = "//a[text()='Admin Login']")
	private WebElement adminLoginLink;

}
