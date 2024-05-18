package config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
public class Browser {
    //Определитель браузера
    private static final String DRIVER_SWITCHER = System.getProperty("browser", "chrome");
    public static WebDriver returnChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
    public static WebDriver returnYandexDriver() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver","C:/Users/Timofey/WebDriver/bin/yandexdriver.exe");
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
    public static WebDriver returnFireFoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }
    public static WebDriver selectDriver() {
        WebDriver driver;
        switch (DRIVER_SWITCHER.toLowerCase()) {
            case "chrome":
                driver = Browser.returnChromeDriver();
                break;
            case "yandex":
                driver = Browser.returnYandexDriver();
                break;
            case "firefox":
                driver = Browser.returnFireFoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported driver type: " + DRIVER_SWITCHER);
        }
        return driver;
    }

}
