package com.example.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Assignment3DDT {

    AndroidDriver<WebElement> driver;
    

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
       // capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("deviceName","NexusAVD");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage","com.mobeta.android.demodslv");
        capabilities.setCapability("appActivity","com.mobeta.android.demodslv.Launcher");

        driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void openappandops() throws InterruptedException {
        
    	//create object to identify element and perform click action
    	driver.findElementsById("com.mobeta.android.demodslv:id/activity_title").get(0).click();
    	
    	MobileElement firstelement = (MobileElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle").get(0);
    	MobileElement targetelement = (MobileElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle").get(4);
    	//Drag to desired option
    	TouchAction action = new TouchAction((AndroidDriver) driver).press(ElementOption.element(firstelement)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(ElementOption.element(targetelement)).release().perform();
    	
    	Thread.sleep(1000);
    	
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