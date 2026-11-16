package page;

import conf.YouTrackConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='Sign up']/../parent::a")
    private WebElement signupButton;

    public LoginPage() {
        driver.get(YouTrackConfig.baseUrl + "/hub/auth");
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage inputUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        return this;
    }

    public DashboardPage successLogin(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new DashboardPage();
    }

    public RegisterPage openRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
        return new RegisterPage();
    }
}
