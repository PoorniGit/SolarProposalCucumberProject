package com.solarproposal.cucumber.tests.login;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.solarproposal.cucumber.pages.home.HomePage;
import com.solarproposal.cucumber.pages.login.LoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * 
 * @author ppenmetsa
 *
 */
public class CucumberInvalidLoginTest {

	LoginPage loginPage;
	HomePage homePage;
	WebDriver driver;
	private static Logger logger =  Logger.getLogger(CucumberInvalidLoginTest.class);
	
	@Before
	public void openBrowser(){
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\binaries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://solarproposal.qa.sunedison.com/#/login");
		loginPage = new LoginPage(driver);
	}
	
	@Given("^Login to Proposal tool with invalid username \"(.*)\" and password \"(.*)\"$")
	public void loginToProposalTool(String userName,String password){
		homePage = loginPage.login(userName, password);
	}
	
	@Then("^Validate Login failed error displayed on the login screen$")
	public void testLoginErrorMessage() {
		// Get the actual and expected error messasge.
		String actualErrorMessage = loginPage.getInvalidErrorMessage();
		String expectedErrorMessage = "The user name or password you entered is incorrect.";

		logger.info("The invalid login error message is displayed on the login screen: " + expectedErrorMessage);
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage,"Invalid error message not matched with expected.");
	}
	
	@After
	public void closeBrowser(Scenario scenario){
		if(scenario.isFailed()){
			logger.error("Capturing screenshot for scenario.");
			loginPage.logWithScreenShot("Capturing Screnshot for failed scenarios", driver);
		}
		driver.close();
	}
}
