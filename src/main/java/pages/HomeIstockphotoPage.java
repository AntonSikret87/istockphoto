package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.page;


public class HomeIstockphotoPage {

    @FindBy(xpath = "//span[@class='icon']/following-sibling::span[starts-with(text(),'Your')]")
    private SelenideElement myBoard;
    @FindBy(xpath = "//input[@name = 'boardname']")
    private SelenideElement boardNameField;
    @FindBy(xpath = "//textarea[@type='text']")
    private SelenideElement boardNameDescriptionField;
    @FindBy(xpath = "//a[@class='button' and text()='Create']")
    private SelenideElement createBtn;
    @FindBy(xpath = "//ul[@class='nav-root']/descendant::a[text()='Photos']")
    private SelenideElement photosBtn;
    @FindBy(xpath = "//a[@class = 'delete-board ng-scope']")
    private SelenideElement deleteBoardLink;
    @FindBy(xpath = "//li[contains(@class,'wide-header right-off')]")
    private SelenideElement accountBtn;
    @FindBy(id = "hypSignOut")
    private SelenideElement signOutBtn;
    @FindBy(id = "//h4[@class='message']")
    private SelenideElement messageOnEmptyBoard;
    @FindBy(xpath = "//span[@class='ng-binding' and contains(.,'Created')]")
    private SelenideElement createdDateField;
    @FindBy(css = ".select-all-assets.ng-scope>a")//a[text()='Select all']
    private SelenideElement selectAllPhotosBtn;
    @FindBy(xpath = "//button[@class='outline clear-all']")
    private SelenideElement deletePhotoBtn;
    @FindBy(xpath = "//div[@class='board-item' and @drag='asset']")
    private List<SelenideElement> photoElementOnMyBoardList;
    @FindBy(xpath = "//div[@ng-class='{selected: isAssetSelected(asset)}']")
    private SelenideElement photoElementOnMyBoard;

    public HomeIstockphotoPage createBoard(String boardName, String boardDescription) {
        myBoard.shouldBe(visible).click();
        boardNameField.waitUntil(visible, 5000).sendKeys(boardName);
        boardNameDescriptionField.sendKeys(boardDescription);
        createBtn.click();
        return page(HomeIstockphotoPage.class);
    }

    public void confirmDelBoard(String alertText) {
        deleteBoardLink.click();
        confirm(alertText);
    }

    public String getMessageIsDisplayedOnEmptyBoard() {
        messageOnEmptyBoard.waitUntil(visible, 5000);
        String messageOnBoardStr = messageOnEmptyBoard.getText();
        return messageOnBoardStr;
    }

    public boolean delLinkIsDisplayed() {
        return deleteBoardLink.waitUntil(visible, 4000).isDisplayed();
    }

    public String getTextOfCreatedBoard() {
        createdDateField.waitUntil(visible, 4000);
        String textCreated = createdDateField.getText();
       // System.out.println("TEXT CREATED: " + createdDateField.getText());
        return textCreated;
    }

    public void navigateToPhoto() {
        Selenide.Wait().until(ExpectedConditions.elementToBeClickable(photosBtn)).click();
    }

    public void logout() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        accountBtn.click();
        signOutBtn.click();
    }

    public void clickSelectAllPhotoButton() {
        selectAllPhotosBtn.click();
      //  Selenide.Wait().until(ExpectedConditions.visibilityOf(selectAllPhotosBtn)).click();

    }

    public void deleteSelectedPhoto() throws AWTException {
        deletePhotoBtn.shouldBe(visible).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
    }

//    public boolean getPhotoRemovedList() {
//        System.out.println("ARR EL is empty: " + photoElementOnMyBoardList.isEmpty());
//        System.out.println("ARR EL size: " + photoElementOnMyBoardList.size());
//        return photoElementOnMyBoardList.isEmpty();
//    }
//
//    public boolean getPhotoListIsEmpty() {
//        Selenide.Wait().until(ExpectedConditions.invisibilityOf(deletePhotoBtn));
//        Selenide.Wait().withTimeout(20, TimeUnit.SECONDS);
//        System.out.println("ARR EL is empty: " + photoElementOnMyBoardList.isEmpty());
//        System.out.println("ARR EL size: " + photoElementOnMyBoardList.size());
//        return photoElementOnMyBoardList.isEmpty();
//    }

    public boolean photoIsAddedAndDisplayed() {
        return photoElementOnMyBoard.isDisplayed();
    }

    public void assertPhotoIsNotDisplayed() {
        photoElementOnMyBoard.shouldNot(visible);
    }

    public void assertLinkDeleteThisBoardIsNotDisplayed() {
        deleteBoardLink.shouldNot(visible);
    }
}


