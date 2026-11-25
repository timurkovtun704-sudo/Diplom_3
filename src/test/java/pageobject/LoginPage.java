package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    private final By registrationLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By passwordResetLink = By.xpath(".//a[text()='Восстановить пароль']");
    private final By loginTitle = By.xpath(".//h2[text()='Вход']");

    @Step("Заполнение поля Email")
    public void setKeysToEmailInput(String email){
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void setKeysToPasswordInput(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажатие на ссылку Зарегистрироваться")
    public void registrationLinkClick(){
        wait.until(ExpectedConditions.elementToBeClickable(registrationLink)).click();
    }

    @Step("Нажатие на ссылку Восстановить пароль")
    public void passwordResetLinkClick(){
        wait.until(ExpectedConditions.elementToBeClickable(passwordResetLink)).click();
    }

    @Step("Проверить отображение заголовка 'Вход'")
    public boolean isLoginTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle)).isDisplayed();
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
