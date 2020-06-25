package com.qa.gmail.tests;

/**
 * @author Suraj
 * Jun 25, 2020
 */

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.gmail.base.BaseTest;
import com.qa.gmail.listeners.ExtentReportListener;
import com.qa.gmail.pages.InboxPage;
import com.qa.gmail.util.Constants;


@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {

	InboxPage inboxPage;
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.Login_Page_Title, "Loginpage title does not match");
	}

	@Test(priority = 2)
	public void verifyLoginTest() {
		inboxPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		Assert.assertTrue(inboxPage.displayComposeButton(), "Login failed.");

	}
}
