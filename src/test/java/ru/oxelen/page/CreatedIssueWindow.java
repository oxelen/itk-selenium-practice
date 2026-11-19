package ru.oxelen.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatedIssueWindow extends BasePage {
    @FindBy(xpath = "//h1[@data-test='ticket-summary']")
    private WebElement ticketSummary;

    @FindBy(xpath = "//div[@data-test='drop-zone-container'][1]//p[1]")
    private WebElement filledDescription;

    @FindBy(xpath = "//button[@data-test='ring-dialog-close-button']")
    private WebElement closeButton;

    public CreatedIssueWindow(WebDriver driver) {
        super(driver);
    }

    public String getTicketSummaryValue() {
        return wait.until(ExpectedConditions.visibilityOf(ticketSummary)).getText();
    }

    public String getFilledDescriptionValue() {
        return wait.until(ExpectedConditions.visibilityOf(filledDescription)).getText();
    }

    public IssuesPage clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        return new IssuesPage(driver);
    }
}
