package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Day10_TestAddressLoginPage {
//    This is 1st page class
//    This class should have ONLY TestAddressLoginPage object
//    We can also add major methods in this class

//    1. Create constructor
    public Day10_TestAddressLoginPage(){
//        PageFactory.initElements will initilize this page objects
        PageFactory.initElements(Driver.getDriver(),this);
    }

//    NORMAL WAY:
//    WebElement email=Driver.getDriver.findElement(By.id("session_email"));//THIS IS SAME AS BELOW CODE

//    2. @FindBy :Used to find elements
//    We can use all 8 locators inside @FindBy annotation
//    Page Object Model. These page objects must be public so they can be accessable
    @FindBy(id="session_email")
    public WebElement email;

    @FindBy(id="session_password")
    public WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement singInButton;

}
