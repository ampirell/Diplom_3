import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.ProfilePage;
import user.Generator;
import user.User;
import user.UserClient;


import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestRegistr {
    private WebDriver driver;
    User user;
    User userWithWrongPass;
    String accessToken;

    @Before
    public void setup() {
        RestAssured.baseURI = Links.MAIN_PAGE;
        driver = WebDriverFactory.createWebDriver();
        user = Generator.generateUser();
        userWithWrongPass = Generator.generateUserWithWrongPass();
        driver.get(Links.MAIN_PAGE);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){

        ValidatableResponse loginResponse = UserClient.login(user);
        String accessTokenFromResponse = loginResponse.extract().path("accessToken");
        if (accessToken != null)
        {
        accessToken= accessTokenFromResponse.substring(7);
        UserClient.delete(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Registration of new user")
    public void registrNewUser(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        mainPage.clickProfileButton();

        registrationPage.clickRegisterButtonToRegPage();
        registrationPage.registerUser(user);

        MatcherAssert.assertThat((loginPage.getLoginTextFromHeader()), equalTo("Вход"));

        loginPage.loginUser(user);
        mainPage.clickProfileButton();
        Assert.assertTrue(ProfilePage.profileTabIsEnabled());
    }

    @Test
    @DisplayName("Registration user with wrong password")
    public void registrWithWrongPass(){

        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.clickLoginButton();
        registrationPage.clickRegisterButtonToRegPage();
        registrationPage.registerUser(userWithWrongPass);

        MatcherAssert.assertThat(registrationPage.getInvalidPasswordText(), equalTo("Некорректный пароль"));
    }
}
