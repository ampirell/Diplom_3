import static org.junit.Assert.assertTrue;

import java.time.Duration;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestConstructorTabs {
    private static int counter = 1;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createWebDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Showing tab feelings")
    public void TabFeelings() {
        driver.get(Links.MAIN_PAGE);

        // Кликаем по вкладке "Начинки"

        driver.findElement(By.xpath(".//span[text() = 'Начинки']/parent::div")).click();

        //  проверяем, что этот элемент находится в видимой области окна браузера

        WebElement element =
                driver.findElement(By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']"));

        boolean isElementInViewport =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    System.out.println("проверка номер: " + counter++);
                                    System.out.println("rect.getX(): " + rect.getX());
                                    System.out.println("rect.getY(): " + rect.getY());
                                    System.out.println("rect.getWidth(): " + rect.getWidth());
                                    System.out.println("rect.getHeight(): " + rect.getHeight());
                                    System.out.println("windowSize.getWidth(): " + windowSize.getWidth());
                                    System.out.println("windowSize.getHeight(): " + windowSize.getHeight() + "\n");

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });

        assertTrue(isElementInViewport);
    }

    @Test
    @DisplayName("Showing tab buns")
    public void TabBuns() {
        driver.get(Links.MAIN_PAGE);

        // Кликаем по вкладке
        driver.findElement(By.xpath(".//span[text() = 'Начинки']/parent::div")).click();
        driver.findElement(By.xpath(".//span[text() = 'Булки']/parent::div")).click();

        //  проверяем, что этот элемент находится в видимой области окна браузера

        WebElement element =
                driver.findElement(By.xpath(".//img[@alt='Краторная булка N-200i']"));

        boolean isElementInViewport =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    System.out.println("проверка номер: " + counter++);
                                    System.out.println("rect.getX(): " + rect.getX());
                                    System.out.println("rect.getY(): " + rect.getY());
                                    System.out.println("rect.getWidth(): " + rect.getWidth());
                                    System.out.println("rect.getHeight(): " + rect.getHeight());
                                    System.out.println("windowSize.getWidth(): " + windowSize.getWidth());
                                    System.out.println("windowSize.getHeight(): " + windowSize.getHeight() + "\n");

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });

        assertTrue(isElementInViewport);
    }

    @Test
    @DisplayName("Showing tab sauces")
    public void TabSauces() {
        driver.get(Links.MAIN_PAGE);

        // Кликаем по вкладке

        driver.findElement(By.xpath(".//span[text() = 'Соусы']/parent::div")).click();

        //  проверяем, что этот элемент находится в видимой области окна браузера

        WebElement element =
                driver.findElement(By.xpath(".//img[@alt='Соус Spicy-X']"));

        boolean isElementInViewport =
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    System.out.println("проверка номер: " + counter++);
                                    System.out.println("rect.getX(): " + rect.getX());
                                    System.out.println("rect.getY(): " + rect.getY());
                                    System.out.println("rect.getWidth(): " + rect.getWidth());
                                    System.out.println("rect.getHeight(): " + rect.getHeight());
                                    System.out.println("windowSize.getWidth(): " + windowSize.getWidth());
                                    System.out.println("windowSize.getHeight(): " + windowSize.getHeight() + "\n");

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });

        assertTrue(isElementInViewport);
    }
}
