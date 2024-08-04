package com.example.findmypg.whatsAppService;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class WhatsAppService {

    public void sendMessage(String phoneNumber, String message) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Set the user-data-dir to your Chrome profile path
        options.addArguments("user-data-dir=C:/Users/venka/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://web.whatsapp.com");

        // Wait for the page to load
        Thread.sleep(15000);

        // Search for the contact by phone number
        WebElement searchBox = driver.findElement(By.xpath("//div[@contenteditable='true'][@data-tab='3']"));
        searchBox.sendKeys(phoneNumber);
        searchBox.sendKeys(Keys.ENTER);

        // Wait for the chat to open
        Thread.sleep(5000);

        // Find the message input box and send the message
        WebElement messageBox = driver.findElement(By.xpath("//div[@contenteditable='true'][@data-tab='9']"));
        messageBox.sendKeys(message);
        messageBox.sendKeys(Keys.ENTER);

        // Wait for the message to be sent
        Thread.sleep(2000);

        driver.quit();
    }
}