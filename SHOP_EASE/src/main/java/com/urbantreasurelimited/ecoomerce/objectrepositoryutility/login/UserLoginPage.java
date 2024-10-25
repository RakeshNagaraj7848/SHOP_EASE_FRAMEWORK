package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.HomePage;

public class UserLoginPage {

	public UserLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(name = "email")
	private WebElement emailAddressTextField;

	@FindBy(id = "exampleInputPassword1")
	private WebElement passwordTextField;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;

	public void loginToApplicationAsUser(String emailAddress, String password, WebDriver driver) {
		HomePage homePage = new HomePage(driver);
		homePage.getLoginLink().click();
		emailAddressTextField.sendKeys(emailAddress);
		passwordTextField.sendKeys(password);
		wLib.javascriptExecutorScrollIntoView(driver, loginButton);
		loginButton.click();
	}

}
