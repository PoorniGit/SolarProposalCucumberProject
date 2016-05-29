package com.solarproposal.cucumber.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solarproposal.cucumber.pages.BasePage;
import com.solarproposal.cucumber.pages.home.HomePage;

/**
 * 
 * @author ppenmetsa
 * @since  Sep 22,2015
 */
public class LoginPage extends BasePage {

   /*
    * Web Element for login
    */
 @FindBy(id="login-username")
 WebElement loginField;
 
 /*
  * Web Element for password
  */
 @FindBy(id= "login-password")
 WebElement loginPassword;
 
 /*
  * Web Element for submit
  */
 
 @FindBy(id= "login-submit")
 WebElement loginSubmit;

/*
 * By element for invalid login Msg
 */
 
 By invalidLoginMsg = By.xpath("//form/ul/li");
   
 /**
  * Constructor to initialize webdriver object 
  * @param driver
  */
 
 public LoginPage(WebDriver driver)
 {
	 super(driver);
	 PageFactory.initElements(driver, this);
 }
 
 /**
  * Set User name in Login page
  * @param strLoginName
  */
 public void setLoginName(String strLoginName)
 {
      this.clearAndType(this.loginField, strLoginName);
      this.log("Login Name: "+strLoginName );
 }

 /**
  * Set password in login page
  * @param strLoginPwd 
  */
 public void setLoginPwd(String strLoginPwd)
 {
	 this.clearAndType(this.loginPassword , strLoginPwd);
	 this.log("Passowrd is: "+strLoginPwd );
 }

 /**
  * Set submit button is login page
  */
 public void clickSubmit()
 {
	 this.loginSubmit.click();
 }

 /**
  * Method to login the portal.
  * @param strLoginName
  * @param strLoginPwd
  */
 public HomePage login(String strLoginName,String strLoginPwd){
	this.setLoginName(strLoginName);
	this.setLoginPwd(strLoginPwd);
	clickSubmit(); 
	this.logWithScreenShot("Navigate to Home Page", this.driver);
	return new HomePage(driver);
 }
 /**
  * Method to login the portal.
  * @param strLoginName
  * @param strLoginPwd
  */
 public void loginWithInvalidCredentials(String strLoginName,String strLoginPwd){
	 this.setLoginName(strLoginName);
	 this.setLoginPwd(strLoginPwd);
	 this.clickSubmit(); 
 }
 
 /**
  * Method to get invalid login error msg
  */
 public String getInvalidErrorMessage(){
	 String errorMessage = this.getText(invalidLoginMsg);
	 this.log("The invalid login error message is: "+errorMessage);
	 return errorMessage;
 }
 

}