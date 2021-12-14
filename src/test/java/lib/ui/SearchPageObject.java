package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL =
                    "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                            "//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            ADD_SEARCH_ARTICLE_TO_LIST = "org.wikipedia:id/page_list_item_title",
            SEARCH_ARTICLE_BY_TITLE_AND_DESCRIPTION =
                    "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                            "//*[@text='{TITLE}']" +
                            "//.." +
                            "//*[@text='{DESCRIPTION}']";

    //*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java'] | //*[@text='Island of Indonesia']
    //*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']//..//*[@text='Island of Indonesia']
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot fond search input after clicking search init element");
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String subString) {
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Cannot find search result with substring" + subString
        );
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForCancelButtonAppear();
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                5);
    }

    public void clickByArticleWithSubString(String subString) {
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementAndClick(
                By.xpath(searchResultXpath),
                "Cannot find and click search result with substring" + subString,
                10
        );
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15);
        return this.getAmountElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15);
    }

    public void assertThereIsNoResultSearch() {
        this.assertElementsNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results");
    }

    public void assertThereIsResultSearch() {
        this.assertElementsNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results");
    }

    public void checkSearchWordInFindsArticles(String searchWord) {
        this.list.clear();
        this.list.addAll(driver.findElementsById(ADD_SEARCH_ARTICLE_TO_LIST));
        this.checkWordInputAllUrls(list, searchWord);
    }
    public void checkFindsArticleInSearchingList(){
        for (WebElement article: this.list) {
            this.waitForElementPresent((By.id(article.getAttribute("resource-id"))) ,"Cannot find this article" + article);
        }
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchXpath = getResultSearchElementForTitleAndDescriptions(
                title, description);
        this.waitForElementPresent(
                By.xpath(searchXpath),
                "Cannot find article for this " + title + " or this " + description,
                10
        );
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String subString) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    private static String getResultSearchElementForTitleAndDescriptions(String title, String description) {
        String temp = SEARCH_ARTICLE_BY_TITLE_AND_DESCRIPTION.replace("{TITLE}", title);
        temp = temp.replace("{DESCRIPTION}", description);
        return temp;
    }
    /*TEMPLATES METHODS*/
}
