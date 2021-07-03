package com.techproed.paralleltesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Day18_ManagerLogin {

    @DataProvider(name = "manager-profiles", parallel = true)
    public Object [][] getData(){
            String [][] managerProfile= {
                    {"manager","Manager1!"},
                    {"manager2","Manager2!"},
                    {"manager3","Manager3!"}
            };
            return managerProfile;
    }

    @Test(dataProvider = "manager-profiles")
    public void resortlineLoginTest(String username,String password){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://qa-environment.resortsline.com/Account/Logon");
        driver.findElement(By.id("UserName")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("btnSubmit")).click();
        String actualUserID=driver.findElement(By.xpath("(//span)[1]")).getText();
        Assert.assertEquals(actualUserID,username);
    }
}
