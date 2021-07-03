package com.techproed.homework;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.pages.Day11_MainPage;
import com.techproed.pages.HW_RoomReservationPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HW_RoomReservationTest {

    Day11_MainPage mainPage;
    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;
    HW_RoomReservationPage roomReservationPage;

    //This ll take me to the Room Reservation Page
    @BeforeMethod
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        mainPage=new Day11_MainPage();
        mainPage.mainPageLoginLink.click();
        loginPage=new Day11_LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();
        defaultPage=new Day11_DefaultPage();
        defaultPage.hotelManagement.click();
        defaultPage.roomReservations.click();
    }
    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }

    @Test
    public void roomReservationTest() throws InterruptedException, IOException {
        ReusableMethods.waitForPageToLoad(10);
        roomReservationPage=new HW_RoomReservationPage();
        roomReservationPage.addRoomReservationButton.click();
        Select idUserOptions=new Select(roomReservationPage.idUser);
        idUserOptions.selectByIndex(2);
        Select idHotelOptions=new Select(roomReservationPage.idHotelRoom);
        idHotelOptions.selectByIndex(3);
        roomReservationPage.price.sendKeys("600");
        ReusableMethods.waitFor(1);
        String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        String checkinDate = (Integer.parseInt(today.substring(0,2))+2)+today.substring(2);
        String checkoutDate = ((Integer.parseInt(today.substring(0,2))+5)+today.substring(2));
        System.out.println(today+" : "+checkinDate+" : "+checkoutDate);
//        roomReservationPage.dateStart.sendKeys("01/23/2022");
//        roomReservationPage.dateEnd.sendKeys("01/24/2022");
        roomReservationPage.dateStart.sendKeys(checkinDate);
        ReusableMethods.waitFor(1);
        roomReservationPage.dateEnd.sendKeys(checkoutDate);
        roomReservationPage.adultAmount.sendKeys("2");
        roomReservationPage.childrenAmount.sendKeys("3");
        roomReservationPage.nameAndSurname.sendKeys("James Bond");
//        Thread.sleep(2000);
        roomReservationPage.contactPhone.sendKeys("2222222222");
        roomReservationPage.contactEmail.sendKeys("abc@gmail.com");
        roomReservationPage.notes.sendKeys("Testing");
        roomReservationPage.approved.click();
        roomReservationPage.isPaid.click();
        roomReservationPage.saveButton.click();

        WebElement actualSuccessMessage= ReusableMethods.waitForVisibility(By.className("bootbox-body"),10);

        String actualSuccessMessageText=actualSuccessMessage.getText();
        Assert.assertEquals(actualSuccessMessageText,ConfigReader.getProperty("expectedReservationSuccessMessage"));
        ReusableMethods.getScreenshot("Reservation_Successful!!!");
    }
}
