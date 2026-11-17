package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class IssuesPage extends SideBar {
    @FindBy(xpath = "//a[@data-test='createIssueButton']")
    WebElement createIssueButton;

    @FindBy(xpath = "//td[@data-test='ring-table-cell summary'][1]//a")
    private WebElement lastAddedIssueSummary;

    @FindBy(xpath = "//input[@data-test='select-all-tickets-checkbox']//parent::label")
    private WebElement selectAllTicketsCheckbox;

    @FindBy(xpath = "//button[@data-test='delete-item-button']")
    private WebElement deleteItemButton;

    @FindBy(xpath = "//button[@data-test='confirm-ok-button']")
    private WebElement confirmButton;

    public CreateIssueWindow clickCreateIssueButton() {
        wait.until(elementToBeClickable(createIssueButton)).click();
        return new CreateIssueWindow();
    }

    public IssuesPage refresh() {
        driver.navigate().refresh();
        return this;
    }

    public String getLastAddedIssueSummary() {
        return wait.until(visibilityOf(lastAddedIssueSummary)).getText();
    }

    public IssuesPage clickSelectAllTicketsCheckbox() {
        wait.until(visibilityOf(selectAllTicketsCheckbox)).click();
        return this;
    }

    public IssuesPage clickDeleteItemButton() {
        wait.until(visibilityOf(deleteItemButton)).click();
        return this;
    }

    public IssuesPage clickConfirmButton() {
        wait.until(visibilityOf(confirmButton)).click();
        return this;
    }

    public IssuesPage deleteAllIssues() {
        clickSelectAllTicketsCheckbox();
        clickDeleteItemButton();
        clickConfirmButton();
        return this;
    }

    public static class CreateIssueWindow extends BasePage {
        @FindBy(xpath = "//textarea[@data-test='summary']")
        private WebElement summaryInput;

        @FindBy(xpath = "//div[@data-test='wysiwyg-editor-content']")
        private WebElement descriptionInput;

        @FindBy(xpath = "//button[@data-test='submit-button']")
        private WebElement submitButton;

        public CreateIssueWindow inputSummary(String summary) {
            if (summary != null) wait.until(ExpectedConditions.visibilityOf(summaryInput)).sendKeys(summary);
            return this;
        }

        public CreateIssueWindow inputDescription(String description) {
            if (description != null) wait.until(ExpectedConditions.visibilityOf(descriptionInput)).sendKeys(description);
            return this;
        }

        public CreatedIssueWindow clickSubmitButton() {
            if (isSubmitClickable()) {
                submitButton.click();
                return new CreatedIssueWindow();
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

    public static class CreatedIssueWindow extends BasePage {
        @FindBy(xpath = "//h1[@data-test='ticket-summary']")
        private WebElement ticketSummary;

        @FindBy(xpath = "//div[@data-test='drop-zone-container'][1]//p[1]")
        private WebElement filledDescription;

        @FindBy(xpath = "//button[@data-test='ring-dialog-close-button']")
        private WebElement closeButton;

        public String getTicketSummaryValue() {
            return wait.until(ExpectedConditions.visibilityOf(ticketSummary)).getText();
        }

        public String getFilledDescriptionValue() {
            return wait.until(ExpectedConditions.visibilityOf(filledDescription)).getText();
        }

        public IssuesPage clickCloseButton() {
            wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
            return new IssuesPage();
        }
    }
}
