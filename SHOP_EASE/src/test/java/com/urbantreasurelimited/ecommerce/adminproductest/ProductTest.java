package com.urbantreasurelimited.ecommerce.adminproductest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.urbantreasurelimited.ecommerce.constantspath.IPathConstants;
import com.urbantreasurelimited.ecommerce.generic.baseclass.BaseClass;
import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;
import com.urbantreasurelimited.ecommerce.generic.fileutility.FileUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.JavaUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.category.CategoryPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.insertproduct.InsertProductConfirmationPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.insertproduct.InsertProductPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.insertproduct.ManageProductPage;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.subcategory.SubCategoryPage;

@Listeners(com.urbantreasurelimited.ecommerce.generic.listenerutility.ListenerImplementationClass.class)
public class ProductTest extends BaseClass {
	@Test
	public void insertProductAndVerifyInsertedProductTest() throws EncryptedDocumentException, IOException

	{
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		FileUtility fLib = new FileUtility();

		String expectedAdminHomePageText = eLib.getDataFromExcel("adminHomePage", 1, 2);
		String categoryName = eLib.getDataFromExcel("productPage", 1, 2) + jLib.getRandomNumber();
		String description = eLib.getDataFromExcel("productPage", 1, 3);
		String subCategoryName = eLib.getDataFromExcel("productPage", 1, 4);
		String productName = eLib.getDataFromExcel("productPage", 1, 5) + jLib.getRandomNumber();
		String productCompanyName = eLib.getDataFromExcel("productPage", 1, 6);
		String productPriceBeforeDiscount = eLib.getDataFromExcel("productPage", 1, 7);
		String productPriceAfterDiscount = eLib.getDataFromExcel("productPage", 1, 8);
		String productDescriptrion = eLib.getDataFromExcel("productPage", 1, 9);
		String productShippingCharge = eLib.getDataFromExcel("productPage", 1, 10);
		String productAvailability = eLib.getDataFromExcel("productPage", 1, 11);
		String absolutePathOfImage1 = fLib.convertingRelativePathToAbsolutePath(IPathConstants.insertProductImage1Path);
		String absolutePathOfImage2 = fLib.convertingRelativePathToAbsolutePath(IPathConstants.insertProductImage2Path);
		String absolutePathOfImage3 = fLib.convertingRelativePathToAbsolutePath(IPathConstants.insertProductImage3Path);
		String ActualInsertProductSuccessfulConfirmationMessage = eLib.getDataFromExcel("productPage", 1, 12);

		AdminHomePage adminHomePage = new AdminHomePage(driver);
		CategoryPage categoryPage = new CategoryPage(driver);
		SubCategoryPage subCategoryPage = new SubCategoryPage(driver);
		InsertProductPage insertProductPage = new InsertProductPage(driver);
		InsertProductConfirmationPage insertProductConfirmationPage = new InsertProductConfirmationPage(driver);
		ManageProductPage manageProductPage = new ManageProductPage(driver);

		adminHomePage.validatingAdminHomePageText(expectedAdminHomePageText);
		categoryPage.createCategory(categoryName, description, driver);
		subCategoryPage.createSubCategory(categoryName, subCategoryName, driver);

		insertProductPage.inserProduct(categoryName, subCategoryName, productName, productCompanyName,
				productPriceBeforeDiscount, productPriceAfterDiscount, productDescriptrion, productShippingCharge,
				productAvailability, absolutePathOfImage1, absolutePathOfImage2, absolutePathOfImage3, driver);

		insertProductConfirmationPage
				.validatingInsertProductSuccessfulConfirmationMessage(ActualInsertProductSuccessfulConfirmationMessage);

		adminHomePage.getManageProductsLink().click();

		manageProductPage.getSearchBar().sendKeys(productName);
		manageProductPage.validatingInsertedProductName(productName, driver);
	}
}
