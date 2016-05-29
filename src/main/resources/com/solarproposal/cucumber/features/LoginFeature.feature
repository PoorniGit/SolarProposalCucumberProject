#Author: your.email@your.domain.com
@RegressionForValidLogin
Feature: Solar portal Login

  @Reg1
  Scenario: Solar portal Login
    Given Login to Proposal tool with username "partnerportaltest123@gmail.com" and password "Test12345"
    When Load all the prospects
    Then Validate Logged user
