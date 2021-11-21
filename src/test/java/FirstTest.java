import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

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
    public void ex2() {
        assertElementHasText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']" +
                        "//*[@class='android.widget.TextView']"),
                "Search Wikipedia",
                "Ожидаемый текст отсутствует по указанному локатору");
    }

    @Test
    public void firstTests() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "error",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "error",
                15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "error",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "error",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "error",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "error",
                5);
        waitElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "error",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "error",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "errorClick",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "error",
                15
        );
        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "error",
                15
        );
    }

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

    private WebElement assertElementHasText(By by, String expected, String message) {
        WebElement webElement = waitForElementPresent(
                by,
                "error",
                15
        );
        Assert.assertEquals(message, expected, webElement.getText());
        return webElement;
    }
}
