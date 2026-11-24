package config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "yandex":
                String driverPath = System.getProperty("yandex.driver", "src/test/resources/yandexdriver.exe");
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = new ChromeOptions();
                String binaryPath = System.getProperty("yandex.binary", System.getenv("YANDEX_BROWSER_PATH"));
                if (binaryPath != null && !binaryPath.isEmpty()) {
                    options.setBinary(binaryPath);
                }
                return new ChromeDriver(options);
            case "chrome":
            default:
                return new ChromeDriver();
        }
    }
}
