package pageobjects;
import constants.GeneralMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class HeaderStellarBurgers {
    private WebDriver driver;
    public HeaderStellarBurgers(WebDriver driver){
        this.driver = driver;
    }
    private By constructorButton = By.xpath("//p[contains(text(),'Конструктор')]/parent::a");
    private By headerLogoButton = By.xpath("//header/nav/div");
    private By profileButton = By.xpath("//a[@href='/account']");
    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton(){
        GeneralMethods.clickButtonByLocator(driver,constructorButton);
    }
    @Step("Нажать кнопку 'Stellar Burger'")
    public void clickHeaderLogoButton(){
        GeneralMethods.clickButtonByLocator(driver,headerLogoButton);
    }
    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickProfileButton(){
        GeneralMethods.clickButtonByLocator(driver,profileButton);
    }
}
