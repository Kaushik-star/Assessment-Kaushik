package com.example.myapplication;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class Assignment2 {

    AndroidDriver<WebElement> driver;
    Dimension size;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("deviceName", "NexusAVD");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.google.android.dialer");
        capabilities.setCapability("appActivity", "com.google.android.dialer.extensions.GoogleDialtactsActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void dailerAction() {
        TouchAction action = new TouchAction<>(driver);
        WebElement e = driver.findElementByAccessibilityId("key pad");

        
        int xCordinates = e.getLocation().getX();
        int yCordinates = e.getLocation().getY();
        action.tap(PointOption.point(xCordinates, yCordinates)).perform();

        e = driver.findElement(By.id("com.google.android.dialer:id/zero"));
        xCordinates = e.getLocation().getX();
        yCordinates = e.getLocation().getY();
        action.longPress(PointOption.point(xCordinates, yCordinates)).perform();

        String t = driver.findElement(By.id("com.google.android.dialer:id/digits")).getText();
        System.out.println(t);
        xCordinates = e.getLocation().getX();
        yCordinates = e.getLocation().getY();
    }

    @After
    public void endTest() throws IOException {
        Date d = new Date();
        String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path= System.getProperty("user.dir")+ "\\screenshots\\" + screenshotFile;
        FileUtils.copyFile(srcFile, new File(path));
        driver.quit();
    }
}