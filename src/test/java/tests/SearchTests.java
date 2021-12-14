package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        searchPageObject.typeSearchLine(searchLine);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "zxcasdasdqwe";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultSearch();
    }

    @Test
    public void testSearchArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonDisappear();
    }

    //EX3
    @Test
    public void testCancelSearchEx3() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Java";
        searchPageObject.typeSearchLine(searchLine);
        int countArticles = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "Articles less that 1",
                countArticles > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.typeSearchLine("Search…");
    }
}
