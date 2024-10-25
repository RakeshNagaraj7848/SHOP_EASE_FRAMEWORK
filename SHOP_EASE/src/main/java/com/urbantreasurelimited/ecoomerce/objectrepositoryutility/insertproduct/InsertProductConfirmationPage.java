package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.insertproduct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class InsertProductConfirmationPage {

	public InsertProductConfirmationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class=\"alert alert-success\"]")
	private WebElement insertProductSuccessfulConfirmationMessage;

	public void validatingInsertProductSuccessfulConfirmationMessage(String actualMessage) {
		Assert.assertTrue(insertProductSuccessfulConfirmationMessage.getText().trim().contains(actualMessage.trim()));
	}

}
