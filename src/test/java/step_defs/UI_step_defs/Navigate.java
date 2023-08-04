package step_defs.UI_step_defs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DropdownDemoQA;
import utilities.BrowserUtils;
import utilities.Driver;

import java.util.List;


public class Navigate {

    DropdownDemoQA dropdownDemoQA = new DropdownDemoQA();

    @Before
    public void beforeMethod() {

        Driver.get().get("https://amazon.com");
        Driver.get().manage().window().maximize();

    }

    @Test
    public void navigate() {
/*
        BrowserUtils.waitFor(5);
        WebElement element = Driver.get().findElement(By.xpath("//div[@id='nav-cart-count-container']"));
        element.click();               // amazon sepetime gidiyor

        BrowserUtils.waitFor(5);
        Driver.get().navigate().back();      // ana sayfaya geri donuyor

        BrowserUtils.waitFor(5);
        Driver.get().navigate().forward();   // tekrar sepetime gidiyor

        BrowserUtils.waitFor(5);
        Driver.get().navigate().refresh();   // sepetimi yeniliyor

        // driver.navigate().to("https://amazon.com");  driver.get() methoduyla ayni [navigate().to tek fark  URL instance kabul ediyor olmasi]*/

    }

    @Test
    public void autoSuggestDropdown() {

        BrowserUtils.waitAndClick(dropdownDemoQA.amazonInputBox, 4);


        dropdownDemoQA.amazonInputBox.sendKeys("selenium");
        BrowserUtils.waitFor(5);

        System.out.println("autoSuggestion.size() = " + dropdownDemoQA.autoSuggestions.size());


        for (WebElement each : dropdownDemoQA.autoSuggestions) {
            System.out.println("each.getText() = " + each.getText());

            if (each.getText().contains("drops")) {

                each.click();
                break;
            }

        }

    }

    @After
    public void tearDown() {
        //Driver.closeDriver();
    }


}
