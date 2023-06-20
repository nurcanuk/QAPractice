package step_defs.UI_step_defs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

public class DemoClass {
    public static void main(String[] args) {

       Driver.get().get(ConfigurationReader.get("url"));

//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://urbanicfarm.com/");


    }
}
