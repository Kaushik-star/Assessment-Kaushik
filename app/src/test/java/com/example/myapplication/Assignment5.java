package com.example.myapplication;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Assignment5 {

    AndroidDriver<WebElement> driver;
    

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("deviceName", "NexusAVD");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "CHROME");
        capabilities.setCapability("chromedriverExecutable",System.getProperty("user.dir") + "\\app\\drivers\\chromedriver.exe");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities) {};
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void browserTest(){
    	
    	//Step 1. Go to Chase.com
        driver.navigate().to("https://www.chase.com/");    	
        driver.findElement(By.id("skip-sidemenu")).click();
        
        //2. Open Menu (by clicking on sandwich icon on top left corner)
        driver.findElement(By.xpath("//div[@class='side-menu']//div[5]//div[2]//ul[1]//li[1]//a[1]")).click();        
        //Click on Customer Service
        String populartopicxpath = "//div[@id='Module2']/div/div[1]/div/div[2]/div[@id='SubHeaderOriginal']/ul/li[2]/a";
        //Scroll down
        Utilities.ScrollUtil.scrollDown(driver);
      //4. Click on Popular Topics available on Customer service page
        driver.findElement(By.xpath(populartopicxpath)).click();
        //5.Click on Search More Topics and search for any random topic 
        driver.findElement(By.xpath("//a[contains(text(),'Search more topics')]")).click();
        //6. Go back to Home page (Initial page from where you clicked on customer Service link)
        driver.findElement(By.xpath("//input[@placeholder='search']")).sendKeys("mobile");
        driver.findElement(By.xpath("//input[@placeholder='search']")).sendKeys(Keys.ENTER);
        //6.1 print the result
        try {
        	String result = driver.findElement(By.id("resultsmessage")).getText();
        	System.out.println(result);
        }catch (Exception e){
        	String result = driver.findElement(By.id("noresultsmessage")).getText();
        	System.out.println(result);
        }
        //7. go to home page
        driver.findElement(By.xpath("//span[@class='chase-text']")).click();
        //8. there is no give feedback page so clicked on chase complaints and feedback
        Utilities.ScrollUtil.scrollToElement(By.linkText("Chase complaints and feedback"), driver);
        driver.findElement(By.linkText("Chase complaints and feedback")).click();
        //There is no give feedback form to fillin. Completing the test here
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



