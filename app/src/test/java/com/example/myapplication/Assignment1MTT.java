package com.example.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Assignment1MTT {

    AndroidDriver<WebElement> driver;
    Dimension size;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("deviceName","Pixel 3 API 26");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage","com.the511plus.MultiTouchTester");
        capabilities.setCapability("appActivity","com.the511plus.MultiTouchTester.MultiTouchTester");

        /*String appli = "com.mobeta.android.demodslv-0.5.0-3_APKdot.com.apk";
        File application = new File(System.getProperty("user.dir") + "\\app\\APK\\" + appli);
        capabilities.setCapability(MobileCapabilityType.APP, application.getAbsolutePath());*/
        //capabilities.setCapability("app", "C:\\Users\\lokes\\AndroidStudioProjects\\MyApplication\\app\\APK_Files\\com.mobeta.android.demodslv-0.5.0-3_APKdot.com.apk");
        driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void mulitTouchAction() {
        size = driver.manage().window().getSize();
        //Get X Y Coordinates for touch action 1(Top left side).
        int x1 = (int) (size.width * 0.20);
        int y1 = (int) (size.height * 0.20);

        //Get X Y Coordinates for touch action 2(Top right side).
        int x2 = (int) (size.width * 0.80);
        int y2 = (int) (size.height * 0.20);

        //Get X Y Coordinates for touch action 3(Bottom left side).
        int x3 = (int) (size.width * 0.20);
        int y3 = (int) (size.height * 0.80);

        //Get X Y Coordinates for touch action 4(Bottom right side).
        int x4 = (int) (size.width * 0.80);
        int y4 = (int) (size.height * 0.80);

        //Get X Y Coordinates for touch action 5(middle of the screen).
        int x5 = size.width / 2;
        int y5 = size.height / 2;

        // Create object of MultiTouchAction class.
        MultiTouchAction maction = new MultiTouchAction((AndroidDriver) driver);
        // Set touch action1 on given X Y Coordinates of screen.
        TouchAction action1 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x1, y1));
        // Set touch action2 on given X Y Coordinates of screen.
        TouchAction action2 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x2, y2));
        // Set touch action3 on given X Y Coordinates of screen.
        TouchAction action3 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x3, y3));
        // Set touch action4 on given X Y Coordinates of screen.
        TouchAction action4 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x4, y4));
        // Set touch action5 on given X Y Coordinates of screen.
        TouchAction action5 = new TouchAction((AndroidDriver) driver).longPress(PointOption.point(x5, y5));

        // Generate multi touch action chain using different actions and perform It.
        maction.add(action1).add(action2).add(action3).add(action4).add(action5).perform();
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