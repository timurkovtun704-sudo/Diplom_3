package tests;
import api.User;
import api.UserClient;
import com.sun.tools.javac.Main;
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

public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp()
    {
        driver = WebDriverFactory.getDriver();
        driver.get(Constants.BASE_URL);
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    @Description("Проверка переключения на вкладку Булки в конструкторе после предварительного перехода в другой раздел. Критерий успешности - вкладка Булки становится активной")
    public void moveToBunSection(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        Assert.assertEquals("Булки", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    @Description("Проверка переключения на вкладку Соусы в конструкторе. Критерий успешности - вкладка Соусы становится активной")
    public void moveToSauceSection(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        Assert.assertEquals("Соусы", mainPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    @Description("Проверка переключения на вкладку Начинки в конструкторе. Критерий успешности - вкладка Начинки становится активной")
    public void moveToFillingsSection(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        Assert.assertEquals("Начинки", mainPage.getActiveTabText());
    }

    @After
    public void tearDown()
    {
        if(driver!=null){
            driver.quit();
        }
    }
}
