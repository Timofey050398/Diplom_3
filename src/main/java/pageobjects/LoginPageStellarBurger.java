package pageobjects;
import constants.GeneralMethods;
import constants.StellarburgersUrls;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class LoginPageStellarBurger {
   private WebDriver driver;
   public LoginPageStellarBurger(WebDriver driver){
      this.driver = driver;
   }

   private By loginHeader = By.xpath("//*[text()='Вход']");
   @Step("Дождаться, пока страница загрузится")
   public void waitPageVisibility(){
      GeneralMethods.waitVisibilityByLocator(driver,loginHeader);
   }

   @Step("Заполнить поле 'Email'")
   public void setEmail(String email){
      GeneralMethods.fillInputByPlaceHolder(driver,"Email",email);
   }
   @Step("Заполнить поле 'Пароль'")
   public void setPassword(String password){
      GeneralMethods.fillInputByPlaceHolder(driver,"Пароль",password);
   }
   @Step("Нажать 'Войти'")
   public void clickLogin(){
      GeneralMethods.clickBiggestAccentButtonByText(driver,"Войти");
   }
   @Step("Заполнить форму логина и войти")
   public void login(String email, String password){
      waitPageVisibility();
      setEmail(email);
      setPassword(password);
      clickLogin();
   }
   @Step("Получить accessToken из Local Storage")
   public String returnAccessToken(){
      JavascriptExecutor js = (JavascriptExecutor) driver;
      String accessToken = (String) js.executeScript("return localStorage.getItem('accessToken');");
      return accessToken;
   }

   @Step("Проверить, авторизован ли пользователь")
   public boolean isUserAuthorized(){
      String accessToken = returnAccessToken();
      boolean isAuthorized;
      if(accessToken != null){
         isAuthorized = true;
      }else{
         isAuthorized = false;
      }
      return isAuthorized;
   }
   @Step("Проверить, что пользователь находится на /login")
   public void isCurrentPageLogin(){
      waitPageVisibility();
      String expectedUrl = StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL+StellarburgersUrls.STELLARBURGERS_LOGIN;
      String currentUrl = driver.getCurrentUrl();
      assertTrue(currentUrl.equals(expectedUrl));
   }
}
