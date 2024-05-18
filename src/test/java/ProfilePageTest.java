import apiclasses.request.User;
import apiclasses.request.UserCreate;
import apiclasses.request.UserDelete;
import com.github.javafaker.Faker;
import constants.ApiConstants;
import config.Browser;
import constants.StellarburgersUrls;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.ConstructorPageStellarBurgers;
import pageobjects.HeaderStellarBurgers;
import pageobjects.LoginPageStellarBurger;
import pageobjects.ProfilePageStellarBurgers;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@DisplayName("Тестирование функциональности личного кабинета")
public class ProfilePageTest {
    private WebDriver driver;
    Faker faker = new Faker();
    String name = faker.name().name();
    String password = faker.internet().password();
    String email = faker.internet().emailAddress();
    User user = new UserCreate(email,password,name);

    @Before
    public void setUp(){
        driver = Browser.selectDriver();
        RestAssured.baseURI = ApiConstants.BASE_URL;
        UserCreate.sendPostUserCreate(user);
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_LOGIN);
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        loginPage.login(email,password);
        mainPage.waitPageVisibility();
        assertTrue(loginPage.isUserAuthorized());
    }
    @After
    public void tearDown(){
        driver.quit();
        UserDelete.sendDeleteUser(user);
    }
    @Test
    @DisplayName("Переход в личный кабинет по кнопке в шапке")
    public void clickProfileButton(){
        ProfilePageStellarBurgers profilePage = new ProfilePageStellarBurgers(driver);
        HeaderStellarBurgers header = new HeaderStellarBurgers(driver);
        header.clickProfileButton();
        profilePage.isCurrentPageProfile();
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void clickConstructorButtonFromProfile(){
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        ProfilePageStellarBurgers profilePage = new ProfilePageStellarBurgers(driver);
        HeaderStellarBurgers header = new HeaderStellarBurgers(driver);
        header.clickProfileButton();
        profilePage.isCurrentPageProfile();
        header.clickConstructorButton();
        mainPage.isCurrentPageMain();
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void clickLogoButtonFromProfile(){
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        ProfilePageStellarBurgers profilePage = new ProfilePageStellarBurgers(driver);
        HeaderStellarBurgers header = new HeaderStellarBurgers(driver);
        header.clickProfileButton();
        profilePage.isCurrentPageProfile();
        header.clickHeaderLogoButton();
        mainPage.isCurrentPageMain();
    }
    @Test
    @DisplayName("выход по кнопке «Выйти» в личном кабинете")
    public void clickLogoutButton(){
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ProfilePageStellarBurgers profilePage = new ProfilePageStellarBurgers(driver);
        HeaderStellarBurgers header = new HeaderStellarBurgers(driver);
        header.clickProfileButton();
        profilePage.isCurrentPageProfile();
        profilePage.clickLogoutButton();
        loginPage.isCurrentPageLogin();
        assertFalse(loginPage.isUserAuthorized());
    }
}
