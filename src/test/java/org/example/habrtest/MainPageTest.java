package org.example.habrtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void changeLogTest() {
        WebElement searchSiteDevice = driver.findElement(By.xpath("//a[contains(text(), 'Устройство сайта')]"));
        searchSiteDevice.click();
        List<WebElement> searchChangelog = driver.findElements(By.xpath("//a[contains(text(), 'Changelog')]"));
        assertFalse(searchChangelog.isEmpty(), "Changelog не найден");
    }
    @Test
    public void forgotPassword() {
        WebElement logButton = driver.findElement(By.xpath("//a/button"));
        logButton.click();
        List <WebElement> searchForgotPassword = driver.findElements(By.xpath("//a[contains(text(), 'Забыли пароль')]"));
        assertFalse(searchForgotPassword.isEmpty(), "На странице логирования нет кнопки Забыли пароль");
    }
    @Test
    public void searchComments() {
        WebElement searchButton = driver.findElement(By.cssSelector("[class*='tm-header-user-menu__icon_search']"));
        searchButton.click();
        String input = "Java";
        WebElement searchField = driver.findElement(By.cssSelector("input[name = 'q']"));
        searchField.sendKeys(input);
        searchField.submit();
        List<WebElement> searchComments = driver.findElements(By.xpath("//span[contains(@class,'tm-tabs__tab-item')]//*[contains(text(),'Комментарии')]"));
        assertFalse(searchComments.isEmpty(), "На странице нет блока комментарии");
    }
}
