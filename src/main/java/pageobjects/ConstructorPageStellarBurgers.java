package pageobjects;
import constants.GeneralMethods;
import constants.StellarburgersUrls;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ConstructorPageStellarBurgers {
    private WebDriver driver;
    private By mainPageHeader = By.xpath("//*[text()='Соберите бургер']");
    public ConstructorPageStellarBurgers(WebDriver driver){
        this.driver = driver;
    }
    @Step("Дождаться, пока страница загрузится")
    public void waitPageVisibility(){
        GeneralMethods.waitVisibilityByLocator(driver,mainPageHeader);
    }

    @Step("Вернуть локатор заголовка таба по имени")
    public By headerLocatorByName(String tabName){
        By tabHeaderLocator = By.xpath("//section/div/h2[text()='"+tabName+"']");
        return tabHeaderLocator;
    }

    @Step("Выбрать таб ингредиента по имени")
    public WebElement choseIngredientTab(String tabName){
        WebElement ingredientTab;
        ingredientTab = driver.findElement(By.xpath("//span[text()='"+tabName+"']/parent::div"));
        return ingredientTab;
    }
    @Step("Клик по табу по имени")
    public void tabClick(String name){
        WebElement bunTab = choseIngredientTab(name);
        bunTab.click();
    }
    @Step("Проверить, выбран ли таб по имени")
    public boolean ingredientTabIsChosen(String tabName){
        boolean isChosen;
        WebElement ingredientTab = choseIngredientTab(tabName);
        String currentClass = ingredientTab.getAttribute("class");
        if (currentClass.contains("tab_tab_type_current__2BEPc")) {
            isChosen = true;
        } else {
            isChosen = false;
        }
        return isChosen;
    }

    @Step("Проверить, что хотя бы одну карточку раздела видно на странице")
    public boolean areElementsDisplayed(String tabName) {
        boolean isDisplayed = false;
        List<WebElement> cardsList;
        String ulNumber;
        switch (tabName) {
            case "Булки":
                ulNumber = "1";
                break;
            case "Соусы":
                ulNumber = "2";
                break;
            case "Начинки":
                ulNumber = "3";
                break;
            default:
                throw new IllegalArgumentException("Invalid field to update: " + tabName);
        }
        cardsList = driver.findElements(By.xpath("//div/ul["+ulNumber+"]/a"));
        for (WebElement card : cardsList) {
            if (card.isDisplayed()) {
                isDisplayed = true;
            }
        }
        return isDisplayed;
    }
    @Step("Проверить, что заголовок раздела видно на странице")
    public boolean isTabHeaderMain(String headerName) {
        WebElement header = driver.findElement(headerLocatorByName(headerName));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            Double headerYDouble = (Double) js.executeScript("return window.pageYOffset + arguments[0].getBoundingClientRect().top", header);
            int headerY = Math.abs(headerYDouble.intValue());
            return headerY == 243;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Step("Нажать 'Войти в аккаунт'")
    public void clickLogin(){
        GeneralMethods.clickBiggestAccentButtonByText(driver,"Войти в аккаунт");
    }

    @Step("Проверить, что пользователь находится на главной")
    public void isCurrentPageMain(){
        waitPageVisibility();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL) ||currentUrl.equals(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+"/"));
    }
}
