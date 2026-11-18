package ru.oxelen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.oxelen.util.ScreenshotWatcher;

import java.time.Duration;

@ExtendWith(ScreenshotWatcher.class)
public abstract class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeEach
    public void setup() {
        if (driverThreadLocal.get() == null) {

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            driverThreadLocal.set(driver);
        }
    }

    public static void quitDriver() {
        getDriver().quit();
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
