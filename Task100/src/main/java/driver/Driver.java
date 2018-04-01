package driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static final String DESTINATION_URL = "https://mail.ru/";
    private static Driver instance;
    private Capabilities cap =  DesiredCapabilities.chrome();
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

    public WebDriver open() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(DESTINATION_URL);
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
