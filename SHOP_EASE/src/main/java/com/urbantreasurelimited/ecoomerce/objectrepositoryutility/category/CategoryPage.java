package com.urbantreasurelimited.ecoomerce.objectrepositoryutility.category;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;

public class CategoryPage {

	public CategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "category")
	private WebElement categoryNameTextField;

	@FindBy(name = "description")
	private WebElement descriptionTextField;

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement createButton;

	@FindBy(xpath = "//div[@class=\"alert alert-success\"]")
	private WebElement categoryCreatedSuccessfullMessage;

	public void createCategory(String categoryName, String description, WebDriver driver) {
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		adminHomePage.getCreateCategoryLink().click();
		categoryNameTextField.sendKeys(categoryName);
		descriptionTextField.sendKeys(description);
		createButton.click();
	}

	public void validatingCategoryCreatedSuccessfullMessageText(String expectedcategoryCreatedSuccessfullMessageText) {
		String actualCategoryCreatedSuccessfullMessageText = categoryCreatedSuccessfullMessage.getText();
		Assert.assertEquals(
				actualCategoryCreatedSuccessfullMessageText.contains(expectedcategoryCreatedSuccessfullMessageText),
				true, "CategoryNotCreatedSuccessfully");
	}

}
