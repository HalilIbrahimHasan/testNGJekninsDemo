package com.techproed.tests;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.pages.Day12_HotelRoomPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day12_HotelRoomCreation {
    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;
    Day12_HotelRoomPage hotelRoomPage;
    //Login teh application:
    @BeforeMethod
    public void setUp(){
        loginPage= new Day11_LoginPage();
        Driver.getDriver().get(ConfigReader.getProperty("application_login_url"));
        loginPage.username.sendKeys(ConfigReader.getProperty("admin_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("admin_password"));
        loginPage.loginButton.click();
//        Checking if the login is successful
        defaultPage=new Day11_DefaultPage();
        Assert.assertTrue(defaultPage.addUserButton.isDisplayed());

    }

    @Test
    public void hotelRoomCreation() throws InterruptedException {
//Click on Hotel Management
        defaultPage.hotelManagement.click();

//Click on Hotel Rooms
        defaultPage.hotelRooms.click();

//Click on Add Hotel Room
        hotelRoomPage=new Day12_HotelRoomPage();
        hotelRoomPage.addHotelRoomButton.click();

//Enter All required fieldd
//        WebElement -> done in page class
//        2. Select object
        Select select=new Select(hotelRoomPage.hotelIdDropdown);
//        3. selectBy...
        select.selectByIndex(2);
        hotelRoomPage.code.sendKeys("discount code");
        hotelRoomPage.name.sendKeys("test name");
        hotelRoomPage.location.sendKeys("Dallas");
        hotelRoomPage.description.sendKeys("This is the best room for special guests");
        hotelRoomPage.price.sendKeys("1000");

        Select roomDropdown=new Select( hotelRoomPage.roomTypeDropdown);
        roomDropdown.selectByVisibleText("Studio");
        hotelRoomPage.maxAdultCount.sendKeys("2");
        hotelRoomPage.maxChildCount.sendKeys("5");
        hotelRoomPage.isApprovedCheckbox.click();
        hotelRoomPage.saveButton.click();


//Verify the message: HotelRoom was inserted successfully
//        1. HARD WAIT
//        Thread.sleep(1000);// not recommended

//       Assert.assertEquals(hotelRoomPage.popUp.getText(),"HotelRoom was inserted successfully");

//        2. USE EXPLICIT WAIT TO SOLVE THE WAIT ISSUE:
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        WebElement popupElement=wait.until(ExpectedConditions.visibilityOf(hotelRoomPage.popUp));
        Assert.assertEquals(popupElement.getText(),"HotelRoom was inserted successfully");
//Click OK
        hotelRoomPage.okButton.click();

    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }

}
