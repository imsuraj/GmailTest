package com.qa.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.qa.gmail.base.BasePage;
import com.qa.gmail.util.Constants;
import com.qa.gmail.util.ElementUtil;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	
	// 1. By locators -- OR
		By emailId = By.id("identifierId");
		By nextButton = By.xpath("//span[@class='RveJvd snByac']");
	
		By password = By.xpath("//input[@name='password']");
		
		
		
		//2. Create constructor for LoginPage
		
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			elementUtil = new ElementUtil(this.driver);
		}
		
		//3. Page actions or functions or methods
		
		public String getLoginPageTitle() {
			return elementUtil.waitForTitleToBePresent(Constants.Login_Page_Title, 10);
		}
		
		
		public InboxPage doLogin(String emailId, String password) {
			elementUtil.waitForElementPresent(this.emailId, 10);
			elementUtil.doSendKeys(this.emailId, emailId);
			elementUtil.doClick(this.nextButton);
			
			//elementUtil.waitForElementPresent(this.password, 10);
			
			elementUtil.waitForElementToBeClickable(this.password, 10);
			elementUtil.doSendKeys(this.password, password);
			
			elementUtil.doEnterKey(this.password);
			
			
			//elementUtil.doClick(this.nextButton);
			
			return new InboxPage(driver);
		}
		
	}
