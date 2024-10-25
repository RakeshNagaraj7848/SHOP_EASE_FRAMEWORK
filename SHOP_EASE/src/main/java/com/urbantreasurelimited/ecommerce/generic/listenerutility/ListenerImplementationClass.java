package com.urbantreasurelimited.ecommerce.generic.listenerutility;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.urbantreasurelimited.ecommerce.generic.webdriverutility.JavaUtility;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.UtilityClassObject;
import com.urbantreasurelimited.ecommerce.generic.webdriverutility.WebDriverUtility;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {
	WebDriverUtility wLib = new WebDriverUtility();
	JavaUtility jLib = new JavaUtility();
	ExtentReports report;
	ExtentTest test;

	public void onStart(ISuite suite) {

		ExtentSparkReporter spark = new ExtentSparkReporter(
				"./AdvanceReports/report_" + jLib.getSystemDateWithoutFormat() + ".html");
		spark.config().setDocumentTitle("SHOP_EASE Test Suite Result");
		spark.config().setReportName("SHOP_EASE Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("BROWSER", "CHROME");
		report.setSystemInfo("OS", "WINDOW-10");
		report.setSystemInfo("ENVIRONMENT", "QA");

	}

	public void onFinish(ISuite suite) {
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, testName + " ========>execution started ==============<");
	}

	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test.log(Status.PASS, testName + " ========>execution completed successfully ==============<");

	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String path = null;
		try {
			path = wLib.takeScreenShotOfEntireWebPage(UtilityClassObject.getDriver(), testName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromBase64String(path, testName + "_" + jLib.getSystemDateWithoutFormat());
		test.log(Status.FAIL, testName + " ========>execution failed  ==============<");

	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName() + " ========>execution skipped ==============<");

	}

}
