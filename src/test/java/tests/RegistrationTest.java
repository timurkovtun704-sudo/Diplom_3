package tests;
import api.User;
import api.UserClient;
import config.Constants;
import config.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import java.util.UUID;

public class RegistrationTest {
    private WebDriver driver;
    private UserClient userClient;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp()
    {
        driver = WebDriverFactory.getDriver();
        driver.get(Constants.BASE_URL);
        userClient = new UserClient();

        email = "test" + UUID.randomUUID() + "@yandex.ru";
        password = "timkov";
        name = "TimurTester";
    }

    @Test
    @DisplayName("Позитивный сценарий регистрации пользователя")
    @Description("Все поля регистрации заполняются полностью, email правильного формата (example@example.com), пароль содержит 6 символов. Ожидается успешная регистрация и переход на страницу авторизации")
    public void successRegistration(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registrationLinkClick();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(name, email, password);
        Assert.assertTrue("Не отобразился заголовок входа", loginPage.isLoginTitleDisplayed());
    }

    @Test
    @DisplayName("Регистрация с паролем меньше 6ти символов")
    @Description("Все поля регистрации заполнены полностью, email правильного формата (example@example.com), но пароль меньше 6ти символов. Переход на другие страницы не ожидается, ожидается только сообщение, что пароль некорректен.")
    public void registrationWithIncorrectPassword(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registrationLinkClick();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(name, email, "12345");
        Assert.assertTrue("Не отобразилось сообщение об ошибке/произошёл переход на другую страницу", registrationPage.isIncorrectPasswordTextVisible());
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = userClient.loginUser(new User(email, password, name));
        String accessToken = loginResponse.extract().path("accessToken");
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}

