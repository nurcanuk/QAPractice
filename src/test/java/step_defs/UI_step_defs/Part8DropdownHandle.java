package step_defs.UI_step_defs;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.Part8DropdownPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.List;

public class Part8DropdownHandle {
    Part8DropdownPage part8DropdownPage = new Part8DropdownPage();

    @Test
    public void countriesNames() {
        Driver.get().get(ConfigurationReader.get("globalsqa.url"));

        Select countryNames = new Select(part8DropdownPage.countries);

        List<WebElement> options = countryNames.getOptions();

        System.out.println("options.size() = " + options.size());


        for (WebElement eachOption : options) {
            //System.out.println("eachOption.getText() = " + eachOption.getText());

            if (eachOption.getText().equals("France")) {
                eachOption.click();
                break;
            }
        }
        //Select by visible text
        countryNames.selectByVisibleText("Bahamas");
        String actualCountries = countryNames.getFirstSelectedOption().getText();
        String expectedCountries = "Bahamas";
        Assert.assertEquals(actualCountries, expectedCountries);

        //Select by value
        countryNames.selectByValue("GAB");
        actualCountries = countryNames.getFirstSelectedOption().getText();
        expectedCountries = "Gabon";
        Assert.assertEquals(actualCountries, expectedCountries);


        //Select by index

        countryNames.selectByIndex(5);
        actualCountries = countryNames.getFirstSelectedOption().getText();
        System.out.println("actualCountries = " + actualCountries);
        expectedCountries = "Andorra";
        Assert.assertEquals(actualCountries, expectedCountries);

        Driver.closeDriver();

    }
    @Test
    public void bootstrapDropdown(){

    }
    @Test
    public void jqueryDropdown(){

    }
}