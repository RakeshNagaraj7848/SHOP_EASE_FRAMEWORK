package com.urbantreasurelimited.ecommerce.adminlogintest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.urbantreasurelimited.ecommerce.generic.baseclass.BaseClass;
import com.urbantreasurelimited.ecommerce.generic.fileutility.ExcelUtility;
import com.urbantreasurelimited.ecoomerce.objectrepositoryutility.home.AdminHomePage;

@Listeners(com.urbantreasurelimited.ecommerce.generic.listenerutility.ListenerImplementationClass.class)
public class LoginToApplicationAsAdminTest extends BaseClass {
	@Test
	public void adminLoginToApplicationTest() throws EncryptedDocumentException, IOException

	{
		ExcelUtility eLib = new ExcelUtility();
		String expectedAdminHomePageText = eLib.getDataFromExcel("adminHomePage", 1, 2);
		AdminHomePage adminHomePage = new AdminHomePage(driver);
		adminHomePage.validatingAdminHomePageText(expectedAdminHomePageText);
	}
}
