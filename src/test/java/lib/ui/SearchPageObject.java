package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

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
            ADD_SEARCH_ARTICLE_TO_LIST = "org.wikipedia:id/page_list_item_title";

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


    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String subString) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }
    /*TEMPLATES METHODS*/
}
