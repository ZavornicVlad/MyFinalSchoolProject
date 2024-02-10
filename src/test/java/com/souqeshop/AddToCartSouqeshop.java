package com.souqeshop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddToCartSouqeshop {

    WebDriver driver;
    String url = "https://souqeshop.ro/pachet-5kw-9-panouri-540-jrh-invertor-52-kw-hybird-pro-4-acumlatori-120a-mufe-mc4-si-cabluri-20mx4mm/";

    @Parameters({"cartDropdown"})
    @BeforeTest
    public void setUp(String browser){
        switch (browser){
            case "chrome": driver = new ChromeDriver();break;
            case "edge": driver = new EdgeDriver();break;
            case "firefox": driver = new FirefoxDriver();break;
            default:driver = new ChromeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test(testName = "AddToCart")
    public void AddToCart(){
        sleep(2000);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebElement addButton = driver.findElement(By.xpath("//button[@name='add-to-cart']"));
        String string = addButton.getText();
        action.sendKeys(Keys.PAGE_UP).build().perform();

        addButton.click();

        WebElement cartButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/header[1]/div[1]/section[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]/i[1]"));
        cartButton.click();
        sleep(2000);

        WebElement cartDropdown = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/header[1]/div[1]/section[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[1]/div[2]/a[1]"));
        Assert.assertTrue(cartDropdown.getText().contains("pachet 5kw"));



        //String addToCart = addButton.getText();
        //at.sendKeys(Keys.PAGE_UP).build().perform();



    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    }

    public static void sleep(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
