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
import pageobject.ProfilePage;
import java.util.UUID;

public class SelfProfileTest {
    private WebDriver driver;
    private UserClient userClient;
    private String email;
    private String password;
    private String name;
    private String accessToken;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getDriver();
        driver.get(Constants.BASE_URL);
        userClient = new UserClient();
        email = "test" + UUID.randomUUID() + "@yandex.ru";
        password = "timkov";
        name = "TimurTester";
        User user = new User(email, password, name);
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
    }

    @Test
    @DisplayName("Переход в личный кабинет через главную страницу")
    @Description("При нажатии на ссылку Личный кабинет на глвной странице должен произойти переход в личный кабинет. Ожидается переход в личный кабинет, который проверяется отображением текста В этом разделе вы можете изменить свои персональные данные")
    public void moveToSelfProfile(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue("Переход в личный кабинет не произошёл", profilePage.isProfileInfoTextVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу в раздел Конструктор")
    @Description("Проверка перехода из личного кабинета на главную страницу по кнопке Конструктор. Критерий успешности - отображение активной вкладки конструктора бургеров")
    public void moveToConstructorFromProfilePage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.constructButtonClick();
        Assert.assertNotNull(mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу в раздел Конструктор с помощью логотипа")
    @Description("Проверка перехода из личного кабинета на главную страницу по логотипу Stellar Burgers. Критерий успешности - отображение активной вкладки конструктора бургеров")
    public void moveToConstructorFromProfilePageByLogo(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.stellarBurgersLogoClick();
        Assert.assertNotNull(mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("На странице личного кабинета нажимаем кнопку Выход, ожидается выход их аккаунта и переход на страницу входа")
    public void successLogoutOnLogoutButtonClick(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.logoutButtonClick();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue("Выход не произошёл/переход произошёл не на страницу входа", loginPage.isLoginTitleDisplayed());
    }

    @After
    public void tearDown(){
        if(accessToken!=null){
            userClient.deleteUser(accessToken);
        }
        if(driver!=null){
            driver.quit();
        }
    }
}
