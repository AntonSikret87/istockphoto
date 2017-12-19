package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class PhotoIstockphotoPage {

    @FindBy(xpath = "//img[@alt = 'Couple photos']/parent::figure")
    private SelenideElement firstPhotoAlbum;
    @FindBy(xpath = "//span[@class='checkbox']/child::label")
    private List<SelenideElement> listOfCheckboxesCouplePhotos;
    @FindBy(xpath = "//button[contains(@class, 'actions')]")
    private SelenideElement chooseAnOptionBtn;
    @FindBy(xpath = "//div[@class='option-container' and contains(.,'Copy to Board')]")
    private SelenideElement copyToBoardBtn;
    @FindBy(xpath = "//span[@class='more-actions ng-scope']/descendant::li[1]")
    private SelenideElement myBoardToCopyBtn;
    @FindBy(xpath = "//div[@class='board-item' and @drag='asset']")
    private List<SelenideElement> listOfAddedPhotos;
    @FindBy(xpath = "//div[text()=' 1 selected']")
    private SelenideElement counterSelectedPhotosField;
    @FindBy(xpath = ".//*[@id='open_board']/descendant::span[@class='count']")
    private SelenideElement counterElementNearMyBoard;
    @FindBy(xpath = "//span[@class='remove']")
    private SelenideElement xBtntOnPhoto;
    @FindBy(css = ".select-all-assets.ng-scope>a")//a[text()='Select all']
    private SelenideElement selectAllPhotosBtn;


    public PhotoIstockphotoPage copyPhotoToMyBoard() throws InterruptedException, AWTException {
        firstPhotoAlbum.click(); //.waitUntil(visible,3000)
        Random random = new Random();
        int index = random.nextInt(listOfCheckboxesCouplePhotos.size());
        listOfCheckboxesCouplePhotos.get(index).click();
        chooseAnOptionBtn.click();
        copyToBoardBtn.click();
        myBoardToCopyBtn.shouldBe(visible).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        return page(PhotoIstockphotoPage.class);
    }

    public String getCounterAfterSelectPhoto() {
        counterSelectedPhotosField.waitUntil(visible,6000);
        String counterSelectedPhotoStr = counterSelectedPhotosField.getText();
        // System.out.println("String count selected lanbel text: " + counterSelectedPhotoStr);
        return counterSelectedPhotoStr;
    }

    public String getCounterNearMyBoardIcon() {
        counterElementNearMyBoard.waitUntil(visible, 4000);
        String counterNearMyBoardStr = counterElementNearMyBoard.getText();
        return counterNearMyBoardStr;
    }

    public boolean photoIsDisplayed() {
        return xBtntOnPhoto.isDisplayed();

    }

}
