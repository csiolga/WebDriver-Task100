package driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static final String USERNAME = "olgastarkova";
    private static final String ACCESS_KEY = "06c917f0-296f-4837-9e77-aac8a4289310";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    private static final String DESTINATION_URL = "https://mail.ru/"    ;
    private static Driver instance;
    private Capabilities cap;
    private DesiredCapabilities desiredCaps;
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

    public WebDriver open(String browserName, String browserVersion, String platform) throws MalformedURLException, Exception {
        if (browserName.equalsIgnoreCase("edge")) {
            desiredCaps = DesiredCapabilities.edge();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            desiredCaps = DesiredCapabilities.firefox();

        } else if (browserName.equalsIgnoreCase("chrome")) {
            desiredCaps = DesiredCapabilities.chrome();

        } else {
            throw new Exception("Incorrect browser name");
        }

        desiredCaps.setCapability("version", browserVersion);
        desiredCaps.setCapability("platform", platform);
        driver = new RemoteWebDriver(new URL(URL), desiredCaps);
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
