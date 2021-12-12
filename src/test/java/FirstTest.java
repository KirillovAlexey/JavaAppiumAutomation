import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
//import org.openqa.selenium.ScreenOrientation;
//import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

//    @Test
//    public void testAddAndRemoveSiteFromBookmarks() {
//        //добавляем первую статью в закладки
//        addSiteToBookMark(searchWord, choiceSite);
//        mainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'GOT IT')]"),
//                "Cannot find button 'GOT IT'",
//                10);
//        mainPageObject.waitForElementAndClear(
//                By.id("org.wikipedia:id/text_input"),
//                "Cannot find input field",
//                15);
//
//        mainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                nameFolder,
//                "Cannot put text in field",
//                5);
//        mainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'OK')]"),
//                "Cannot find button 'OK'",
//                15);
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'Navigate up')]"),
//                "Cannot find close article button 'X'",
//                15);
//
//
//        searchWord = "Appium";
//        choiceSite = searchWord;
//        //добавляем вторую статью в созданную закладку
//        addSiteToBookMark(searchWord, choiceSite);
//        mainPageObject.waitForElementAndClick(
//                By.xpath("//*[@class='android.widget.LinearLayout']//*[contains(@text, '" + nameFolder + "')]"),
//                "Cannot find button 'OK'",
//                15);
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'Navigate up')]"),
//                "Cannot find close article button 'X'",
//                15);
//        //Переход в меню закладок, выбор ранее созданной.
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@content-desc, 'My lists')]"),
//                "Cannot find button 'My Lists'",
//                15);
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, '" + nameFolder + "')]"),
//                "Cannot find folder",
//                15);
//        //Удаление закладки "Java (programming language)"
//        mainPageObject.swipeElementToLeft(
//                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
//                "Cannot swipe article");
//        mainPageObject.waitForElementNotPresent(
//                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
//                "Cannot delete article",
//                15);
//        mainPageObject.waitForElementPresent(
//                By.xpath("//*[contains(@text,'" + choiceSite + "')]"),
//                "Cannot found ths site" + choiceSite + " ",
//                5);
//        //Переход во вторую сохраненную закладку и провека "Title"
//        mainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text, '" + choiceSite + "')]"),
//                "Cannot tip by " + choiceSite + " ",
//                5
//        );
//        WebElement element = driver.findElement(By.id("org.wikipedia:id/view_page_title_text"));
//        assertTrue("Title is not equals name Site",
//                choiceSite.equals(element.getText()));
//    }

    public void addSiteToBookMark(String searchSiteByName, String openSite) {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                10);

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchSiteByName,
                "error",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView' " +
                        "and contains(@text,'" + openSite + "')]"),
                "errorClick",
                5);
        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "error",
                15
        );
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button 'More Options'",
                10);
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to reading list')]"),
                "Cannot find option to add article to reading list",
                5);
    }

    @Test
    public void testCheckTitle() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                5);

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchWord,
                "error",
                5);

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView' " +
                        "and contains(@text,'" + choiceSite + "')]"),
                "errorClick",
                5);
        mainPageObject.assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
    }

    @Test
    public void testCheckSearchContainer() {
        mainPageObject.assertElementHasText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']" +
                        "//*[@class='android.widget.TextView']"),
                "Search Wikipedia",
                "Ожидаемый текст отсутствует по указанному локатору");
    }


    //Отрефакторить тесты, написанные в предыдущих занятиях (Ex3, Ex5, Ex6)
    // под текущую структуру тестов.
    @Test
    public void testSearchTwice() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Java";
        searchPageObject.typeSearchLine(searchLine);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        list.addAll(driver.findElementsById("org.wikipedia:id/page_list_item_container"));
        mainPageObject.checkCountUrls(list);

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "error",
                5);

        mainPageObject.assertElementHasText(
                By.xpath("//*[@text='Search…']"),
                "Search…",
                "Ожидаемый текст отсутствует по указанному локатору");
    }

    @Test
    public void testCheckCountSearchSite() {
        mainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "errorClick",
                5);

        mainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "error",
                5
        );
        list.clear();
        list.addAll(driver.findElementsById("org.wikipedia:id/page_list_item_title"));
        mainPageObject.checkWordInputAllUrls(list);
    }
}