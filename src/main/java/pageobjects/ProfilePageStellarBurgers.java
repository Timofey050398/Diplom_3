package pageobjects;
import constants.GeneralMethods;
import constants.StellarburgersUrls;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class ProfilePageStellarBurgers {
    private WebDriver driver;
    public ProfilePageStellarBurgers(WebDriver driver){
        this.driver = driver;
    }
    private By logoutButton = By.className("Account_button__14Yp3");
    private By profileHeader = By.xpath("//*[text()='Профиль']");
    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton(){
        GeneralMethods.clickButtonByLocator(driver,logoutButton);
    }
    @Step("Проверить, что пользователь находится на /account/profile")
    public void isCurrentPageProfile(){
        waitPageVisibility();
        String expectedUrl = StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_PROFILE;
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals(expectedUrl));
    }
    @Step("Дождаться, пока страница загрузится")
    public void waitPageVisibility(){
        GeneralMethods.waitVisibilityByLocator(driver,profileHeader);
    }
}
