package com.souqeshop;

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

public class LoginSouqeshop {
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
    @Parameters({"usernameP","passwordP","successMessageP"})
    @Test(testName = "login souqeshop")

    public void login(String username, String password, String message) {
        System.out.println("Asteapta 2 secunde");
        sleep(2000);
        WebElement loginButon = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/header[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/a[1]/span[1]"));
        loginButon.click();
        sleep(5000);

        WebElement usernameImput = driver.findElement(By.name("username"));
        usernameImput.sendKeys(username);

        WebElement passwordImput = driver.findElement(By.name("password"));
        passwordImput.sendKeys(password);

        WebElement butonLogare = driver.findElement(By.xpath("//form[@id='custom-login']/input[@value='Logare']"));
        butonLogare.click();
        sleep(5000);

        WebElement loginConfirm = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/section[1]/div/div[2]/div/div[4]/div/div/div/a/span"));
        Assert.assertTrue(loginConfirm.getText().contains("Buna ZavorniVlad"));


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
