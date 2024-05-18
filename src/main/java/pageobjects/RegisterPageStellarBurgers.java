package pageobjects;
import constants.GeneralMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class RegisterPageStellarBurgers {
    private WebDriver driver;
    public RegisterPageStellarBurgers(WebDriver driver){
        this.driver = driver;
    }
    private By RegisterHeader = By.xpath("//*[text()='Регистрация']");
    private By incorrectPasswordText = By.xpath("//*[text()='Некорректный пароль']");
    private By passwordInputLocator = By.xpath("//fieldset/div/div"+"[.//label[text()='Пароль']]");
    @Step("Дождаться, пока страница загрузится")
    public void waitPageVisibility(){
        GeneralMethods.waitVisibilityByLocator(driver,RegisterHeader);
    }
    @Step("Заполнить поле 'Имя'")
    public void setName(String name){
        GeneralMethods.fillInputByPlaceHolder(driver,"Имя",name);
    }
    @Step("Заполнить поле 'Email'")
    public void setEmail(String email){
        GeneralMethods.fillInputByPlaceHolder(driver,"Email",email);
    }
    @Step("Заполнить поле 'Пароль'")
    public void setPassword(String password){
        GeneralMethods.fillInputByPlaceHolder(driver,"Пароль",password);
    }
    @Step("Нажать 'Зарегистрироваться'")
    public void clickRegister(){
        GeneralMethods.clickBiggestAccentButtonByText(driver,"Зарегистрироваться");
    }
    @Step("Заполнить форму и зарегестрироваться")
    public void register(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegister();
    }
    @Step("Перейти на страницу 'Войти' со страницы /register")
    public void redirectToLogin(){
        waitPageVisibility();
        GeneralMethods.clickLoginButton(driver);
    }
    @Step("Проверить, отображается ли красная рамка у инпута пароля")
    public boolean isCurrentPasswordFieldStatusError(){
        boolean isCurrentStatusError;
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        String currentClass = passwordInput.getAttribute("class");
        if (currentClass.contains("input_status_error")) {
            isCurrentStatusError = true;
        } else {
            isCurrentStatusError = false;
        }
        return isCurrentStatusError;
    }
    @Step("Проверить, отображается ли текст 'Некорректный пароль'")
    public boolean isTextVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordText));
        boolean isTextVisible = driver.findElement(incorrectPasswordText).isDisplayed();
        return isTextVisible;
    }
    @Step("Проверить, что состояние инпута пароля сменилось на некорректный")
    public void isPasswordIncorrect(){
        assertTrue(isCurrentPasswordFieldStatusError() && isTextVisible());
    }
}
