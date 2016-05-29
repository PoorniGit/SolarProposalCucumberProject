#Author: your.email@your.domain.com
@RegressionForInvalidLogin
Feature: Solar portal Invalid Login

  @Reg1
  Scenario: Solar portal Invalid Login
    Given Login to Proposal tool with invalid username "partnerportaltest123@gmail.com" and password "Test123456"
    Then Validate Login failed error displayed on the login screen
