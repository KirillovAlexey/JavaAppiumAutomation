package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testSwipeArticle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubString("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testCheckWordIntoEachFindArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchWord = "Java";
        searchPageObject.typeSearchLine(searchWord);
        searchPageObject.checkSearchWordInFindsArticles(searchWord);
    }

    //refactoringEX6
    @Test
    public void testCheckTitleEx6() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        String searchNameArticle = "Java (programming language)";
        SearchPageObject searchPageObject1 = new SearchPageObject(driver);
        searchPageObject.clickByArticleWithSubString(searchNameArticle);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.getArticleTitleWithoutTimeout();
    }
}
