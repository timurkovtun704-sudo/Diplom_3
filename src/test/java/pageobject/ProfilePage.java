package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructButton = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By stellarBurgersLogo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]/a");
    private final By profileInfoText = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    @Step("Нажатие на кнопку Выход")
    public void logoutButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Нажатие на кнопку Конструктор")
    public void constructButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(constructButton)).click();
    }

    @Step("Нажатие на логотипа Stellar Burgers")
    public void stellarBurgersLogoClick(){
        wait.until(ExpectedConditions.elementToBeClickable(stellarBurgersLogo)).click();
    }

    @Step("Проверка видимости текста В этом разделе вы можете изменить свои персональные данные")
    public boolean isProfileInfoTextVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileInfoText)).isDisplayed();
    }

    public ProfilePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
