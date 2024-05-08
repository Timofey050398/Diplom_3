package config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
public class Browser {
    //Определитель браузера
    private static String DRIVER_SWITCHER = System.getProperty("browser", "chrome");
    public static final WebDriver RETURN_CHROME_DRIVER() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
    public static final WebDriver RETURN_YANDEX_DRIVER() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver","C:/Users/Timofey/WebDriver/bin/yandexdriver.exe");
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
    public static final WebDriver RETURN_FIREFOX_DRIVER() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }
    public static WebDriver SELECT_DRIVER() {
        WebDriver driver;
        switch (DRIVER_SWITCHER.toLowerCase()) {
            case "chrome":
                driver = Browser.RETURN_CHROME_DRIVER();
                break;
            case "yandex":
                driver = Browser.RETURN_YANDEX_DRIVER();
                break;
            case "firefox":
                driver = Browser.RETURN_FIREFOX_DRIVER();
                break;
            default:
                throw new IllegalArgumentException("Unsupported driver type: " + DRIVER_SWITCHER);
        }
        return driver;
    }

}
