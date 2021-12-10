import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class FirstTest {
    List<WebElement> list = new LinkedList<>();
    private AppiumDriver driver;
    String searchWord = "Java";
    String searchSite = "Java (programming language)";
    String nameFolder = "Learning Programming";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:\\Users\\amoroz\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void ex5() {
        //добавляем первую статью в закладки
        addSiteToBookMark(searchWord, searchSite);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'GOT IT')]"),
                "Cannot find button 'GOT IT'",
                5);
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input field",
                15);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameFolder,
                "Cannot put text in field",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot find button 'OK'",
                15);
        waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'Navigate up')]"),
                "Cannot find close article button 'X'",
                15);


        searchWord = "Appium";
        searchSite = searchWord;
        //добавляем вторую статью в созданную закладку
        addSiteToBookMark(searchWord, searchSite);
        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.LinearLayout']//*[contains(@text, '" + nameFolder + "')]"),
                "Cannot find button 'OK'",
                15);
        waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'Navigate up')]"),
                "Cannot find close article button 'X'",
                15);
        //Переход в меню закладок, выбор ранее созданной.
        waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'My lists')]"),
                "Cannot find button 'My Lists'",
                5);
        waitForElementAndClick(By.xpath("//*[contains(@text, '" + nameFolder + "')]"),
                "Cannot find folder",
                5);
        //Удаление закладки "Java (programming language)"
        swipeElementToLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot swipe article");
        waitElementNotPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot delete article",
                15);
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + searchSite + "')]"),
                "Cannot found ths site" + searchSite + " ",
                5);
        //Переход во вторую сохраненную закладку и провека "Title"
        waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + searchSite + "')]"),
                "Cannot tip by " + searchSite + " ",
                5
        );
        WebElement element = driver.findElement(By.id("org.wikipedia:id/view_page_title_text"));
        Assert.assertTrue("Title is not equals name Site",
                searchSite.equals(element.getText()));
    }

    public void addSiteToBookMark(String searchSiteByName, String openSite) {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchSiteByName,
                "error",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView' " +
                        "and contains(@text,'" + openSite + "')]"),
                "errorClick",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "error",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button 'More Options'",
                5);
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"),
                "Cannot find option to add article to reading list",
                5);
    }

    @Test
    public void ex6() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchWord,
                "error",
                5);

        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView' " +
                        "and contains(@text,'" + searchSite + "')]"),
                "errorClick",
                5);
        assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
    }

//    //Обучение
//    @Test
//    public void saveArticleToMyList() {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "errorClick",
//                5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
//                "errorClick",
//                5);
//        waitForElementPresent(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "error",
//                15
//        );
//        waitForElementAndClick(
//                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
//                "Cannot find button 'More Options'",
//                5);
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Add to reading list')]"),
//                "Cannot find option to add article to reading list",
//                5);
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'GOT IT')]"),
//                "Cannot find button 'GOT IT'",
//                5);
//        waitForElementAndClear(
//                By.id("org.wikipedia:id/text_input"),
//                "Cannot find input field",
//                15);
//
//        String nameFolder = "Learning Programming";
//
//        waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                nameFolder,
//                "Cannot put text in field",
//                5);
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'OK')]"),
//                "Cannot find button 'OK'",
//                5);
//        waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'Navigate up')]"),
//                "Cannot find close article button 'X'",
//                5);
//        waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'My lists')]"),
//                "Cannot find button 'My Lists'",
//                5);
//        waitForElementAndClick(By.xpath("//*[contains(@text, '" + nameFolder + "')]"),
//                "Cannot find folder",
//                5);
//        swipeElementToLeft(
//                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
//                "Cannot swipe article");
//        waitElementNotPresent(
//                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
//                "Cannot delete article",
//                5);
//    }
//
//    @Test
//    public void testSwipeArticle() {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "errorClick",
//                5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
//                        "//*[@text='Object-oriented programming language']"),
//                "errorClick",
//                5);
//
//        waitForElementPresent(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "error",
//                15
//        );
//        swipeUpToFindElement(
//                By.xpath("//*[@text = 'View page in browser']"),
//                "error",
//                50);
//    }
//
//    @Test
//    public void ex2() {
//        assertElementHasText(
//                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']" +
//                        "//*[@class='android.widget.TextView']"),
//                "Search Wikipedia",
//                "Ожидаемый текст отсутствует по указанному локатору");
//    }
//
//    @Test
//    public void ex3() {
//
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "errorClick",
//                5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//
//        list.addAll(driver.findElementsById("org.wikipedia:id/page_list_item_container"));
//        checkCountUrls(list);
//
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "error",
//                5);
//
//        assertElementHasText(
//                By.xpath("//*[@text='Search…']"),
//                "Search…",
//                "Ожидаемый текст отсутствует по указанному локатору");
//    }
//
//    @Test
//    public void ex4() {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "errorClick",
//                5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//        //xpathShort - *[@resource-id='org.wikipedia:id/page_list_item_title']
//        //id - org.wikipedia:id/page_list_item_title
//        list.clear();
//        list.addAll(driver.findElementsById("org.wikipedia:id/page_list_item_title"));
//        checkWordInputAllUrls(list);
//    }
//
//    @Test
//    public void firstTests() {
//
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "errorClick",
//                5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//        waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
//                "error",
//                15);
//    }
//
//    @Test
//    public void testCancelSearch() {
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "error",
//                5);
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//        waitForElementAndClear(
//                By.id("org.wikipedia:id/search_src_text"),
//                "error",
//                5
//        );
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "error",
//                5);
//        waitElementNotPresent(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "error",
//                5);
//    }
//
//    @Test
//    public void testCompareArticleTitle() {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "errorClick",
//                5);
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "error",
//                5
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
//                "errorClick",
//                5);
//        waitForElementPresent(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "error",
//                15
//        );
//        WebElement titleElement = waitForElementPresent(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "error",
//                15
//        );
//    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitElementNotPresent(By by, String message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeElementToLeft(By by, String message) {
        WebElement element = waitForElementPresent(
                by,
                message,
                10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(300);
    }

    protected void swipeUpToFindElement(By by, String message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            swipeUpQuick();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find elements " + message, 0);
                break;
            }
        }
    }

    private WebElement assertElementHasText(By by, String expected, String message) {
        WebElement webElement = waitForElementPresent(
                by,
                "error",
                15
        );
        Assert.assertEquals(message, expected, webElement.getText());
        return webElement;
    }

    private void checkCountUrls(List list) {
        Assert.assertTrue("Количество статей менее либо равно '1'", list.size() > 1);
    }

    private void checkWordInputAllUrls(List<WebElement> list) {
        for (WebElement element : list) {
            Assert.assertTrue("В указанной ссылке отсутствует искомое слово в названии",
                    element.getText().contains("Java"));
        }
    }

    private int getAmountElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementsNotPresent(By by, String message) {
        int amount_of_elements = getAmountElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + message);
        }
    }

    private void assertElementPresent(By by) {
        //String s = driver.findElement(by).getText();
        //driver.findElement(By.context).getText();;
        //System.out.println(this.driver.getTitle());
        //waitForElementPresent(by,"Title is not download");
        Assert.assertNotNull("Title is not found", driver.findElement(by));
    }
}
