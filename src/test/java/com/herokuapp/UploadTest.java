package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.herokuapp.LoginTest.sleep;

public class UploadTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/upload";
    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().fullscreen();
        sleep(5);

        try {
            WebElement consimtamant = driver.findElement(By.xpath("//*[@class=\"fc-button-label\"]"));
            if (consimtamant.isDisplayed()) {
                consimtamant.click();
            }
        }
        catch (Exception e){
            throw new RuntimeException("Nu am gasit elementul");
        }

    }
    @Test
    public void testUpload(){
        WebElement fileUpload = driver.findElement(By.xpath("//input[@id='file-submit']"));

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
       // driver.close();
    }
}
