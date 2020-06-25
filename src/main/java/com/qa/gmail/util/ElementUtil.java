package com.qa.gmail.util;

/**
 * @author Suraj
 * Jun 25, 2020
 */

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.gmail.base.BasePage;



public class ElementUtil extends BasePage {

	WebDriver driver;


	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> elementsList = driver.findElements(locator);
		return elementsList;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			System.out.println("Locator is : " + locator);
			element = driver.findElement(locator);
			System.out.println("WebElement is created successfully : " + locator);

		} catch (Exception e) {
			System.out.println("Some exception got occurred with this locator: " + locator);
		}
		return element;
	}

	public void doSendKeys(By locator, String value) {
		waitForElementPresent(locator, 10);
		getElement(locator).sendKeys(value);
	}

	public void doEnterKey(By locator) {
		waitForElementPresent(locator, 10);
		getElement(locator).sendKeys(Keys.RETURN);
	}

	public boolean isCheckboxSelected(By locator) {
		waitForElementPresent(locator, 10);
		return getElement(locator).isSelected();
	}

	public void doClick(By locator) {
		waitForElementPresent(locator, 10);
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		waitForElementPresent(locator, 10);
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		waitForElementPresent(locator, 10);
		return getElement(locator).isDisplayed();
	}


	// **********************************Actions class Utils
	// *********************************

	public void doActionsClick(By locator) {
		waitForElementToBeVisible(locator, 10);
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}


	// ***************************** Wait Utils
	// *******************************************


	public WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}


	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

}
