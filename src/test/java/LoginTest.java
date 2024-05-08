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
import pageobjects.*;

import static org.junit.Assert.assertTrue;

@DisplayName("Тестирование входа с разных страниц")
public class LoginTest {
    private WebDriver driver;
    Faker faker = new Faker();
    String name = faker.name().name();
    String password = faker.internet().password();
    String email = faker.internet().emailAddress();
    User user = new UserCreate(email,password,name);

    @Before
    public void setUp(){
        driver = Browser.SELECT_DRIVER();
        RestAssured.baseURI = ApiConstants.BASE_URL;
        UserCreate.sendPostUserCreate(user);
    }
    @After
    public void tearDown(){
        driver.quit();
        UserDelete.sendDeleteUser(user);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromConstructorPage(){
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL);
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        mainPage.waitPageVisibility();
        mainPage.clickLogin();
        loginPage.isCurrentPageLogin();
        loginPage.login(email,password);
        mainPage.isCurrentPageMain();
        assertTrue(loginPage.isUserAuthorized());
    }
    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void loginFromHeaderButton(){
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL);
        HeaderStellarBurgers header = new HeaderStellarBurgers(driver);
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        mainPage.waitPageVisibility();
        header.clickProfileButton();
        loginPage.isCurrentPageLogin();
        loginPage.login(email,password);
        mainPage.isCurrentPageMain();
        assertTrue(loginPage.isUserAuthorized());
    }
    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void loginFromRegisterPage(){
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_REGISTER);
        RegisterPageStellarBurgers registerPage = new RegisterPageStellarBurgers(driver);
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        registerPage.redirectToLogin();
        loginPage.isCurrentPageLogin();
        loginPage.login(email,password);
        mainPage.isCurrentPageMain();
        assertTrue(loginPage.isUserAuthorized());
    }
    @Test
    @DisplayName("вход через кнопку на странице «Восстановление пароля»")
    public void loginFromForgotPasswordPage(){
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_FORGOT_PASSWORD);
        ForgotPasswordPageStellarBurgers forgotPasswordPage = new ForgotPasswordPageStellarBurgers(driver);
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        forgotPasswordPage.redirectToLogin();
        loginPage.isCurrentPageLogin();
        loginPage.login(email,password);
        mainPage.isCurrentPageMain();
        assertTrue(loginPage.isUserAuthorized());
    }
    @Test
    @DisplayName("вход через кнопку на странице «/reset-password»")
    public void loginFromResetPasswordPage(){
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_RESET_PASSWORD);
        ResetPasswordPageStellarBurgers resetPasswordPage = new ResetPasswordPageStellarBurgers(driver);
        LoginPageStellarBurger loginPage = new LoginPageStellarBurger(driver);
        ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
        resetPasswordPage.redirectToLogin();
        loginPage.isCurrentPageLogin();
        loginPage.login(email,password);
        mainPage.isCurrentPageMain();
        assertTrue(loginPage.isUserAuthorized());
    }
}
