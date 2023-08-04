package pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;
import utilities.Driver;

import java.util.List;
import java.util.Random;

public class DropdownDemoQA {
    public DropdownDemoQA(){

        PageFactory.initElements(Driver.get(),this);

    }

    @FindBy(id = "oldSelectMenu")
    public WebElement selectColour;


    @FindBy(id = "cars")
    public WebElement selectCars;

    @FindBy(id = "withOptGroup")
    public WebElement selectValue;

    @FindBy(xpath = "//div[@class=' css-26l3qy-menu']")
    public WebElement values;

    @FindBy(xpath = "//div[@class=' css-yt9ioa-option']")
    public List<WebElement> groupsNames;

    @FindBy(xpath =  "(//div[@class=' css-tlfecz-indicatorContainer'])[2]")
    public WebElement clickSelectOne;

    @FindBy(xpath = "(//div[@class='mt-2 row'])[2]/div/div/div/div/div")
    public WebElement selectTitle;


    @FindBy(xpath="//div[@id='selectMenuContainer']//div[@class='row']//div[contains(@class,'css-1hwfws3')]")
    public WebElement multiSelectDropdown;

    @FindBy(xpath="//div[@class=' css-11unzgr']/div")
    public List<WebElement> multiSelectOptions;

    @FindBy(xpath = "//*[@class=\" css-1s9izoc\"]//div//div")
    public List<WebElement> selectOnesTable;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement amazonInputBox;
    @FindBy(xpath = "//div[@class='s-suggestion-container']/div/span")
    public List<WebElement> autoSuggestions;



    public static void randomDRPSelection(WebElement element, int limit) {
        Actions actions = new Actions(Driver.get());
        Random random = new Random();

        int randomItems = random.nextInt(limit);
        BrowserUtils.waitFor(3);

        actions.moveToElement(element).perform();

        element.click();
        for (int i = 0; i < randomItems; i++) {

            actions.sendKeys(Keys.ARROW_DOWN).perform();


        }
        actions.sendKeys(Keys.ENTER).perform();
        actions.sendKeys(Keys.ESCAPE).perform();
    }
    @FindBy(id = "searchbox")
    public WebElement searchBox;

    @FindBy(id = "ui-id-945")
    public WebElement aust;


}
