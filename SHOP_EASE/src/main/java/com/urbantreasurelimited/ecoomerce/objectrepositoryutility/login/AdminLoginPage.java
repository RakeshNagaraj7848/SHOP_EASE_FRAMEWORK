package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.HomePage;

public class AdminLoginPage {

	public AdminLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "inputEmail")
	private WebElement usernameTextField;

	@FindBy(id = "inputPassword")
	private WebElement passwordTextField;

	@FindBy(name = "submit")
	private WebElement loginButton;

	public void loginToApplicationAsAdmin(String userName, String password, WebDriver driver) {
		HomePage homePage = new HomePage(driver);
		homePage.getAdminLoginLink().click();
		usernameTextField.sendKeys(userName);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}

}
