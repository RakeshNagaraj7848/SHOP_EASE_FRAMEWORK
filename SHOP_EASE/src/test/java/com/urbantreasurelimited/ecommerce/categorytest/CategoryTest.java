package com.urbantreasurelimited.ecommerce.categorytest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.urbantreasurelimited.ecommerce.generic.baseclass.BaseClass;
import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.JavaUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.category.CategoryPage;

@Listeners(com.urbantreasurelimited.ecommerce.generic.listenerutility.ListenerImplementationClass.class)
public class CategoryTest extends BaseClass {
	@Test
	public void createCategoryTest() throws EncryptedDocumentException, IOException

	{
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		String expectedcategoryCreatedSuccessfullMessageText = eLib.getDataFromExcel("categoryPage", 1, 4);
		String categoryName = eLib.getDataFromExcel("categoryPage", 1, 2) + jLib.getRandomNumber();
		String description = eLib.getDataFromExcel("categoryPage", 1, 3);

		CategoryPage categoryPage = new CategoryPage(driver);
		categoryPage.createCategory(categoryName, description, driver);
		categoryPage.validatingCategoryCreatedSuccessfullMessageText(expectedcategoryCreatedSuccessfullMessageText);
	}
}
