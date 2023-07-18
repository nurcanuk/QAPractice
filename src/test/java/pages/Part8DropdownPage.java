package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Part8DropdownPage {

    public Part8DropdownPage(){
        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy(xpath ="//*[@id=\"post-2646\"]/div[2]/div/div/div/p/select")
    public WebElement countries;
}
