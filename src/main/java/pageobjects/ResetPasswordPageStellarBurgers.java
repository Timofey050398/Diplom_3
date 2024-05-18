package pageobjects;

import constants.GeneralMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResetPasswordPageStellarBurgers {
    //TO DO
    private WebDriver driver;
    public ResetPasswordPageStellarBurgers(WebDriver driver){
        this.driver = driver;
    }
    private By resetPasswordHeader = By.xpath("//*[text()='Восстановление пароля']");
    @Step("Дождаться, пока страница загрузится")
    public void waitPageVisibility(){
        GeneralMethods.waitVisibilityByLocator(driver,resetPasswordHeader);
    }
    @Step("Перейти на страницу 'Войти' со страницы /forgot-password")
    public void redirectToLogin(){
        new WebDriverWait(driver, Duration.ofSeconds(2));
        waitPageVisibility();
        GeneralMethods.clickLoginButton(driver);
    }
}
