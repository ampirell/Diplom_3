package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{

   public ProfilePage(WebDriver driver) {
       super(driver);
   }

    // Вкладка "Профиль"
    private static final String profileButton = ".//a[text()='Профиль']";
    // Вкладка "Выход"
    private final String exitButtun = ".//button[text()='Выход']";

    public static boolean profileTabIsEnabled() {
        return driver.findElement(By.xpath(profileButton)).isEnabled();
    }

    public void clickExitButton() {
        driver.findElement(By.xpath(exitButtun)).click();
    }

}