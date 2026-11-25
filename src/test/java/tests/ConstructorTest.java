package tests;
import config.Constants;
import config.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

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
    @Description("Проверка переключения на вкладку Булки в конструкторе после предварительного перехода в раздел Соусы. Критерий успешности - вкладка Булки становится активной")
    public void moveToBunSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        mainPage.isSaucesTabActive(); // Ждём завершения перехода на «Соусы»
        mainPage.clickBunsTab();
        Assert.assertTrue("Вкладка «Булки» не стала активной", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    @Description("Проверка переключения на вкладку Соусы в конструкторе. Критерий успешности - вкладка Соусы становится активной")
    public void moveToSauceSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        Assert.assertTrue("Вкладка «Соусы» не стала активной", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    @Description("Проверка переключения на вкладку Начинки в конструкторе. Критерий успешности - вкладка Начинки становится активной")
    public void moveToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        Assert.assertTrue("Вкладка «Начинки» не стала активной", mainPage.isFillingsTabActive());
    }

    @After
    public void tearDown()
    {
        if(driver!=null){
            driver.quit();
        }
    }
}
