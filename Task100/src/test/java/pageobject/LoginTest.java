package pageobject;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testng.MailRuListener;

import java.net.MalformedURLException;

@Listeners(MailRuListener.class)
public class LoginTest {
    private static final String USERNAME = "seleniumtests10";
    private static final String PASSWORD = "060788avavav";
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setup() throws MalformedURLException, Exception {
        driver = Driver.getInstance().open("edge", "16", "Windows 10");
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }

    @Features("Login")
    @Description("Verify the ability to login")
    @TestCaseId("Project-1")
    @Test
    public void login() {
        homePage = loginPage.login(USERNAME,PASSWORD);

        Assert.assertTrue(homePage.isDisplayed(),
                "Expected title is '" + homePage.getTitle() + "' but actual title is '" + driver.getTitle() + "'.");
    }

    @Features("Login")
    @Description("Verify the ability to logout")
    @TestCaseId("Project-1")
    @Test
    public void logout() {
        homePage = loginPage.login(USERNAME,PASSWORD);
        loginPage = homePage.logout();

        Assert.assertTrue(loginPage.isDisplayed(),
                "Expected title is '" + loginPage.getTitle() + "' but actual title is '" + driver.getTitle() + "'.");
    }
}