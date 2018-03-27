package driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static final String URL = "https://mail.ru/";
    private Capabilities cap;
    private static Driver instance;
    private WebDriver driver;

    private Driver() {}

    public static Driver getInstance() {
        if (instance == null) {
            synchronized (Driver.class) {
                if (instance == null) {
                    instance = new Driver();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver open() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        return driver;
    }

    public void close() {
        driver.close();
    }

    public String getInfo() {
        cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String platform = cap.getPlatform().toString();
        String browserVersion = cap.getVersion().toString();

        return "Browser: "  + browserName + "\nBrowser version: " + browserVersion + "\nPlatform: " + platform ;
    }
}
