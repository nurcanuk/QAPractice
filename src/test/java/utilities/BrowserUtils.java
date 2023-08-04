package utilities;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class BrowserUtils {
    Random random = new Random();

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue("Element is not visible: " + by, Driver.get().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element is not found: " + by);
        }
    }


    public static void verifyElementDisplayed(WebElement element) {
        BrowserUtils.waitFor(2);
        try {
            Assert.assertTrue("Element is not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void verifyElementDisplayedWithJS(WebElement element) {
        try {
            ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
            Assert.assertTrue("Element is not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse("Element should not be visible: " + by, Driver.get().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public static void verifyElementNotDisplayed(WebElement element) {
        try {
            Assert.assertFalse("Element should not be visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public static void verifyElementClickable(WebElement element) {
        try {
            Assert.assertTrue("Element is not clickable: " + element, element.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void verifyElementEnabled(WebElement element) {
        try {
            Assert.assertTrue("Element is not enabled: " + element, element.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element is not found: " + element);
        }
    }

    public static void verifyElementDisabled(WebElement element) {
        try {
            Assert.assertTrue("Element is not enabled: " + element, Boolean.parseBoolean(element.getAttribute("disabled")));
            System.out.println(element.getAttribute("disabled"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element is not found: " + element);
        }
    }

    public static void verifyElementEnabled(By by) {
        try {
            Assert.assertTrue("Element is not enabled: " + by, Driver.get().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element is not enabled: " + by);
        }
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element);
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.get().getWindowHandle();
        for (String handle : Driver.get().getWindowHandles()) {
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.get().switchTo().window(origin);
    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsByText(By locator) {
        List<WebElement> elements = Driver.get().findElements(locator);
        List<String> elementTexts = new ArrayList<>();
        for (WebElement el : elements) {
            if (!el.getText().isEmpty()) {
                elementTexts.add(el.getText());
            }
        }
        return elementTexts;
    }

    public static List<String> getElementsText(By locator) {
        List<WebElement> elements = Driver.get().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elements) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static String getSingleElementText(By locator) {
        return Driver.get().findElement(locator).getText();
    }


    public static String generateAName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();

        // Generate the first uppercase letter
        char firstChar = (char) (random.nextInt(26) + 'A');
        name.append(firstChar);

        // Generate the remaining four lowercase letters
        for (int i = 0; i < 4; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            name.append(randomChar);
        }
        return name.toString();
    }

    public static String generateAnEmail() {
        Random random= new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder();

        int emailLength = 5; // Specify the desired length of the random part of the email
        for (int i = 0; i < emailLength; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            email.append(randomChar);
        }

        String domain = "@mailsac.com";
        String prefix = "test__";

        return prefix + email + domain;
    }

    public static String generateAPassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$ºª%&/()=?^*:[ ],.-_+";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));

        for (int i = 4; i < length; i++) {
            password.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
        }
        return password.toString();
    }

    public static void switchToWindowWithIndex(int index) {
        List<String> windowHandles = new ArrayList<>(Driver.get().getWindowHandles());
        Driver.get().switchTo().window(windowHandles.get(index));
    }

    //it seems OK but check it out. DataTableListMap.feature daki kodlari kullanabilirsin
    public static void waitForPresenceOfElement(WebElement element, int timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeOutInSecond));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static By getLocatorFromWebElement(WebElement element) {
        String elementDescription = element.toString();
        String locator = elementDescription.split("->")[1].replaceFirst("(?s)(.*)]", "$1" + " ").trim();

        return By.cssSelector(locator);
    }

    public static void goToHomePage() {
        BrowserUtils.wait(2);
        Driver.get().findElement(By.cssSelector(".react-header-logo")).click();

        BrowserUtils.wait(2);
    }

    public static void verifyVideoPlays(WebElement element) {
        // Precondition: User clicks on the video
        try {
            ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
            waitFor(1);
            boolean isPlaying = (boolean) ((JavascriptExecutor) Driver.get()).executeScript("return arguments[0].paused === false;", element);
            Assert.assertTrue(isPlaying);
        } catch (Exception ex) {
            Assert.fail("You didn't click on the play button.");
        }
    }

    public static WebElement getElementFromGetText(String text) {
        By element = By.xpath("//*[contains(text(), '" + text + "')]");
        WebElement found;
        try {
            List<WebElement> elements = Driver.get().findElements(element);
            found = Driver.get().findElement(element);
            if (elements.size() != 1) {
                System.out.println(elements.size() + " element found. Check if the element is yours");
            }
            return found;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Element not found");
            return null;
        }
    }

    public static void sendKeysWithJS(WebElement element, String sendKeys) {
        WebDriver driver = Driver.get();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].setAttribute('value', arguments[1])", element, sendKeys);
    }

    public static void mySendKeysMethod(WebElement webElement, String sendText) {
        Wait<WebDriver> wait = new FluentWait<>(Driver.get()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");
        wait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(sendText);
    }

    public static void mySendKeysMethod(By locator, String sendText) {
        WebElement element = Driver.get().findElement(locator);
        Wait<WebDriver> wait = new FluentWait<>(Driver.get()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(sendText);
    }

    public static void myClickMethod(By locator) {

        WebElement element = Driver.get().findElement(locator);
        Wait<WebDriver> wait = new FluentWait<>(Driver.get()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void myClickMethod(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(Driver.get()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }





    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        BrowserUtils.waitForVisibility(element, 10);
    }

    public static void scrollToView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.get();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static void hoverOver(WebElement element) {
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).perform();
    }

    public static void moveCursor(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void dragAndDrop(int startX, int startY, int endX, int endY) {
        try {
            Robot robot = new Robot();

            // Move the cursor to the starting position
            robot.mouseMove(startX, startY);

            // Press the left mouse button
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

            // Move the cursor to the ending position
            robot.mouseMove(endX, endY);

            // Release the left mouse button
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    //login for hypnotes
    public static void login(String username, String password) {
        Driver.get().findElement(By.cssSelector("[href='/api/login']")).click();
        Driver.get().findElement(By.xpath("//input[@type='email']")).sendKeys(username);
        Driver.get().findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        Driver.get().findElement(By.xpath("//button[@type='submit']")).click();
    }



    //this method will clear text box
    public static void cleanTextInBox1(WebElement element) {
        String inputText = element.getAttribute("value");
        if (inputText != null) {
            for (int i = 0; i < inputText.length(); i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }
    }

    public static void cleanTextInBox(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            // Handle the exception as per your requirements
            e.printStackTrace();
        }
    }

    public static void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitAndSendText(WebElement element, String text, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static String waitAndGetText(WebElement element, int timeout) {
        String text;
        for (int i = 0; i < timeout; i++) {
            try {
                text = element.getText();
                return text;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
        return null;
    }

    public static void wait(int secs) {
        try {
            Thread.sleep(1000L * secs);
        } catch (InterruptedException | NoSuchElementException | StaleElementReferenceException |
                ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }

    public static Boolean waitForInVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void selectAnItemFromDropdown(WebElement item, String selectableItem) {
        wait(5);
        Select select = new Select(item);
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equalsIgnoreCase(selectableItem)) {
                select.getOptions().get(i).click();
                break;
            }
        }
    }

    public static String getTodayMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        Date date = new Date();
        return format.format(date);
    }

    public static String getTodaysDay() {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        Date date = new Date();
        return format.format(date);
    }

    public static String getDateForLocation(int dayToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(cal.getTime());
    }

    public static String getDateForCoupon(int dayToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
        return format1.format(cal.getTime());
    }

    public static String getDateForEvent(int dayToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        return format1.format(cal.getTime());
    }

    public static String getYear_month_day_time(int hourToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hourToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format1.format(cal.getTime());
    }

    public static String getDay_day_month_year_time(int hourToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hourToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        return format1.format(cal.getTime());
    }
    public static String getDay_year_month_day_time(int hourToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hourToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
        return format1.format(cal.getTime());
    }

//    public static void staleElementSolution(String date) {
//        Duration timeout = Duration.ofSeconds(30);
//        new WebDriverWait(driver, timeout)
//                .ignoring(StaleElementReferenceException.class)
//                .until((WebDriver d) -> {
//                    d.findElement(By.xpath("//td[@data-date='" + date + "']//div[@class='fc-daygrid-day-top']")).click();
//                    return true;
//                });
//    }

    public static int booleanToInt(boolean input) {
        return input ? 1 : 0;
    }

    public static String getTodayMonthYear() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public static String getTodayYear_month_day() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    public static boolean isFutureDate(String date) {
        String todayDate = BrowserUtils.getTodayYear_month_day();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean b = false;
        try {
            b = sdf.parse(todayDate).before(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String getTodayMonthDayYear() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public static String getTodayMonthDayYear2() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public static String getDateForMail() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    public static String getTimeForMail() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
        Date date = new Date();
        return format.format(date);
    }

    public static String getDateForGroupSession() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();

        return format.format(date);
    }
    public static String getDayOfWeek(){
        LocalDate bugun = LocalDate.now(); // Şu anki tarihi alır
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        return bugun.format(formatter);
    }

    public static String getTime(){
        LocalTime now = LocalTime.now(); // Şu anki saati alır

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return now.format(formatter);
    }





    //    Parameter1 : WebElement
    //    Parameter2:  int
    //    Driver.selectByIndex(dropdown element, 1)
    public static void selectByIndex(WebElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }

    //    Parameter1 : WebElement
    //    Parameter2:  String
    //    Driver.selectByIndex(dropdown element, "91303")
    public static void selectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        List<WebElement> elementCount = objSelect.getOptions();
        objSelect.selectByValue(value);
        System.out.println("number of elements: " + elementCount.size());
    }

    public static void waitAndClickLocationText(WebElement element, String value) {
        Driver.get().findElement(By.xpath("//*[text()='" + value + "']")).click();
    }

    public static void scrollUntilEnd(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }



    public static <T> Set<T> mergeSet(Set<T> a, Set<T> b) {

        // Adding all elements of respective Sets
        // using addAll() method
        return new HashSet<T>() {
            {
                addAll(a);
                addAll(b);
            }
        };
    }

    public static void setClipBoard(String filePath) {
        StringSelection selection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }






    /**
     * webelement listesindeki elemenlar arasinda verilen obje adini gorene kadar arrow down a basiyor
     *
     * @param tList        bakilacak webelement listesi
     * @param objectToLook bakilacak obje adi
     */
    public static void clickStringInTheList(List<WebElement> tList, String objectToLook) {
        Actions actions= new Actions(Driver.get());
        for (int i = 0; i < tList.size() * 2; i++) {

            try {
                if (tList.stream().noneMatch(t -> t.getText().contains(objectToLook))) {
                    throw new Exception();
                }
            } catch (Exception e) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                wait(1);
            }
        }
    }

    public static void staleElementSolutions(String date) {
        Duration timeout = Duration.ofSeconds(10);
        new WebDriverWait(Driver.get(), timeout)
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    d.findElement(By.xpath("//td[@data-date='" + date + "']//div[@class='fc-daygrid-day-top']")).click();
                    return true;
                });
    }

    public static String getDateForCalendar(int dayToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(cal.getTime());
    }

    public static String dayToSkipForSession(int dayToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        return format1.format(cal.getTime());
    }

    public static String hourToSkipForSession(int hourToSkip) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hourToSkip);
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm a");
        return format1.format(cal.getTime());
    }

    public static boolean isExist(WebElement element) {
        boolean b;
        try {
            b = element.isDisplayed() || !element.isDisplayed();
        } catch (Exception var3) {
            b = false;
        }
        return b;
    }

    public static void assertTexts(String expectedText, WebElement element) {
        Assert.assertEquals(expectedText, element.getText());
    }

    public static boolean isWebElementVisible(WebElement webElement) {
        boolean flag;

        try {
            webElement.isDisplayed();
            flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        }
        return flag;
    }

    public static String getDigitFromString(String str) {

        String out = "";
        String strPattern = "\\d+";

        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            out = matcher.group();
        }
        return out;
    }

    public static void clearLocalSessionCookies() {
        LocalStorage local = ((WebStorage) Driver.get()).getLocalStorage();
        SessionStorage session = ((WebStorage) Driver.get()).getSessionStorage();
        local.clear();
        session.clear();
        Driver.get().manage().deleteAllCookies();
    }

    public static void addSeconds(int secondsToSkip) {
        Calendar calendarFuture = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendarFuture.add(Calendar.SECOND, secondsToSkip);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm ss");

        System.out.println("calendar.getTime().getTime() = " + dateFormat.format(calendar.getTime()));
        System.out.println("calendarfuture.getTime().getTime() = " + dateFormat.format(calendarFuture.getTime()));

        while (!dateFormat.format(calendar.getTime()).equals(dateFormat.format(calendarFuture.getTime()))) {
            calendar = Calendar.getInstance();
//            System.out.println(dateFormat.format(calendar.getTime()) + " : " + dateFormat.format(calendarfuture.getTime()));
//            wait(1000);
        }
    }

    public static void selectNewWindow() {
        if (Driver.get().getWindowHandles().size() > 1) {
            String parentWindow = Driver.get().getWindowHandle();
            Set<String> handles = Driver.get().getWindowHandles();
            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    Driver.get().switchTo().window(windowHandle);
                    break;
                }
            }
        } else {
            selectNewWindow();
        }
    }




    public static void clickWithRetry(WebElement element) {
        int attempts = 0;
        boolean clicked = false;
        while (attempts < 3 && !clicked) { // Try clicking up to 3 times
            try {
                element.click();
                clicked = true;
            } catch (StaleElementReferenceException e) {
                // If the element is no longer attached to the DOM, try again
                attempts++;
            }
        }
    }

    //this method will clear text box
    public static void cleanTextInBox2(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);

    }

    //this method will select one or more options from a dropdown
    public static void selectValuesFromDropdown(List<WebElement> dropdownOptions, String... options){

        for (WebElement item: dropdownOptions){

            String text = item.getText();

            for(String option : options){

                if (text.equals(option)){

                    item.click();

                    break;
                }
            }
        }

    }











}

