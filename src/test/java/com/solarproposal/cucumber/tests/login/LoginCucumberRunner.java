package com.solarproposal.cucumber.tests.login;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\main\\resources\\com\\solarproposal\\cucumber\\features",format={"pretty","html:reports/cucumbr-html-report"},
tags="@~RegressionForValidLogin,@RegressionForInvalidLogin")
public class LoginCucumberRunner {

}
