package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
        //Заголовок "Вход"
        private final By headerLogin = By.xpath(".//h2[text()='Вход']");
        //Поле "Email"
        private final By inputEmail = By.xpath(".//label[text()='Email']/../input");
        //Поле "Пароль"
        private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");
        //Кнопка "Войти"
        private final By loginButton = By.xpath(".//button[text()='Войти']");
        //Кнопка "Восстановить пароль"
        private final By resetPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

        //Кнопка "Личный кабинет"
        private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");

        public String getLoginTextFromHeader() {
            return driver.findElement(headerLogin).getText();
        }

        public void setEmail(String email) {
            driver.findElement(inputEmail).sendKeys(email);
        }

        public void setPassword(String password) {
            driver.findElement(inputPassword).sendKeys(password);
        }

        public void clickLoginButton() {
            driver.findElement(loginButton).click();
        }

        public void clickResetButton() {
        driver.findElement(resetPasswordButton).click();
    }

        public void loginUser(User user){
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            clickLoginButton();
        }
    }

