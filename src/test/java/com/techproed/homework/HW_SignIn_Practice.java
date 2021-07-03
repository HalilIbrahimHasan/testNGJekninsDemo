package com.techproed.homework;
import com.github.javafaker.Faker;
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
import java.util.Random;

public class HW_SignIn_Practice {
    Faker faker;
    @BeforeMethod
    public void setUp() throws IOException {
       Driver.getDriver().get("http://automationpractice.com/index.php");
        ReusableMethods.getScreenshot("Default Page!");

    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
    @Test
    public void signInClick() throws Exception {
        faker = new Faker();
        //sigin click
        WebElement signIn = Driver.getDriver().findElement(By.xpath("//a[@class='login']"));
        signIn.click();
        //email send
        WebElement emailCreate = Driver.getDriver().findElement(By.xpath("//input[@name='email_create']"));
        String randomEmail=faker.name().firstName() + "@gmail.com";
        System.out.println(randomEmail);
        emailCreate.sendKeys(randomEmail);
        //submit
        WebElement submitCreate = Driver.getDriver().findElement(By.id("SubmitCreate"));
        submitCreate.click();
        ReusableMethods.getScreenshot("Sign in Page!");
        //verifying
        //create account
        WebElement verifyCreateAccount = Driver.getDriver().findElement(By.xpath("//h1[@class='page-heading']"));
        Assert.assertTrue(verifyCreateAccount.isDisplayed());
        //personal info
        WebElement verifyPersonalInfo = Driver.getDriver().findElement(By.xpath("//h3[@class='page-subheading']"));
        Assert.assertTrue(verifyPersonalInfo.isDisplayed());
        //title
        WebElement verifyTextTitle = Driver.getDriver().findElement(By.xpath("//label[.='Title']"));
        Assert.assertTrue(verifyTextTitle.isDisplayed());

        //filling the fields
        //gender
        WebElement selectTitle = Driver.getDriver().findElement(By.id("uniform-id_gender1"));
        selectTitle.click();

        //name
        WebElement firstName = Driver.getDriver().findElement(By.xpath("//input[@name='customer_firstname']"));
        firstName.sendKeys(faker.name().firstName());
        //lastname
        WebElement lastName = Driver.getDriver().findElement(By.xpath("//input[@name='customer_lastname']"));
        lastName.sendKeys(faker.name().lastName());
        //password
        WebElement passwd = Driver.getDriver().findElement(By.xpath("//input[@name='passwd']"));
        String pass=faker.lorem().characters(5,10);
        System.out.println(pass);
        passwd.sendKeys(pass);
        //        randomize numbers
        Random random = new Random();
        int randomDay = random.nextInt(30);
        int randomMonth = random.nextInt(11);
        int randomYear = random.nextInt(20);


        //dateOfBirth dropbox
        //days
        WebElement daysDrop =Driver.getDriver().findElement(By.id("days"));
        Select daysOptions = new Select(daysDrop);
        daysOptions.selectByIndex(randomDay);
        //month
        WebElement monthsDrop = Driver.getDriver().findElement(By.id("months"));
        Select monthOptions = new Select(monthsDrop);
        monthOptions.selectByIndex(randomMonth);
        //year
        WebElement yearsDrop = Driver.getDriver().findElement(By.id("years"));
        Select yearOptions = new Select(yearsDrop);
        yearOptions.selectByIndex(randomYear);


        //SignUp news letter
        WebElement newsLetterCheckBox = Driver.getDriver().findElement(By.xpath("//input[@name='newsletter']"));
        newsLetterCheckBox.click();

        //company
        WebElement company = Driver.getDriver().findElement(By.xpath("//input[@name='company']"));
        company.sendKeys(faker.company().name());

        //address
        WebElement address = Driver.getDriver().findElement(By.xpath("//input[@name='address1']"));
        address.sendKeys(faker.address().buildingNumber() +faker.address().streetName());

        //city
        WebElement city = Driver.getDriver().findElement(By.xpath("//input[@name='city']"));
        city.sendKeys(faker.address().cityName());

        //state dropdown
        WebElement statesDropDown = Driver.getDriver().findElement(By.xpath("//select[@name='id_state']"));
        Select statesOptions = new Select(statesDropDown);
        statesOptions.selectByValue("23");

        //zipcode
        WebElement zipCode = Driver.getDriver().findElement(By.xpath("//input[@name='postcode']"));
        zipCode.sendKeys(faker.number().digits(5));


        //country dropdown
        WebElement countryDrop = Driver.getDriver().findElement(By.xpath("//select[@name='id_country']"));
        Select countryOptions = new Select(countryDrop);
        countryOptions.selectByValue("21");

        //additional info
        WebElement additionalInfo = Driver.getDriver().findElement(By.xpath("//textarea[@name='other']"));
        additionalInfo.sendKeys(faker.pokemon().name());

        //home phone
        WebElement homePhone = Driver.getDriver().findElement(By.xpath("//input[@name='phone']"));
        homePhone.sendKeys(faker.phoneNumber().cellPhone());

        //mobile phone
        WebElement mobilePhone = Driver.getDriver().findElement(By.xpath("//input[@name='phone_mobile']"));
        mobilePhone.sendKeys(faker.phoneNumber().cellPhone());

        //assign reference name
        WebElement referenceName = Driver.getDriver().findElement(By.xpath("//input[@name='alias']"));
        referenceName.sendKeys(" "+faker.name().fullName() );

        //submitAccount
        WebElement submitAccount = Driver.getDriver().findElement(By.id("submitAccount"));
        submitAccount.click();

        //verify myAccount
        WebElement verifyMyAccount = Driver.getDriver().findElement(By.xpath("//h1[@class='page-heading']"));
        Assert.assertTrue(verifyMyAccount.isDisplayed());

        ReusableMethods.getScreenshot("Account Created!");

    }
}
