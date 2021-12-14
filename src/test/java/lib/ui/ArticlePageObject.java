package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text = 'View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc = 'More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[contains(@text, 'Add to reading list')]",
            ADD_TO_MY_LIST_OVERLAY = "//*[contains(@text, 'GOT IT')]",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[contains(@text, 'OK')]",
            CLOSE_ARTICLE_BUTTON = "//*[contains(@content-desc, 'Navigate up')]",
            ADD_TO_MY_CREATED_LIST_PREVIOUS = "//*[contains(@text,'{nameFolder}')]/../..",
            ADD_NEW_FOLDER_NAME = "org.wikipedia:id/create_button";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return waitForElementPresent(
                By.id(TITLE),
                "Cannot find article title on page",
                15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public String getArticleTitleWithoutTimeout() {
        WebElement titleElement = this.assertElementPresentWithoutTimeout(By.id(TITLE));
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addFirstArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button 'More Options'",
                5);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button 'GOT IT'",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input field",
                15);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                nameOfFolder,
                "Cannot put text in field",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find button 'OK'",
                5);
    }

    public void addArticleToCreatedFolder(String nameFolder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button 'More Options'",
                5);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5);

        String folderName = getResultSearchFolder(nameFolder);
        this.waitForElementAndClick(
                By.xpath(folderName),
                "Cannot find folder name'" + nameFolder,
                5);
    }

    public void addArticleToNewCreateFolder() {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button 'More Options'",
                5);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5);

    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find close article button 'X'",
                5);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchFolder(String nameFolder) {
        return ADD_TO_MY_CREATED_LIST_PREVIOUS.replace("{nameFolder}", nameFolder);
    }
    /*TEMPLATES METHODS*/
}
