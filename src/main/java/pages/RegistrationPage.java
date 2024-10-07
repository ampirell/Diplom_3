package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import user.User;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private final String nameInput = ".//label[text()='Имя']/../input";
    private final String emailInput = ".//label[text()='Email']/../input";
    private final String passwordInput = ".//label[text()='Пароль']/../input";
    private final String registerButton = ".//a[text()='Зарегистрироваться']";
    private final String registerSecondButton = ".//button[text()='Зарегистрироваться']";
    private final String textInvalidPassword = ".//p[text()='Некорректный пароль']";
    private final String loginButton = "Auth_link__1fOlj";

    public void setName(String name) {
        driver.findElement(By.xpath(nameInput)).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(By.xpath(emailInput)).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(By.xpath(passwordInput)).sendKeys(password);
    }

    public void clickRegisterButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(registerSecondButton)));
        driver.findElement(By.xpath(registerSecondButton)).click();
    }
    public void clickRegisterButtonToRegPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(registerButton)));
        driver.findElement(By.xpath(registerButton)).click();
    }

    public String getInvalidPasswordText() {
        return driver.findElement(By.xpath(textInvalidPassword)).getText();
    }

    public void clickLoginButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.className(loginButton)));
        driver.findElement(By.className(loginButton)).click();
    }

    public void registerUser(User user){
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickRegisterButton();
    }

}
