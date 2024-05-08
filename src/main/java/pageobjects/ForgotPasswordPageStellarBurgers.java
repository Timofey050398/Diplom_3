package pageobjects;
import constants.GeneralMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPageStellarBurgers {
    private WebDriver driver;
    public ForgotPasswordPageStellarBurgers(WebDriver driver){
        this.driver = driver;
    }

    private By resetPasswordHeader = By.xpath("//*[text()='Восстановление пароля']");
    @Step("Дождаться, пока страница загрузится")
    public void waitPageVisibility(){
        GeneralMethods.waitVisibilityByLocator(driver,resetPasswordHeader);
    }
    @Step("Перейти на страницу 'Войти' со страницы /forgot-password")
    public void redirectToLogin(){
        waitPageVisibility();
        GeneralMethods.clickLoginButton(driver);
    }
}
