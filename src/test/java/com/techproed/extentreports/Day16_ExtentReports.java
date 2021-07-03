package com.techproed.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day16_ExtentReports {
//Creating extentReport : Create the report object
    ExtentReports extentReports;
//    Creating html report : Generated html report
    ExtentHtmlReporter extentHtmlReporter;
//    Creating extentTest : logs information
    ExtentTest extentTest;

    @BeforeTest(alwaysRun = true)//Runs before each Test. This will run only once.
    public void setUpTest(){
//        Creating extent report
        extentReports=new ExtentReports();
//        Path of the html report
        String filePath =  System.getProperty("user.dir")+ "/test-output/myprojectreport.html";
//        Creating html report in the file path
        extentHtmlReporter=new ExtentHtmlReporter(filePath);
//        Attaching html report the Extent report
        extentReports.attachReporter(extentHtmlReporter);
//      So far the report creating is DONE.


//      OPTIONAL : We can add custom information for our our report
        extentReports.setSystemInfo("Environment","QA Environment");
        extentReports.setSystemInfo("Browser","chrome");
        extentReports.setSystemInfo("Tester","Best Tester Ever");
        extentReports.setSystemInfo("Team Name","Jaguars");

        extentHtmlReporter.config().setDocumentTitle("Resortsline Hotels Report");
        extentHtmlReporter.config().setReportName("Resortsline Login Report");

        extentTest=extentReports.createTest("Resortsline Extent Reports","Autoamtion Login Scenarios");

    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest(){
//        Closing the extent reports and generation it.
        extentReports.flush();
    }


//    We use extent reports in our tests to give information on the test steps
//    Log4J classes also helps with reports
    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        loginPage=new Day11_LoginPage();
        defaultPage=new Day11_DefaultPage();
        Driver.getDriver().get(ConfigReader.getProperty("application_login_url"));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {//Listen the test result
//        We will take screenshot when only test case fails
        if (result.getStatus()==ITestResult.FAILURE){

////         *************   TAKING SCREENSHOT STEP BY STEPS  **************
////        1. TakesScreenshot object is used to take screenshot in selenium
//            TakesScreenshot ts=(TakesScreenshot) Driver.getDriver();
////        2. Calling  getScreenshotAs method creates an image file. Storing in File object
//            File image=ts.getScreenshotAs(OutputType.FILE);
//
////        getting the current data to use it in the image name to have unique image name
//            String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
////        Full path of the image file where the image will be stored/Folder Name: test-output. File Name:  Screenshots/"+currentDate+".png". File Format : png
//            String path = System.getProperty("user.dir")+                 "/test-output/Screenshots/"+currentDate+".png";
////        Final path of the image file
//            File finalPath = new File(path);
//
////        Saving the image to the final path
//            FileUtils.copyFile(image,finalPath);
////            Log the test as FAILED
//            extentTest.fail("TEST CASE FAILED : "+result.getName());//result.getName(); returns the name of the test
//      **************************************************************
//           Using reusable getScreenShot method instead of the codes above
            String path = ReusableMethods.getScreenshot(result.getName());
//      *****************************************************************
//          adding the screenshot to the html report
            extentTest.addScreenCaptureFromPath(path);
//           adding the error message in the html report
            extentTest.fail(result.getThrowable());

        }else if (result.getStatus()==ITestResult.SKIP){
            extentTest.skip("TEST CASE IS SKIPPED : "+result.getName());
        }
    }


    @Test(alwaysRun = true)
    public void extentReportsTest(){
//        We log the information using extentTest object.
        extentTest.info("Giving information");
        extentTest.pass("Test Case Passed Yayyy");
        extentTest.fail("Test Case Failled Nooooooo");
        extentTest.skip("Test Case is skipped No reaction!!!");
    }



    @Test(alwaysRun = true)
    public void positiveLoginTest() throws IOException {
        extentTest.info("Entering username");
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
//        Assert.assertTrue(false);//THis test will fail. So We expect to see screenshot.
        extentTest.info("Entering password");
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        extentTest.info("Clicking login button");

        loginPage.loginButton.click();
        extentTest.info("Verifying login is successful");
        Assert.assertEquals(defaultPage.userID.getText(),ConfigReader.getProperty("manager_username"));
        extentTest.pass("Positive Login Is Complete");
    }
}
