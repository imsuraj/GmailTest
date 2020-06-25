/**
 * 
 */
package com.qa.gmail.tests;

/**
 * @author Suraj
 * Jun 25, 2020
 */

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.gmail.base.BaseTest;
import com.qa.gmail.listeners.ExtentReportListener;
import com.qa.gmail.pages.InboxPage;
import com.qa.gmail.util.Constants;


public class InboxPageTest extends BaseTest{

	InboxPage inboxPage;

	@BeforeClass
	public void inboxSetup() {
		//1 - Login to Gmail
		inboxPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}


	@Test(priority = 1)
	public void verifyComposeButtonIsDisplayedTest() {
		Assert.assertTrue(inboxPage.displayComposeButton());
	}


	@Test(priority = 2, dependsOnMethods = "verifyComposeButtonIsDisplayedTest")
	public void verifyGmailTest() {

		//2 - compose an email from subject and body(Subject text should be "Subject of this message" and body text should be "Single line body of the message".
		inboxPage.clickComposeEmail();
		inboxPage.composeEmail(prop.getProperty("username"), Constants.Inbox_Page_Subject, Constants.Inbox_Page_Body_Message);

		//3 - Label email as "Social"
		inboxPage.selectSocialLabel();

		//4 - Send the email to the same account which was used to login (from and to addresses would be the same)
		inboxPage.clickSendButton();

		String sentMessage = inboxPage.verifySentMessage();
		Assert.assertEquals(sentMessage, Constants.Success_Message, "Message not sent");


		//5 - - Wait for the email to arrive in the Inbox
		inboxPage.clickOnSocialTab();

		//6 - open email
		inboxPage.clickEmail(Constants.Inbox_Page_Subject);
		
		//7- Mark star and 
		inboxPage.markEmailStar();
		
		String starredMessage = inboxPage.verifyStarredMessage();
		Assert.assertEquals(starredMessage, Constants.Starred_Message, "Starred message not sent");

		//8- Verify email came under proper Label i.e. "Social"
		inboxPage.clickOnInboxLabel();
		System.out.println("Labels button clicked.");

		Assert.assertTrue(inboxPage.isSocialCheckedNew());

		//9-- Verify the subject and body of the received email
		String inboxSubject = inboxPage.getInboxSubject();
		String inboxBody = inboxPage.getInboxBody();

		Assert.assertEquals(inboxSubject, Constants.Inbox_Page_Subject, "Subject not matched.");
		Assert.assertEquals(inboxBody, Constants.Inbox_Page_Body_Message, "Email body not matched.");
		

	}
}
