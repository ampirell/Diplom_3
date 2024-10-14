package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ResetPassPage extends BasePage {

    //Кнопка "Войти" под формой восстановления пароля
    private final String loginButton = "Auth_link__1fOlj";

    public ResetPassPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButtonUnderResetting() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.className(loginButton)));
        driver.findElement(By.className(loginButton)).click();
    }


}