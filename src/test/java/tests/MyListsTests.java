package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveArticleToMyList() {
        if(this.platform.isIOS()){
            return;
        }
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        String nameFolder = "Learning Programming";
        articlePageObject.addFirstArticleToMyList(nameFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameFolder);
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    //refactoringEX5
    @Test
    public void testAddArticleAndRemoveAnotherArticleFromBookmarksEx5() {
        if(this.platform.isIOS()){
            return;
        }
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        String nameFolder = "Learning Programming";
        articlePageObject.addFirstArticleToMyList(nameFolder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubString("Appium");

        String article_title_second = articlePageObject.getArticleTitle();
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToCreatedFolder(nameFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameFolder);
        myListsPageObject.swipeByArticleToDelete(article_title);
        myListsPageObject.clickByBookMark(article_title_second);

        article_title = articlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title",
                "Appium",
                article_title);
    }
}
