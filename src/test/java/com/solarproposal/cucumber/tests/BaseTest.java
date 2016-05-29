package com.solarproposal.cucumber.tests;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.solarproposal.cucumber.pages.BasePage;
import com.solarproposal.cucumber.pages.home.HomePage;
import com.solarproposal.cucumber.pages.login.LoginPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;



public class BaseTest {

	/**
	 * Instance variable for web driver instance.
	 */
	public WebDriver driver;

	/**
	 * Instance variable for login page.
	 */
	LoginPage loginPage;

	protected HomePage homePage;
	private static Logger logger =  Logger.getLogger(BasePage.class);

	@Before
	public void openBrowser() {
		driver = getWebDriver();
		driver.manage().window().maximize();
		String url = this.getProperty("url");
		driver.get(url);
		logger.info("Loged into Application Successfully");
	}

	/**
	 * Method to get the web driver instance.
	 * 
	 * @return driver
	 */
	public WebDriver getWebDriver() {
		String driverName = getProperty("driver");
		if (driverName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (driverName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\binaries\\chromedriver.exe");
			driver = new ChromeDriver();
		} 
		return driver;
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}

	/**
	 * Method to get the properties value.
	 * 
	 * @param propertyName
	 * @return value
	 */
	public String getProperty(String propertyName) {
		Properties properties = new Properties();
		FileReader fileReader;
		try {
			fileReader = new FileReader(new File("src\\main\\resources\\default.properties"));
			properties.load(fileReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(propertyName);
		return value;
	}

	/**
	 * Method to log with message.
	 * 
	 * @param message
	 */
	public void log(String message) {
		logger.info(message);
	}

	public HomePage loginAndGotoHomePage() {
		loginPage = new LoginPage(this.driver);
		String userName = this.getProperty("userName");
		String password = this.getProperty("passWord");
		homePage = loginPage.login(userName, password);
		return homePage;
	}
}
