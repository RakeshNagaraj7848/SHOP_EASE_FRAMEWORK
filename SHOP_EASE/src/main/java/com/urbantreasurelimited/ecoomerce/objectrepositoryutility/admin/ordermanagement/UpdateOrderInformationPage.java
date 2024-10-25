package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UpdateOrderInformationPage {

	public UpdateOrderInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table/tbody/tr[4]/td[2]")
	private WebElement statusText;

	@FindBy(xpath = "//table/tbody/tr[5]/td[2]")
	private WebElement remarkText;

	public void validatingStatusOfUpdatedOrder(String actualStatus) {

		Assert.assertTrue(statusText.getText().trim().contains(actualStatus),
				"actualStatus" + actualStatus + "' not found.");
	}

	public void validatingRemarkOfUpdatedOrder(String actualRemark) {

		Assert.assertTrue(remarkText.getText().trim().contains(actualRemark),
				"actualRemark" + actualRemark + "' not found.");
	}

}
