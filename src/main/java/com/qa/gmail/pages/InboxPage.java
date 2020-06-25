/**
 * 
 */
package com.qa.gmail.pages;

/**
 * @author Suraj
 * Jun 25, 2020
 */

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.gmail.base.BasePage;
import com.qa.gmail.util.ElementUtil;


public class InboxPage extends BasePage{
	private WebDriver driver;

	//1. Page locators

	By composeButton 		= By.xpath("//div[text()='Compose']");

	By recipients 			= By.xpath("//textarea[@name='to']");
	By subject 				= By.xpath("//input[@name='subjectbox']");
	By body 				= By.xpath("//div[@class='Am Al editable LW-avf tS-tW']");

	By threeDotIcon 		= By.xpath("//div[@class='J-JN-M-I J-J5-Ji Xv L3 T-I-ax7 T-I']");
	By label 				= By.xpath("//div[text()='Label']");
	By social 				= By.xpath("//div[@title='Social']");

	By sendButton			= By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']");

	By messageSent 			= By.xpath("//span[text()='Message sent.']");

	By socialTab 			= By.xpath("//*[text()='Social' and @class='aKz']");	
	
	By recSubject 			= By.xpath("//tr[@class='zA zE']//td[@class='xY a4W']//span[@class='bog']");

	By inboxSubject 		= By.xpath("//h2[@class='hP']");
	By inboxBody			= By.xpath("//div[@class='a3s aXjCH ']//div[@dir='ltr']");
	By inboxLabel			= By.xpath("(//*[@class='asb T-I-J3 J-J5-Ji'])[2]");
	By inboxLabelCheckbox	= By.xpath("//div[@title='Social']");
	String checkBoxAttribute= "aria-checked";
	String attributeValue	= "true";

	
	By inboxThreeDot 		= By.xpath("(//div[@class='T-I J-J5-Ji mA nf T-I-ax7 L3'])[2]");
	By addStar				= By.xpath("//div[text()='Add star']");
	By addStarSucessMessage	= By.xpath("//span[text()='Conversation starred.']");



	//2. Create constructor for the page
	public InboxPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	//3. Page actions or methods.

	public boolean displayComposeButton() {
		return elementUtil.doIsDisplayed(composeButton);
	}

	public void clickComposeEmail() {
		elementUtil.doClick(composeButton);
	}

	public void composeEmail(String recipients, String subject, String body) {
		elementUtil.doSendKeys(this.recipients, recipients);
		elementUtil.doSendKeys(this.subject, subject);
		elementUtil.doSendKeys(this.body, body);
	}

	public void selectSocialLabel() {
		elementUtil.doClick(threeDotIcon);
		elementUtil.doActionsClick(label);
		elementUtil.doActionsClick(social);
	}

	public void clickSendButton() {
		elementUtil.doClick(sendButton);
	}

	public String verifySentMessage() {
		if(elementUtil.doIsDisplayed(messageSent)) {
			return elementUtil.doGetText(messageSent);
		}
		return null;
	}

	public void clickOnSocialTab() {
		elementUtil.doClick(socialTab);
	}

	public List<WebElement> readSubject() {
		return elementUtil.getElements(recSubject);
	}

	public String getInboxSubject() {
		return elementUtil.doGetText(inboxSubject);
	}

	public String getInboxBody() {
		return elementUtil.doGetText(inboxBody);
	}

	public void clickOnInboxLabel() {
		elementUtil.waitForElementToBeClickable(inboxLabel, 10);
		elementUtil.doActionsClick(inboxLabel);
	}

	public boolean isSocialCheckedNew() {
		boolean checkbox = elementUtil.getElement(inboxLabelCheckbox).getAttribute(checkBoxAttribute).equals(attributeValue);
		System.out.println("The checkbox is selection state is - " + checkbox);
		return checkbox;
		
	}


	public void clickEmail(String sub) {

		List<WebElement> subjectList = readSubject();

		for (int i = 0; i < subjectList.size(); i++) {

			if (subjectList.get(i).getText().contains(sub)) {
				System.out.println(subjectList.get(i).getText());
				subjectList.get(i).click();
				System.out.println("email clicked");
				break;
			}
		}
	}
	

	
	public void markEmailStar() {
		//elementUtil.doClick(starLocator);
		elementUtil.doClick(inboxThreeDot);
		elementUtil.doActionsClick(addStar);
		
	}
	
	public String verifyStarredMessage() {
		if(elementUtil.doIsDisplayed(addStarSucessMessage)) {
			return elementUtil.doGetText(addStarSucessMessage);
		}
		return null;
	}

	
	
}
