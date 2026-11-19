package ru.oxelen.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

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

    public IssuesPage(WebDriver driver) {
        super(driver);
    }

    public CreateIssueWindow clickCreateIssueButton() {
        wait.until(elementToBeClickable(createIssueButton)).click();
        return new CreateIssueWindow(driver);
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
}
