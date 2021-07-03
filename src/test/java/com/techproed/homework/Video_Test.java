package com.techproed.homework;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.text.NumberFormat;

public class Video_Test {
    @Test
    public void videoTest() {

        Driver.getDriver().get("https://www.wonderplugin.com/wordpress-lightbox");
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[contains(text(),'Open a Div in Lightbox')]"));
        element.click();
        System.out.println("found element and clicked");

        ReusableMethods.waitFor(3);
        WebElement frameElement = Driver.getDriver().findElement(By.xpath("//iframe[@src='https://www.youtube.com/embed/wswxQ3mhwqQ']"));

        Driver.getDriver().switchTo().frame(frameElement);
        Driver.getDriver().findElement(By.xpath("//button[@aria-label=\'Play\']")).click();
        Actions builder = new Actions(Driver.getDriver());
        WebElement we = Driver.getDriver().findElement(By.className("ytp-progress-bar-container"));
        Action mouseMovement = builder.moveToElement(we).build();
        mouseMovement.perform();
        we.click();
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
//        WebElement titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ytp-title-link")));
        WebElement titleText = ReusableMethods.waitForVisibility(By.className("ytp-title-link"),10);
        System.out.println(titleText.getText());

        WebElement time = ReusableMethods.waitForVisibility(By.cssSelector("span.ytp-time-current"),10);
        System.out.println(time.getText());

        WebElement totalTime = ReusableMethods.waitForVisibility(By.cssSelector("span.ytp-time-duration"),10);
        System.out.println(totalTime.getText());

        double currentTime1=Double.valueOf(time.getText().replace(":",""));
        double totalTime1=Double.valueOf(totalTime.getText().replace(":",""));
        System.out.println(currentTime1);
        System.out.println(totalTime1);

        double percent=(currentTime1/totalTime1);
        System.out.println(percent);

        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(2);
        System.out.println("Percent format: " + defaultFormat.format(percent));

    }
}
