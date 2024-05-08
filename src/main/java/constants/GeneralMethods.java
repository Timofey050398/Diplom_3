package constants;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralMethods {

    public static void clickButtonByLocator(WebDriver driver, By buttonLocator){
        waitVisibilityByLocator(driver, buttonLocator);
        driver.findElement(buttonLocator).click();
    }
    public static void waitVisibilityByLocator(WebDriver driver, By buttonLocator){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(buttonLocator));
    }
    public static void scrollIntoView(WebDriver driver, By locator){
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    @Step("Найти инпут по плейсхолдеру и заполнить")
    public static void fillInputByPlaceHolder(WebDriver driver, String placeholder, String text){
        By inputLocator = By.xpath("//label[text()='"+placeholder+"']/parent::div/input");
        WebElement input = driver.findElement(inputLocator);
        input.click();
        input.sendKeys(text);
    }
    @Step("Найти кнопку класса button_button__33qZ0 по тексту кнопки и нажать")
    public static void clickBiggestAccentButtonByText(WebDriver driver, String text){
        By biggestAccentButton = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='"+text+"']");
        clickButtonByLocator(driver,biggestAccentButton);
    }
    @Step("Переход на страницу /login по кнопке 'Войти'")
    public static void clickLoginButton(WebDriver driver) {
        By loginButton = By.className("Auth_link__1fOlj");
        scrollIntoView(driver, loginButton);
        clickButtonByLocator(driver, loginButton);
    }
}
