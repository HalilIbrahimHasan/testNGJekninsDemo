package com.techproed.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {
    //Similar to TestBase, This is a utilities class
    private static WebDriver driver;
    //setup, create, and return the driver instance
    public static WebDriver getDriver(){
       /*
       If driver is not being used, if it is not pointing anywhere, then instantiate the driver
       We want to use only one driver in the entire framework
        */
        if(driver==null) {
//            create chromedriver if browser in configuration.properties file is chrome
//            create firefox if browser in configuration.properties file is firefox
//            ....
            switch (ConfigReader.getProperty("browser")) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;

//                headless browser means, running the test case without opening the browser on the screen
//                browser runs in the background
//                This is not recommended
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    options.addArguments("disable-gpu");
                    driver = new ChromeDriver(options);
                    break;
            }
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }//getDriver ends here
    //create closeDriver method to close teh driver
    public static void closeDriver(){
        if (driver!=null) {//if driver is pointing anywhere
            driver.quit();//quit when I call closeDriver
            driver=null;//make the driver null so when we call getDriver, we can open the driver again
        }
    }
}

