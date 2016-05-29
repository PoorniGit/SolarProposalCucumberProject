package com.solarproposal.cucumber.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public WebDriver driver;

	public final static int DEFAULT_TIMEOUT = 30;
	public final static int MIN_TIMEOUT = 15;
	public final static int MAX_TIMEOUT = 60;
	private static Logger logger =  Logger.getLogger(BasePage.class);

	/**
	 * Constructor to initialize webdriver object.
	 * 
	 * @param driver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to wait for seconds
	 * 
	 * @param ms
	 */
	public void waitForSeconds(int ms) {
		try {
			Thread.sleep(1000 * ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to move to element.
	 * 
	 * @param moveToElement
	 */
	public void moveToElement(WebElement moveToElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(moveToElement).build().perform();
	}

	/**
	 * Method to log with message.
	 * 
	 * @param message
	 */
	public void log(String message) {
		logger.info(message);
	}

	
	/**
	 * Method to log message with screen shot.
	 */

	public void logWithScreenShot(final String message,
			final WebDriver webDriver) {
		StringBuilder logText = new StringBuilder(message);
		File screenShot = ((TakesScreenshot) webDriver)
				.getScreenshotAs(OutputType.FILE);
		File screenShotFile = new File("src\\main\\resources\\CucumberScreenShots",
				RandomStringUtils.randomAlphabetic(5) + ".png");
		try {
			FileUtils.copyFile(screenShot, screenShotFile);
		} catch (IOException e) {
			e.printStackTrace();
			logText.append(" <i>Screenshots directory not set. Not logging screenshot<i>");
		}

		logText.append("<br>");
		logger.info(logText.toString());
	}

	/**
	 * Method to check whether given web element displayed or not.
	 * 
	 * @param element
	 * @return isDisplayed
	 */
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Method to check whether given by element is displayed or not.
	 * 
	 * @param byElement
	 *            (by element)
	 * @return isDisplayed (is displayed)
	 */
	public boolean isDisplayed(final By byElement) {
		boolean isDisplayed = false;
		try {
			isDisplayed = this.findVisibleElement(byElement, DEFAULT_TIMEOUT)
					.isDisplayed();
		} catch (Exception e) {
		}
		return isDisplayed;
	}

	/**
	 * Method to find visible element.
	 * 
	 * @param by
	 * @return visibleElement
	 */
	public WebElement findVisibleElement(final By by, int timeOut) {
		WebElement visibleElement;
		try {
			visibleElement = (new WebDriverWait(this.driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Throwable cause) {
			String errorMessage = "Could not find visible element: "
					+ by.toString();
			this.logWithScreenShot("FindVisible Element failed.", this.driver);
			throw new RuntimeException(errorMessage);
		}
		return visibleElement;
	}

	/**
	 * Method to get a text for the mentioned web element.
	 * 
	 * @param webElement
	 *            (text of web element)
	 * @return text
	 */
	public String getText(final WebElement webElement) {
		String text = null;
		try {
			text = webElement.getText();
			if (text == null || text.isEmpty()) {
				text = null;
			}
		} catch (Exception e) {
		}
		return text;
	}

	/**
	 * Method to get a text for the mentioned by element.
	 * 
	 * @param byElement
	 * 
	 * @return text
	 */
	public String getText(final By byElement) {
		String text = null;
		try {
			text = this.findVisibleElement(byElement, MAX_TIMEOUT)
					.getText();
			if (text == null || text.isEmpty()) {
				text = null;
			}
		} catch (Exception e) {
		}
		return text;
	}

	/**
	 * Method to get the tile.
	 * 
	 * @return title
	 */
	public String getPageTitle() {
		String title = this.driver.getTitle();
		this.log("Title of the page is : " + title);
		return title;
	}

	/**
	 * Method to Clear a filed and type text
	 */
	public void clearAndType(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Method to select values from drop down
	 * 
	 * @param element
	 * @param optionValue
	 * @param optionIndex
	 * @param optionVisibleText
	 */
	public void selectDropDown(final WebElement element,
			final String optionValue, final int optionIndex,
			final String optionVisibleText) {
		this.log("Select option value: " + optionValue);
		this.log("Select option index: " + optionIndex);
		this.log("Select option visible text : " + optionVisibleText);
		Select dropDown = new Select(element);
		if (optionValue != null) {
			waitForSeconds(1);
			dropDown.selectByValue(optionValue);
		} else if (optionIndex >= 0) {
			waitForSeconds(1);
			dropDown.selectByIndex(optionIndex);
		} else if (optionVisibleText != null) {
			waitForSeconds(1);
			dropDown.selectByVisibleText(optionVisibleText);
		}
		// this.logWithScreenShot("After select the option", this.driver);
	}

	/**
	 * Method to find clickable element.
	 * 
	 * @param by
	 *            (by element)
	 * @return (clickable element)
	 */
	public WebElement findClickableElement(final By by) {
		WebElement clickableElement;
		try {
			clickableElement = (new WebDriverWait(this.driver, MAX_TIMEOUT))
					.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Throwable cause) {
			String errorMessage = "Could not find clickable element: "
					+ by.toString();
			throw new RuntimeException(errorMessage);
		}
		return clickableElement;
	}

	/**
	 * Method to return list of titles/get text for the given xpath.
	 * 
	 * @param xpath
	 * 
	 * @return textList
	 */
	public List<String> getListOfText(List<WebElement> webElementList) {
		List<String> textList = new ArrayList<String>();
		for (WebElement element : webElementList) {
			if (element.getText() != null && element.getText().length() > 0) {
				textList.add(element.getText());
			}
		}
		this.log("To get list of existing elements :" + textList);
		return textList;
	}

	/**
	 * 
	 * Method to get current page URL
	 * 
	 * @return
	 */
	public String getCurrentPageUrl() {
		String url = this.driver.getCurrentUrl();
		this.log("The Page Url is: " + url);
		return url;
	}

	/**
	 * Method to convert email in to unique
	 * 
	 * @param email
	 * @return formattedEmail
	 */
	public String getFormattedEmail(String email) {

		String formattedEmail = email + RandomStringUtils.randomAlphabetic(3)
				+ "@mailinator.com";
		this.log("Get formatted email: " + formattedEmail);
		return formattedEmail;
	}
	
	/**
	 * Method to convert phone number in a specific format
	 * 
	 * @param phone
	 * @return phonenumber
	 */
	public String getFormattedPhoneNum(String phone) {
		String[] str = phone.split("-");
		String phoneNumber = "(" + str[0] + ") " + str[1] + "-" + str[2];
		this.log("Get formatted phone number: " + phoneNumber);
		return phoneNumber;
	}
	
	/**
	 * Method to find presence of the element in page.
	 * 
	 * @param by
	 * @param timeoutInSeconds
	 * 
	 * @return presenceOfElement
	 */
	public WebElement findPresenceOfElement(final By by,
			final int timeoutInSeconds) {
		WebElement presenceOfElement;
		try {
			presenceOfElement = (new WebDriverWait(this.driver,
					timeoutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			String errorMessage = "Could not find presence of element: "
					+ by.toString();
			this.logWithScreenShot("Could not find presence of element:", this.driver);
			throw new RuntimeException(errorMessage);
		}
		return presenceOfElement;
	}
	
	/**
	 * Method to find the visible elements
	 * 
	 * @param by
	 * @param timeoutInSeconds
	 * @return list of webelement
	 */
	public List<WebElement> findVisibleElements(final By by,
			final int timeoutInSeconds) {
		List<WebElement> visibleElements;
		try {
			visibleElements = (new WebDriverWait(this.driver, timeoutInSeconds)).until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(by));
		} catch (Throwable clause) {
			String errorMessage = "Could not find visible element: " + by.toString();
		
			this.logWithScreenShot("Could not find visible element: ", this.driver);
			throw new RuntimeException(errorMessage);
		}
		return visibleElements;
	}

}
