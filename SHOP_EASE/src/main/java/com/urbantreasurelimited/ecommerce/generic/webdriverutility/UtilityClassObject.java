package com.urbantreasurelimited.ecommerce.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}

	public static void setTest(ExtentTest actualTest) {
		test.set(actualTest);
	}

	public static ExtentTest getTest() {
		return test.get();
	}

}
