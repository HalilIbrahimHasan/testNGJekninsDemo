package com.techproed.extentreports;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day17_ExtentReports extends TestBase {

    Day11_LoginPage loginPage=new Day11_LoginPage();
    Day11_DefaultPage defaultPage=new Day11_DefaultPage();
    @Test
    public void extentReports(){
        extentTest.pass("Entering manager username");
//        loginPage.username.sendKeys(ConfigReader.getProperty("invalid_manager_id"));//FAILS SO WE GET SCREENSHOT
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));//FAILS SO WE GET SCREENSHOT
        extentTest.pass("Entering password");
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        extentTest.pass("Clicking on login button");
        loginPage.loginButton.click();
        extentTest.pass("Manager log in is successful");
        Assert.assertEquals(defaultPage.userID.getText(),ConfigReader.getProperty("manager_username"));
    }
}
