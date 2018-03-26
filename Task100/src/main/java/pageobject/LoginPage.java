package pageobject;

import org.openqa.selenium.By;
import waits.Wait;

public class LoginPage extends Page {
    private static final String TITLE = "Mail.Ru: почта, поиск в интернете, новости, игры";
    private static final By USERNAME_INPUT = By.id("mailbox:login");
    private static final By PASSWORD_INPUT = By.id("mailbox:password");
    private static final By LOGIN_BUTTON = By.xpath("//label[@id='mailbox:submit']/input");

    public LoginPage() {
        super(TITLE);
    }

    public String getTitle() {
        return TITLE;
    }

    public HomePage login(String username, String password ) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        Wait.waitForTitleChange(TITLE);

        return new HomePage();
    }
}
