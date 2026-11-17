package page;

import model.RegisterUserModel;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RegisterPage extends BasePage{
    @FindBy(xpath = "//input[@data-test='name-field']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@data-test='login-field']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@data-test='email-field']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@data-test='login-field']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@data-test='confirm-password-field']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@data-test='register-button']")
    private WebElement registerButton;

    public RegisterPage fillFields(RegisterUserModel reg) {
        wait.until(visibilityOfAllElements(
                nameField,
                loginField,
                emailField,
                passwordField,
                confirmPasswordField
        ));

        nameField.sendKeys(reg.username());
        loginField.sendKeys(reg.login());
        emailField.sendKeys(reg.email());
        passwordField.sendKeys(reg.password());
        confirmPasswordField.sendKeys(reg.confirmPassword());

        return this;
    }

    public boolean isRegisterButtonClickable() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(elementToBeClickable(registerButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
