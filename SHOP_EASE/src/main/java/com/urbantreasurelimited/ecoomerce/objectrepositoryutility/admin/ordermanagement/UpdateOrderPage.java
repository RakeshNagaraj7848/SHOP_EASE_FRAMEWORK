package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateOrderPage {

	public UpdateOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "status")
	private WebElement selectStatusDropDown;

	public WebElement getSelectStatusDropDown() {
		return selectStatusDropDown;
	}

	public WebElement getRemarkTextArea() {
		return remarkTextArea;
	}

	public WebElement getUpdateButton() {
		return updateButton;
	}

	@FindBy(name = "remark")
	private WebElement remarkTextArea;

	@FindBy(xpath = "//input[@value=\"update\"]")
	private WebElement updateButton;

}
