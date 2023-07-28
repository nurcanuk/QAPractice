package step_defs.UI_step_defs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.DropdownDemoQA;
import utilities.BrowserUtils;
import utilities.Driver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DropdownTest {

    DropdownDemoQA dropdownDemoQA = new DropdownDemoQA();

    @Before
    public void setUp() {
        Driver.get().get("https://demoqa.com/select-menu");
        Driver.get().manage().window().maximize();

    }

    @Test
    public void oldStyleSelectDropdownTest() {

        Select colourSelect = new Select(dropdownDemoQA.selectColour);

        List<WebElement> options = colourSelect.getOptions();

        for (WebElement option : options) {
            System.out.println("option.getText() = " + option.getText());

        }

        System.out.println("options.size() = " + options.size());

        //1.Select Yellow --->select by visible text
        colourSelect.selectByVisibleText("Yellow");
        String actualOption = colourSelect.getFirstSelectedOption().getText();
        String expectedOption = "Yellow";
        Assert.assertEquals(expectedOption, actualOption);

        //2.Select Yellow --> Select by value
        colourSelect.selectByValue("3");
        actualOption = colourSelect.getFirstSelectedOption().getText();
        expectedOption = "Yellow";
        Assert.assertEquals(expectedOption, actualOption);

        //3.Select Yellow --> Select by index
        colourSelect.selectByIndex(3);
        actualOption = colourSelect.getFirstSelectedOption().getText();
        expectedOption = "Yellow";
        Assert.assertEquals(expectedOption, actualOption);


    }

    @Test
    public void standardMultiSelectTest() {

        Select multiSelectCars = new Select(dropdownDemoQA.selectCars);

        List<WebElement> carOptions = multiSelectCars.getOptions();

        for (WebElement carOption : carOptions) {

            System.out.println("carOption.getText() = " + carOption.getText());

        }
        multiSelectCars.selectByIndex(2);
        multiSelectCars.selectByValue("audi");
        multiSelectCars.selectByVisibleText("Volvo");

        for (WebElement options : multiSelectCars.getAllSelectedOptions()) {
            System.out.println("options.getText() = " + options.getText());

        }


    }
    @Test
    public void selectValueTest() {

dropdownDemoQA.selectValue.click();
        BrowserUtils.wait(5);

        for (WebElement eachName : dropdownDemoQA.groupsNames) {
            System.out.println(eachName.getText());
        }

    }

    @Test
    public void selectOneTest() {

            dropdownDemoQA.clickSelectOne.click();
            List<WebElement>  list = dropdownDemoQA.selectOnesTable;

        for (WebElement eachOptions : list) {
            System.out.println(eachOptions.getText());

        }

        //DropdownDemoQA.randomDRPSelection(dropdownDemoQA.clickSelectOne,5);

    }
    @Test
    public void multiSelect(){

        dropdownDemoQA.multiSelectDropdown.click();
        for (WebElement eachOption : dropdownDemoQA.multiSelectOptions) {
            System.out.println(eachOption.getText());
        }
    }

    @After
    public void tearDown() {
        Driver.closeDriver();

    }
}