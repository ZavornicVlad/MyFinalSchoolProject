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

public class SearchSouqeshop {

    WebDriver driver;
    String url = "https://souqeshop.ro/";

    @Parameters({"browserP"})
    @BeforeTest
    public void setUp(String browser) {
        switch (browser) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();
    }

    @Parameters({"searchMessageP"})
    @Test
    public void searchTest(String mesage) {
        WebElement searchField = driver.findElement(By.xpath("/html//header[@id='tbay-header']/div[@class='elementor elementor-3157']/section[2]/div/div[2]//div[@class='elementor-element elementor-element-054c69e elementor-widget elementor-widget-besa-search-form']//form[@action='https://souqeshop.ro/']//input[@name='s']"));
        searchField.sendKeys(mesage);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();

        WebElement searchSucces = driver.findElement(By.xpath("//*[@id=\"tbay-breadscrumb\"]/div/div/ol"));
        Assert.assertTrue(searchSucces.getText().contains(mesage));
    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        //Inchide pagina
        System.out.println("Inchide pagina");
        driver.close();
    }

    public static void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

