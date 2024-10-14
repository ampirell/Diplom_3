import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import user.Generator;
import user.User;
import user.UserClient;

import java.util.concurrent.TimeUnit;

public class TestLogin {
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    User user;
    String accessToken;

    @Before
    public void setup() {
        RestAssured.baseURI = Links.MAIN_PAGE;
        driver = WebDriverFactory.createWebDriver();
        user = Generator.generateUser();
        driver.get(PAGE_URL);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //регистрация пользователя
        ValidatableResponse createResponse = UserClient.create(user);
    }
    @After
    public void tearDown(){
        ValidatableResponse loginResponse = UserClient.login(user);
        String accessTokenFromResponse = loginResponse.extract().path("accessToken");
        if (accessToken != null) {
            accessToken= accessTokenFromResponse.substring(7);
            UserClient.delete(accessToken);
        }
        driver.quit();
    }
    @Test
    //войти в аккаунт
    public void loginFromButtonMainPage(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.loginUser(user);
        mainPage.clickProfileButton();
        Assert.assertTrue(ProfilePage.profileTabIsEnabled());
    }
    @Test
    //личный кабинет
    public void loginFromLK(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickProfileButton();
        loginPage.loginUser(user);
        mainPage.clickProfileButton();
        Assert.assertTrue(ProfilePage.profileTabIsEnabled());
    }
    //Через страницу регистрации
    @Test
    public void loginFromRegistration() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickProfileButton();
        registrationPage.clickRegisterButtonToRegPage();
        registrationPage.clickLoginButton();
        loginPage.loginUser(user);
        mainPage.clickProfileButton();
        Assert.assertTrue(ProfilePage.profileTabIsEnabled());

    }
    //Через восстановление пароля
    @Test
    public void loginFronRestorePass() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ResetPassPage resetPassPage = new ResetPassPage(driver);

        mainPage.clickProfileButton();
        loginPage.clickResetButton();
        resetPassPage.clickLoginButtonUnderResetting();
        loginPage.loginUser(user);
        mainPage.clickProfileButton();
        Assert.assertTrue(ProfilePage.profileTabIsEnabled());
    }
}
