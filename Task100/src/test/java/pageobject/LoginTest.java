package pageobject;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private static final String USERNAME = "seleniumtests10";
    private static final String PASSWORD = "060788avavav";
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = Driver.getInstance().open();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }

    @Test
    public void login() {
        homePage = loginPage.login(USERNAME,PASSWORD);

        Assert.assertTrue(homePage.isDisplayed(),
                "Expected title is '" + homePage.getTitle() + "' but actual title is '" + driver.getTitle() + "'.");
    }

    @Test
    public void logout() {
        homePage = loginPage.login(USERNAME,PASSWORD);
        loginPage = homePage.logout();

        Assert.assertTrue(loginPage.isDisplayed(),
                "Expected title is '" + loginPage.getTitle() + "' but actual title is '" + driver.getTitle() + "'.");
    }
}