package com.solarproposal.cucumber.pages.home;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solarproposal.cucumber.pages.BasePage;
import com.solarproposal.cucumber.pages.login.LoginPage;

/**
 * 
 * @author ppenmetsa
 * @since oct 01,2015
 */

public class HomePage extends BasePage {

	/**
	 * Constructor to initalize the Webdriver Object
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		// Assert.assertTrue(this.driver.getCurrentUrl().contains("/homepage"),"Home
		// page is not found");
		PageFactory.initElements(driver, this);
	}

	/**
	 * Web element for Maps search icon.
	 */
	// @FindBy(id = "homeownerMapSearchBox")
	By mapSearch = By.id("homeownerMapSearchBox");

	/**
	 * By element for Lead Button
	 */
	By leadButton = By.id("lead-button");

	/**
	 * Web element for shortcuts
	 */
	@FindBy(xpath = ("//span[text()='Shortcuts']"))
	WebElement Shortcuts;

	/**
	 * Web Element for Home Button
	 */
	By homeLink = By.xpath("//a[text()='Home']");

	/**
	 * Web Element for logout button
	 * 
	 */
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logOut;

	/**
	 * Web Elemnet for userName
	 */
	@FindBy(xpath = ("//span[@class='menu-label-text ng-binding']"))
	WebElement userName;

	/**
	 * Web Element for Sales DashBoard
	 */
	// @FindBy(xpath = "//a[text()='Sales Dashboard']")
	By salesDashBoardLink = By.xpath("//a[text()='Sales Dashboard']");

	/**
	 * Web Element for View more
	 */
	// @FindBy(id = "viewMoreHomeowners")
	By viewMoreBtn = By.id("viewMoreHomeowners");

	/**
	 * By Element for solar news links
	 */
	By solarNewslist = By.xpath("//div[@class='list-group']/a");

	/**
	 * By element for solar news.
	 */
	By solaNews = By.xpath("//h4[text()='Solar News']");
	
	/**
	 * By element for user role
	 */
	
	By userRole = By.xpath("//span[text()='Pname M']");

	/**
	 * WebElement for prospects table
	 */
	By prospectsTable = By.xpath("(//table)[2]//tr");

	/**
	 * By element for First Name in PRospects table
	 */
	By firstName = By.xpath("(//table//tr/td)[1]");

	/**
	 * By element for Last name in Prospect table
	 */
	By lastName = By.xpath("(//table//tr/td)[2]");

	/**
	 * By element for CC-initiated
	 */
	By ccInitiated = By.xpath("(//span[text()='Initiate'])[1]");

	/**
	 * By element for CC -Resend
	 */
	By ccResend = By.xpath("(//span[text()='Resend'])[1]");

	/**
	 * By element to locate Credit check Confirmation pop-up
	 */
	By ccConfirmation = By.id("resend-continue");

	/**
	 * By element for to proposal button
	 */
	By toProposal = By.xpath("(//span[text()='To Proposal'])[1]");

	/**
	 * Method to check for HOME Button
	 * 
	 * @return isHomeLinkExists
	 */
	public Boolean homeLinkExists() {
		boolean isHomeLinkExists = isDisplayed(homeLink);
		this.log("Is Home link exist : " + isHomeLinkExists);
		return isHomeLinkExists;
	}

	/**
	 * Method to verify the Lead button is present.
	 * 
	 * @return isLeadbtnPresent
	 */
	public boolean leadBtn() {
		boolean isLeadbtnPresent = this.findPresenceOfElement(leadButton, MIN_TIMEOUT).isDisplayed();
		this.log("Lead Button : " + isLeadbtnPresent);
		return isLeadbtnPresent;
	}

	/**
	 * Method to verify the maps is present
	 */
	public boolean mapSearch() {
		boolean isMapbtnPresent = this.findPresenceOfElement(mapSearch, MIN_TIMEOUT).isDisplayed();
		this.log("Map Search is Available :" + isMapbtnPresent);
		return isMapbtnPresent;
	}

	/**
	 * Method to verify is Sales DashBoard is present.
	 * 
	 * @return
	 */
	public boolean salesDB() {

		boolean isSalesbdbtnPresent = this.findPresenceOfElement(salesDashBoardLink, MIN_TIMEOUT).isDisplayed();
		this.log("Sales DashBoard button is available: " + isSalesbdbtnPresent);
		return isSalesbdbtnPresent;
	}

	/**
	 * Method to verify is view more button is present
	 * 
	 * @return
	 */
	public boolean viewMore() {

		boolean isviewbtnPresent = this.findPresenceOfElement(viewMoreBtn, MIN_TIMEOUT).isDisplayed();
		this.log("View More button is present: " + isviewbtnPresent);
		return isviewbtnPresent;
	}

	/**
	 * Method to verify the shortcuts dropdown
	 * 
	 * @return
	 */
	public List<WebElement> checkShortcuts() {
		this.Shortcuts.click();
		List<WebElement> shortcuts = driver.findElements(
				By.xpath("(//li[text()='Shortcuts']/following-sibling::li)[position() >= 2 and position() <= 5]"));
		return shortcuts;
	}

	/**
	 * Method to check solar links exists
	 * 
	 * @return
	 */
	public int getSolarListCount() {
		return this.findVisibleElements(this.solarNewslist, MIN_TIMEOUT).size();
	}

	/**
	 * Method to check solar links exists
	 * 
	 * @return
	 */
	public boolean isSolarNewsExists() {
		boolean isSolarNewsExists = isDisplayed(this.solaNews);
		return isSolarNewsExists;
	}

	/**
	 * Method to check for all the rows in Prospects table.
	 * 
	 * @return
	 */
	public int getTableRowsCount() {
		return this.findVisibleElements(this.prospectsTable, MAX_TIMEOUT).size();
	}
	
	/**
	 * Method to check for the user role
	 */
    public boolean isUserRoleExists() {
    	boolean isUserRoleExists= isDisplayed(this.userRole) ;
    	return isUserRoleExists;
    }
		
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		String firstName = this.getText(this.firstName);
		this.log("The first name is: " + firstName);
		return firstName;
	}

	public String getLastName() {
		String lastName = this.getText(this.lastName);
		this.log("The Last name is: " + lastName);
		return lastName;
	}

	/**
	 * Method to click on the newly created lead
	 */
	public void clickNewLead() {
		this.findPresenceOfElement(firstName, MIN_TIMEOUT).click();

	}

	/**
	 * Method to click on the new lead Credit check initiate button
	 */
	public void clickCCInitiatebtn() {
		this.findPresenceOfElement(ccInitiated, 2).click();
	}

	/**
	 * Method to find Credit check Resend button
	 */
	public void clickCCResendbtn() {
		this.findClickableElement(ccResend).click();
	}

	/**
	 * Method to find click on Credit check resend confirmation pop-up
	 */
	public void confirmationCCpopupbtn() {
		this.findPresenceOfElement(ccConfirmation, MAX_TIMEOUT).click();
	}

	/**
	 * Method to click on logout and verify if the user came out of the portal
	 */
	public LoginPage logOut() {
		this.userName.click();
		this.logOut.click();
		return new LoginPage(driver);

	}

}
