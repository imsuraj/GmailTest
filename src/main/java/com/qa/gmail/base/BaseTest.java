package com.qa.gmail.base;
/**
 * @author Suraj
 * Jun 25, 2020
 */
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.gmail.pages.LoginPage;

public class BaseTest {
	public WebDriver driver;
	
	public BasePage basePage;
	public LoginPage loginPage;
	public Properties prop;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		
		loginPage = new LoginPage(driver);
		
	}
		
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
