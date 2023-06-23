package step_defs.UI_step_defs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

public class DemoClass {

    @Test
    public void name() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/elements");

        driver.manage().window().maximize();

       // WebElement elements = driver.findElement(By.xpath("//h5[normalize-space()='Elements']"));



        System.out.println("Current URL = " + driver.getCurrentUrl());
        System.out.println("title = " + driver.getTitle());


    }

    @Test
    public void test2() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/elements");
        driver.manage().window().maximize();

        // is enabled() is displayed()

        WebElement element = driver.findElement(By.xpath("//div[normalize-space()='Elements']//span"));
        System.out.println("element.isDisplayed() = " + element.isDisplayed()); //true
        System.out.println("element.isEnabled() = " + element.isEnabled());

       // driver.wait(3);
        driver.get("https://demoqa.com/radio-button");
        driver.manage().window().maximize();

        WebElement yesbutton = driver.findElement(By.xpath("//label[normalize-space()='Yes']"));
        yesbutton.click();

        System.out.println("yesbutton.isSelected = " + yesbutton.isSelected());//ture

        WebElement impressivebttn = driver.findElement(By.xpath("//label[normalize-space()='Impressive']"));
        System.out.println("impressivebttn.isSelected = " + impressivebttn.isSelected());//false


    }
}
