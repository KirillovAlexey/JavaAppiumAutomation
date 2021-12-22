package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME = "xpath://*[contains(@text, '{FOLDER_NAME}')]",
            ARTICLE_BY_TITLE = "xpath://*[contains(@text, '{TITLE}')]",
            OPEN_BOOKMARK = "xpath://*[contains(@text,'{{BOOKMARK}}')]/../../..";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameFolder) {
        String folderNameXpath = getFolderXpathByName(nameFolder);
        this.waitForElementAndClick(
                folderNameXpath,
                "Cannot find folder by name" + nameFolder,
                5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(
                articleTitleXpath,
                "Cannot find save article" + articleTitle);
        this.waitForArticleToDisappearByTitle(articleTitle);

    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(
                articleTitleXpath,
                "Saved article still present with title" + articleTitle,
                15);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(
                articleTitleXpath,
                "Cannot find saved article by title '" + articleTitle + "'",
                15);
    }

    public void clickByBookMark(String bookmark) {
        this.waitForArticleToAppearByTitle(bookmark);
        String bookmarkThis = getSavedArticleXpathByTitle(bookmark);
        this.waitForElementAndClick(
                bookmarkThis,
                "Cannot click by bookmark" + bookmark,
                5);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String nameFolder) {
        return FOLDER_BY_NAME.replace("{FOLDER_NAME}", nameFolder);
    }

    private static String getOpenBookmarkXpathByName(String bookmark) {
        return OPEN_BOOKMARK.replace("{BOOKMARK}", bookmark);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE.replace("{TITLE}", articleTitle);
    }
    /*TEMPLATES METHODS*/
}
