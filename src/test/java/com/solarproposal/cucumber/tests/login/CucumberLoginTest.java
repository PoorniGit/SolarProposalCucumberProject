package com.solarproposal.cucumber.tests.login;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.solarproposal.cucumber.pages.BasePage;
import com.solarproposal.cucumber.pages.home.HomePage;
import com.solarproposal.cucumber.pages.login.LoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * This cucumber test class contains implentation of login feature.
 * @author ppenmetsa
 *
 */
public class CucumberLoginTest  {

	LoginPage loginPage;
	HomePage homePage;
	WebDriver driver;
	private static Logger logger =  Logger.getLogger(CucumberLoginTest.class);
	@Before
	public void openBrowser(){
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\binaries\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://solarproposal.qa.sunedison.com/#/login");
		loginPage = new LoginPage(driver);
	}
	
	@Given("^Login to Proposal tool with username \"(.*)\" and password \"(.*)\"$")
	public void loginToProposalTool(String userName,String password){
		homePage = loginPage.login(userName, password);
	}
	
	@When("^Load all the prospects$")
	public void testProspetsTableLoaded() {
		int actualCount = this.homePage.getTableRowsCount();
		logger.info("Verifying if Prospects table has been loaded on Home Page: " + actualCount);
		Assert.assertEquals(actualCount, 5);
	}
	
	@Then("^Validate Logged user$")
	public void validateLoggedUser(){
		boolean userRole = this.homePage.isUserRoleExists();
		logger.info("Verifying if Map Search is present on Home Page: " + userRole);
		Assert.assertTrue("User Role is validated on the Home Page", userRole);
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
