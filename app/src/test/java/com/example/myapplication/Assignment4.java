package com.example.myapplication;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class Assignment4 {

    AndroidDriver driver;
    Dimension size;

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
        driver.navigate().to("https://www.amazon.com/");
        driver.findElement(By.id("nav-search-keywords")).sendKeys("Appium Book");
        driver.findElement(By.id("nav-search-keywords")).sendKeys(Keys.ENTER);
        
        //based on xpath - 10th result has a div tag with 12 and 11th with div[13]. declared 12 since, 10th result is desired
        int j = 12;
        for (int i=1; i<20; i++) {
            if (i == j) {

                //xpath for the span for Ratings

            	String ratingXpath = "//body/div[@id='a-page']/div[@id=\"search\"]/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[1]/div/div/a/div[2]/div/span[1]";
                
            	if ((driver.findElements(By.xpath(ratingXpath)).size() != 0)) {
                    System.out.println(driver.findElement(By.xpath(ratingXpath)).getAttribute("aria-label"));
                    //click on book
                    driver.findElement(By.xpath(ratingXpath)).click();
                    try{
                    	//click on Add to cart button. if button not present execute catch block
                    	driver.findElement(By.id("add-to-cart-button")).click();
                    	
                    } catch (Exception e) { //catch block created to handle kindle books or rent books. it will help in selecting the next book in search result
                		driver.navigate().back();
                		j=j+1;
                   	
                    }
                } else {
                    j=j+1; //in case rating tag is not present, take the next book
                        }
                    }
                }
            j = 0; //initializing variable to use for selenium book search
            
            
        driver.navigate().back();
        driver.navigate().back();
        driver.findElement(By.id("nav-search-keywords")).clear();
        driver.findElement(By.id("nav-search-keywords")).sendKeys("Selenium Book");
        driver.findElement(By.id("nav-search-keywords")).sendKeys(Keys.ENTER);

        j = 7;
        for (int i=1; i<20; i++) {
            if (i == j) {

                //xpath for the span for Ratings

            	String ratingXpath = "//body/div[@id='a-page']/div[@id=\"search\"]/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[1]/div/div/a/div[2]/div/span[1]";
                
            	if ((driver.findElements(By.xpath(ratingXpath)).size() != 0)) {
                    driver.findElement(By.xpath(ratingXpath)).click();
            		List<WebElement> cmnts = driver.findElements(By.className("review-text-sub-contents"));
                    for(WebElement cmnt:cmnts)
                    {
                     //print all user comments
                    System.out.println(cmnt.getText());
                     
                    }
            		try{
                    	//click on Add to cart button. if button not present execute catch block
                    	driver.findElement(By.id("add-to-cart-button")).click();

                    } catch (Exception e) {
                		driver.navigate().back();
                		j=j+1;
                   	
                    }
                } else {
                    j=j+1;
                        }
                    }
                }
        //print subtotal block
        List<WebElement> total = driver.findElements(By.id("sc-proceed-to-checkout-params-form"));
        
        for(WebElement e:total)
        {
         
        System.out.println(e.getText());
         
        }
    
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
