package com.urbantreasurelimited.ecommerce.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	JavaUtility jLib = new JavaUtility();

	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void pageLoadTimeout(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public void maximizeBrowserWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForAlertPresent(WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToTabonURL(WebDriver driver, String partialURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String actualURL = driver.getCurrentUrl();
			if (actualURL.contains(partialURL)) {
				break;
			}
		}

	}

	public void switchToTabonTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String actualURL = driver.getCurrentUrl();
			if (actualURL.contains(partialTitle)) {
				break;
			}
		}

	}

	public void switchToFrame(WebDriver driver, int index) {

		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String name) {

		driver.switchTo().frame(name);
	}

	public void switchToFrame(WebDriver driver, WebElement element) {

		driver.switchTo().frame(element);
	}

	public void switchToAlertAndAccept(WebDriver driver) {

		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndCancel(WebDriver driver) {

		driver.switchTo().alert().dismiss();
	}

	public void select(WebElement element, int index) {

		Select s = new Select(element);
		s.selectByIndex(index);
	}

	public void select(WebElement element, String text) {

		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	public void selectByValue(WebElement element, String value) {

		Select s = new Select(element);
		s.selectByValue(value);
	}

	public void mouseMoveOnElement(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}

	public void doubleClick(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
	}

	public void rightClick(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}

	public void clickOnElement(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.click(element).perform();
	}

	public String takeScreenShotOfEntireWebPage(WebDriver driver, String methodName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String pathToAttachToEXtentReport = ts.getScreenshotAs(OutputType.BASE64);

		File destiny = new File("./screenshots/" + methodName + "+" + jLib.getSystemDateWithoutFormat() + ".png");

		FileHandler.copy(source, destiny);
		return pathToAttachToEXtentReport;

	}

	public void takeScreenShotOfWebElement(WebElement element) throws IOException {
		File source = element.getScreenshotAs(OutputType.FILE);
		File destiny = new File("./screenshots/webelement.png");
		FileHandler.copy(source, destiny);
		// FileUtils.copyFile(source, destiny);

	}

	public void javascriptExecutorScrollTo(WebDriver driver, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
	}

	public void javascriptExecutorScrollBy(WebDriver driver, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

	public void javascriptExecutorScrollIntoView(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

}
