package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static void setWait(WebDriverWait wait) {
        BasePage.wait = wait;
    }
}
