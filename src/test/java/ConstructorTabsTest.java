import config.Browser;
import constants.StellarburgersUrls;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobjects.ConstructorPageStellarBurgers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertTrue;

@DisplayName("Тестирование табов страницы конструктора")
@RunWith(Parameterized.class)
public class ConstructorTabsTest {
    private WebDriver driver;
    private final int tabNumber;
    private final List<String> tabNames = new ArrayList<>(Arrays.asList("Булки", "Соусы", "Начинки"));

    public ConstructorTabsTest(int tabNumber){
        this.tabNumber = tabNumber;
    }
    @Parameterized.Parameters
    public static Object[][] getTabs() {
        return new Object[][] {
                {0},
                {1},
                {2}
        };
    }
    @Test
    @DisplayName("Проверить переходы по табам")
    public void moveTab(){
        driver = Browser.selectDriver();
        try {
            String tab = tabNames.get(tabNumber);
            driver.get(StellarburgersUrls.STELLARBURGERS_MAIN_PAGE_URL);
            ConstructorPageStellarBurgers mainPage = new ConstructorPageStellarBurgers(driver);
            mainPage.waitPageVisibility();
            if (tab.equals(tabNames.get(0))) {
                mainPage.tabClick(tabNames.get(2));
            }
            mainPage.tabClick(tab);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            assertTrue(mainPage.ingredientTabIsChosen(tab));
            assertTrue(mainPage.isTabHeaderMain(tab));
            assertTrue(mainPage.areElementsDisplayed(tab));
        }finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
