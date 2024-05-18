import apiclasses.request.User;
import apiclasses.request.UserDelete;
import apiclasses.request.UserLogin;
import com.github.javafaker.Faker;
import constants.ApiConstants;
import config.Browser;
import constants.StellarburgersUrls;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.RegisterPageStellarBurgers;
import java.util.Random;

@DisplayName("Тестирование регистрации")
public class RegisterTest {
    private WebDriver driver;
    Faker faker = new Faker();
    String name = faker.name().name();
    String password = faker.internet().password();
    String email = faker.internet().emailAddress();

    @Before
    public void setUp(){
        driver = Browser.selectDriver();
        driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_REGISTER);
        RestAssured.baseURI = ApiConstants.BASE_URL;
    }
    @After
    public void tearDown(){
        driver.quit();
    }
    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successRegisterTest(){
        User user = new UserLogin(email,password);
        RegisterPageStellarBurgers regPage = new RegisterPageStellarBurgers(driver);
        regPage.waitPageVisibility();
        regPage.register(name, email, password);
        Response response = UserLogin.sendPostUserLogin(user);
        UserLogin.verifySuccessResponseBodyIsCorrect(response,email,name);
        UserDelete.sendDeleteUser(user);
    }
    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void incorrectRegisterTest(){
        Random random = new Random();
        String incorrectPassword = String.valueOf(random.nextInt(100000));
        RegisterPageStellarBurgers regPage = new RegisterPageStellarBurgers(driver);
        regPage.waitPageVisibility();
        regPage.register(name, email, incorrectPassword);
        regPage.isPasswordIncorrect();
    }
}
