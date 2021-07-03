package com.techproed.paralleltesting;

import com.techproed.utilities.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Day18_SearchItem {

//    amazonSearch()   COMPLETE THEN ebaySearch()  COMPLETE THEN googleSearch() . Execution time: 24.29

    @Test
    public void googleSearch(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.google.com");
        ReusableMethods.waitFor(2);
        System.out.println("Google Title : "+driver.getTitle());
        driver.findElement(By.name("q")).sendKeys("porcelain teapot"+ Keys.ENTER);
    }
    @Test
    public void ebaySearch(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.ebay.com");
        ReusableMethods.waitFor(2);
        System.out.println("Resort line Title : "+driver.getTitle());
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("porcelain teapot"+Keys.ENTER);
    }

    @Test
    public void amazonSearch(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.amazon.com");
        ReusableMethods.waitFor(2);
        System.out.println("Amazon Title : "+driver.getTitle());
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("porcelain teapot"+ Keys.ENTER);

    }
}
