package ru.oxelen.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.oxelen.conf.YouTrackConfig;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='Sign up']/../parent::a")
    private WebElement signupButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(YouTrackConfig.baseUrl + "/hub/auth");
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
        if (tryLogin(username, password)) {
            return new DashboardPage(driver);
        } else {
            throw new RuntimeException("Bad data");
        }
    }

    public boolean tryLogin(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        try {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public RegisterPage openRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
        return new RegisterPage(driver);
    }
}
