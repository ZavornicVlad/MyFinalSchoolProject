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

public class LogoutSouqeshop {
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

    @Parameters({"usernameP", "passwordP", "messageP"})
    @Test(testName = "Succesful logout")
    public void loginTest(String username, String password, String message) {

        login(username, password);
        sleep(5000);

        WebElement loginButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/header[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/a[1]/span[1]"));
        loginButton.click();
        sleep(2000);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

        sleep(2000);

        WebElement logoutButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/section[2]/div[1]/div[1]/div[1]/div[1]/nav[1]/ul[1]/li[6]/a[1]"));
        logoutButton.click();

        WebElement logoutConfirm = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/section[1]/div/div[2]/div/div[4]/div/div/div"));
        Assert.assertTrue(logoutConfirm.getText().contains(message));

    }

    private void login(String username, String password) {
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

