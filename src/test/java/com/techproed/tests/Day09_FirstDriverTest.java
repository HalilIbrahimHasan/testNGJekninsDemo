package com.techproed.tests;

import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day09_FirstDriverTest {
    @Test
    public void firstDriverTest(){
//        driver => Driver.getDriver()
//        Driver.getDriver().get("https://www.amazon.com");//HARD CODED LINK

//        GET THE https://www.amazon.com from the configuration.properties file
//        https://www.amazon.com   => ConfigReader.getProperty("key")
        Driver.getDriver().get(ConfigReader.getProperty("amazon_url"));
    }
}

