package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.admin.ordermanagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class PendingOrdersPage {

	public PendingOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//input[@aria-controls=\"DataTables_Table_0\"]")
	private WebElement searchBar;

	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getEditIcon() {
		return editIcon;
	}

	@FindBy(xpath = "(//table/tbody/tr[*]/td[9])/descendant::i")
	private WebElement editIcon;
}
