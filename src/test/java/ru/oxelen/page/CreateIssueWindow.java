package ru.oxelen.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateIssueWindow extends BasePage {
    @FindBy(xpath = "//textarea[@data-test='summary']")
    private WebElement summaryInput;

    @FindBy(xpath = "//div[@data-test='wysiwyg-editor-content']")
    private WebElement descriptionInput;

    @FindBy(xpath = "//button[@data-test='submit-button']")
    private WebElement submitButton;

    public CreateIssueWindow(WebDriver driver) {
        super(driver);
    }

    public CreateIssueWindow inputSummary(String summary) {
        if (summary != null) wait.until(ExpectedConditions.visibilityOf(summaryInput)).sendKeys(summary);
        return this;
    }

    public CreateIssueWindow inputDescription(String description) {
        if (description != null)
            wait.until(ExpectedConditions.visibilityOf(descriptionInput)).sendKeys(description);
        return this;
    }

    public CreatedIssueWindow clickSubmitButton() {
        if (isSubmitClickable()) {
            submitButton.click();
            return new CreatedIssueWindow(driver);
        } else {
            throw new RuntimeException("Button is not clickable");
        }
    }

    public boolean isSubmitClickable() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(submitButton));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
