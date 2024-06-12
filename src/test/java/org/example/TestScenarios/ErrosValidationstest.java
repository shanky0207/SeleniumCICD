package org.example.TestScenarios;

import org.example.TestComponents.BaseTest;
import org.example.TestComponents.RetryLogic;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrosValidationstest extends BaseTest {

    @Test(retryAnalyzer = RetryLogic.class)
    public void loginErrorValidations(){

        landingPage.loginToApplication("seleniumtesting@practice.com","Selenium@12345");
        Assert.assertTrue(landingPage.verifyLoginErrormsg().equals("Incorrect email or password "));
    }


}
