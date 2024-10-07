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
import pages.ProfilePage;
import user.Generator;
import user.User;
import user.UserClient;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestLogout {
    private WebDriver driver;
    User user;
    String accessToken;

    @Before
    public void setup() {
        RestAssured.baseURI = Links.MAIN_PAGE;
        driver = WebDriverFactory.createWebDriver();
        user = Generator.generateUser();
        driver.get(Links.MAIN_PAGE);
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
    @DisplayName("LogOut user")
    public void logOut(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginButton();
        loginPage.loginUser(user);
        mainPage.clickProfileButton();
        Assert.assertTrue(ProfilePage.profileTabIsEnabled());
        profilePage.clickExitButton();
        MatcherAssert.assertThat(loginPage.getLoginTextFromHeader(), equalTo("Вход"));
    }
}
