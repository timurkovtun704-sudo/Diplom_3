package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By regButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By incorrectPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");

    @Step("Заполнить поле Имя")
    public void setName(String name){
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(name);
    }

    @Step("Заполнить поле Email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }

    @Step("Заполнить поле Пароль")
    public void setPassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    @Step("Кликнуть по кнопке Зарегистрироваться")
    public void regButtonClick(){
        wait.until(ExpectedConditions.elementToBeClickable(regButton)).click();
    }

    @Step("Кликнуть по ссылке Войти")
    public void loginLinkClick(){
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверка видимости текста Некорректный пароль")
    public boolean isIncorrectPasswordTextVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordText)).isDisplayed();
    }

    @Step("Полный цикл регистрации и клик по кнопке Зарегистрироваться")
    public void registration(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        regButtonClick();
    }

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
