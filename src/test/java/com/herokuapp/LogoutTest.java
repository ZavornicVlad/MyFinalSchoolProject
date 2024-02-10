package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest {
    @Test

    public void login() {
        // 1. deschide pagina Form Autent
        System.out.println("deschide pagina Form Authentification");
        WebDriver driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();

        System.out.println("Asteapta 2 secunde");
        //sleep(2000);


        // 2. click usser name & enter "pusser"
        WebElement ussernameImput = driver.findElement(By.id("username"));
        ussernameImput.sendKeys("tomsmith");
        //sleep(2000);
        System.out.println("Introdu usser ");

        // 3. click password and enter "pass
        WebElement passwordImput = driver.findElement(By.id("password"));
        passwordImput.sendKeys("SuperSecretPassword!");
        //sleep(2000);
        System.out.println("introdu parola");

        // 4. click login buton
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        // 5. click logout buton

        //WebElement logoutButton = driver.findElement(By.className("radius"));
        WebElement logoutButton = driver.findElement(By.linkText("Logout"));
        logoutButton.click();

        WebElement logoutMessage = driver.findElement(By.id("flash"));
        String logoutMesageContent = "You logged out of the secure area!";
        Assert.assertTrue(logoutMessage.getText().contains(logoutMesageContent));
    }
}