package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    //Логотип
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    //Раздел "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Заголовок "Соберите бургер"
    private final By createBurger = By.xpath(".//h1[text()='Соберите бургер']");
    //Кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
     //Кнопка "Личный кабинет"
    private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getCreateBurgerTextFromHeader() {
        return driver.findElement(createBurger).getText();
    }

    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

}