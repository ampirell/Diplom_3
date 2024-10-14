import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Класс-фабрика, который создаёт объекты WebDriver для работы с различными браузерами (Chrome,
 * YandexBrowser), в зависимости от значения system property.
 *
 * <p>В конфигурации запуска нужно указать ключ -Dbrowser=chrome или -Dbrowser=yandex
 */
public class WebDriverFactory {
    private static final String BROWSER_PROPERTY = "browser";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String WEBDRIVER_CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    /**
     * Создаёт объект WebDriver в зависимости от значения system property "browser".
     *
     * <p>Если system property не указана, то берётся браузер по умолчанию - Chrome.
     *
     * @return
     */
    public static WebDriver createWebDriver() {
        Browser browser = getActiveBrowser();
        WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class);
        switch (browser) {
            case CHROME:
                System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, webDriverConfig.chromeDriverPath());
                return new ChromeDriver();
            case YANDEX:
                System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, webDriverConfig.yandexDriverPath());
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static Browser getActiveBrowser() {
        String browserName = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER);
        return Browser.valueOf(browserName.toUpperCase());
    }
}
