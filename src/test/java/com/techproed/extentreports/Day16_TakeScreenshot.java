package com.techproed.extentreports;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day16_TakeScreenshot {

    public void takeScreenshot() throws IOException {
//        1. TakesScreenshot object is used to take screenshot in selenium
        TakesScreenshot ts=(TakesScreenshot) Driver.getDriver();
//        2. Calling  getScreenshotAs method creates an image file. Storing in File object
        File image=ts.getScreenshotAs(OutputType.FILE);

//        getting the current data to use it in the image name to have unique image name
        String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        Full path of the image file where the image will be stored/Folder Name: test-output. File Name:  Screenshots/"+currentDate+".png". File Format : png
        String path = System.getProperty("user.dir")+                 "/test-output/Screenshots/"+currentDate+".png";
//        Final path of the image file
        File finalPath = new File(path);

//        Saving the image to the final path
        FileUtils.copyFile(image,finalPath);
    }


    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;

    @BeforeMethod
    public void setUp() throws IOException {
        loginPage= new Day11_LoginPage();
        defaultPage = new Day11_DefaultPage();
        Driver.getDriver().get(ConfigReader.getProperty("application_login_url"));
        takeScreenshot();
    };
    @Test
    public void positiveLoginTest() throws IOException {
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        takeScreenshot();
        loginPage.loginButton.click();
        Assert.assertEquals(defaultPage.userID.getText(),ConfigReader.getProperty("manager_username"));
        takeScreenshot();
    }

    @Test
    public void negativeLoginTest() throws IOException {
        loginPage.username.sendKeys(ConfigReader.getProperty("invalid_manager_id"));
        loginPage.password.sendKeys(ConfigReader.getProperty("invalid_manager_pass"));
        takeScreenshot();
        loginPage.loginButton.click();
        Assert.assertTrue(loginPage.errorMessage.getText().contains(ConfigReader.getProperty("login_error_message")));
        takeScreenshot();

    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
}
