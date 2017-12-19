package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;


public class LoginIstockphotoPage extends HomeIstockphotoPage {

    @FindBy(linkText = "Sign in")
    private SelenideElement signInBtn;
    @FindBy(id = "new_session_username")
    private SelenideElement userNameField;
    @FindBy(id = "new_session_password")
    private SelenideElement passwordField;
    @FindBy(id = "sign_in")
    private SelenideElement loginBtn;


    public void login(String name, String password){
        signInBtn.click();
        userNameField.val(name);
        passwordField.val(password);
        loginBtn.pressEnter();
    }

    public boolean signInIsDisplayed() {
        signInBtn.waitUntil(visible, 5000);
        return signInBtn.isDisplayed();
    }
}




