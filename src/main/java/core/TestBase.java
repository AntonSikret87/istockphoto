package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;


public class TestBase {


    @Parameters({ "browser" })
    @BeforeClass
    public void openBrowser(String browser) {

        System.out.println(browser);
        System.out.println(browser);
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                setBrowserChrome();
                setWait();
            } else if (browser.equalsIgnoreCase("Firefox")) {
                setBrowserFF();
                setWait();
            }
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
    }

//    @BeforeClass(alwaysRun = true)
//    public void setUp() throws Exception {
//        setBrowserChrome();
//        setWait();
//    }

    private void setWait() {
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverRunner.getWebDriver().manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        WebDriverRunner.getWebDriver().manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
    }

    public void setBrowserChrome() {
        Configuration.browser = "chrome";
        Configuration.startMaximized=true;
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
    }
    public void setBrowserFF() {
        Configuration.browser = "firefox";
        Configuration.startMaximized=true;
        System.setProperty("webdriver.firefox.driver", ".\\drivers\\gecodriverdriver.exe");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        WebDriverRunner.getWebDriver().close();
    }
}


