package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckboxTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dropdown";

    @Parameters({"browserP"})
    @BeforeTest
    public void setUp(String browser) {
        switch (browser) {
            case "chrome": driver = new ChromeDriver();break;
            case "edge": driver = new EdgeDriver();break;
            case "firefox": driver = new FirefoxDriver();break;
            default: driver = new ChromeDriver();
        }
        driver.get(url);
        driver.manage().window().maximize();

    }
    @Test
    public void checkAll(){
        WebElement chechbox1 = driver.findElement(By.xpath("/html//form[@id='checkboxes']"));
        chechbox1.click();
        chechbox1.getAttribute("checked");
        Assert.assertTrue(chechbox1.getAttribute("checked").isEmpty());

        WebElement chechbox2 = driver.findElement(By.xpath("/html//form[@id='checkboxes']"));
        chechbox1.click();
        chechbox1.getAttribute("checked");
        Assert.assertFalse(chechbox2.getAttribute("checked").isEmpty());
    }



    @AfterTest(alwaysRun = true)
    public void tearDown() {
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();

    }
}
