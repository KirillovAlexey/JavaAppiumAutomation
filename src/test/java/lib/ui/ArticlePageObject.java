package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc = 'More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text, 'Add to reading list')]",
            ADD_TO_MY_LIST_OVERLAY = "xpath://*[contains(@text, 'GOT IT')]",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[contains(@text, 'OK')]",
            CLOSE_ARTICLE_BUTTON = "xpath://*[contains(@content-desc, 'Navigate up')]",
            ADD_TO_MY_CREATED_LIST_PREVIOUS = "xpath://*[contains(@text,'{nameFolder}')]/../..",
            ADD_NEW_FOLDER_NAME = "id:org.wikipedia:id/create_button";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return waitForElementPresent(
                TITLE,
                "Cannot find article title on page",
                15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public String getArticleTitleWithoutTimeout() {
        WebElement titleElement = this.assertElementPresentWithoutTimeout(TITLE);
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
    }

    public void addFirstArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button 'More Options'",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button 'GOT IT'",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input field",
                15);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                nameOfFolder,
                "Cannot put text in field",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find button 'OK'",
                5);
    }

    public void addArticleToCreatedFolder(String nameFolder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button 'More Options'",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        String folderName = getResultSearchFolder(nameFolder);
        this.waitForElementAndClick(
                folderName,
                "Cannot find folder name'" + nameFolder,
                5);
    }

    public void addArticleToNewCreateFolder() {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button 'More Options'",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);

    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find close article button 'X'",
                5);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchFolder(String nameFolder) {
        return ADD_TO_MY_CREATED_LIST_PREVIOUS.replace("{nameFolder}", nameFolder);
    }
    /*TEMPLATES METHODS*/
}
