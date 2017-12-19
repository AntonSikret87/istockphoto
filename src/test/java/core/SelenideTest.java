package core;

import org.testng.annotations.Test;
import pages.HomeIstockphotoPage;
import pages.LoginIstockphotoPage;
import pages.PhotoIstockphotoPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SelenideTest extends TestBase {

    private final static String BASE_URL = "https://www.istockphoto.com";
    private final static String USER_NAME = "vifyelospe@giyam.com";
    private final static String USER_PASSWORD = "Test1234";
    private final static String BOARD_NAME = "TestNameBoard";
    private final static String BOARD_DESCRIPTION = "TestBoardDescription";
    private final static String ALERT_TEXT = "Are you sure you want to delete this Board?";
    private final static String CREATED_BOARD = "Created:";
    private final static String SELECTED_PHOTO_COUNTER = "1 selected";
    private final static String COUNTER_NEAR_MYBOARD = "1";


    @Test
    public void testAddBoardAddPhotoDeletePhotoDeleteBoard() throws Exception {
        //перейти по ссылке https://www.istockphoto.com/
        open(BASE_URL);
        //войти в систему под предварительно созданной учеткой
        page(LoginIstockphotoPage.class).login(USER_NAME, USER_PASSWORD);
        //создать “Board”
        HomeIstockphotoPage homePage = page(HomeIstockphotoPage.class);
        homePage.createBoard(BOARD_NAME, BOARD_DESCRIPTION);
        //проверить что “Board” создалась
        assertTrue(homePage.getTextOfCreatedBoard().contains(CREATED_BOARD));
        assertTrue(homePage.delLinkIsDisplayed());
        //перейти в “Photos”
        homePage.navigateToPhoto();
        //скопировать одну из фотографий на созданную “Board”
        PhotoIstockphotoPage photoPage = page(PhotoIstockphotoPage.class);
        photoPage.copyPhotoToMyBoard();
        homePage.clickSelectAllPhotoButton();
        //проверить что фотография скопировалась.
        assertEquals(photoPage.getCounterAfterSelectPhoto(), SELECTED_PHOTO_COUNTER);
        assertEquals(photoPage.getCounterNearMyBoardIcon(), COUNTER_NEAR_MYBOARD);
        assertTrue(homePage.photoIsAddedAndDisplayed());
        //Удалить фото из “Board” и проверить удаление.
        homePage.deleteSelectedPhoto();
        homePage.assertPhotoIsNotDisplayed();
        //Удалить “Board” и проверить удаление.
        homePage.confirmDelBoard(ALERT_TEXT);
        homePage.assertLinkDeleteThisBoardIsNotDisplayed();
        //Выйти из системы.
        homePage.logout();
        assertTrue(page(LoginIstockphotoPage.class).signInIsDisplayed());
    }
}
