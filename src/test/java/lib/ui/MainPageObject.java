package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(By by, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe) {
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

    public void swipeElementToLeft(By by, String message) {
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

    public void swipeUpQuick() {
        swipeUp(300);
    }

    public void swipeUpToFindElement(By by, String message, int max_swipes) {
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

    public WebElement assertElementHasText(By by, String expected, String message) {
        WebElement webElement = waitForElementPresent(
                by,
                "error",
                15
        );
        Assert.assertEquals(message, expected, webElement.getText());
        return webElement;
    }

    public void checkCountUrls(List list) {
        Assert.assertTrue("Количество статей менее либо равно '1'", list.size() > 1);
    }

    public void checkWordInputAllUrls(List<WebElement> list) {
        for (WebElement element : list) {
            Assert.assertTrue("В указанной ссылке отсутствует искомое слово в названии",
                    element.getText().contains("Java"));
        }
    }

    public int getAmountElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementsNotPresent(By by, String message) {
        int amount_of_elements = getAmountElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + " supposed to be not present";
            throw new AssertionError(default_message + " " + message);
        }
    }

    public void assertElementPresent(By by) {
        //waitForElementPresent(by,"Title is not download");
        Assert.assertNotNull("Title is not found", driver.findElement(by).getText());
    }
}
