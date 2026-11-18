package ru.oxelen.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DashboardPage extends SideBar {
    @FindBy(xpath = "//button[@data-test='new-dashboard-button']")
    private WebElement newDashboardButton;

    @FindBy(xpath = "//input[@data-test='dashboard-name-input']")
    private WebElement newDashboardNameInput;

    @FindBy(xpath = "//button[@data-test='create-button']")
    private WebElement createDashboardButton;

    @FindBy(xpath = "//div[@data-test='breadcrumbs-container']//button[@data-test='ring-link']")
    private WebElement dashboardsListButton;

    @FindBy(xpath = "//div[@data-test='entity-selector-list-item-label']//a[@data-test='ring-link']")
    private List<WebElement> dashboardsListItems;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage createNewDashboard(String dashboardName) {
        wait.until(elementToBeClickable(newDashboardButton)).click();
        wait.until(visibilityOf(newDashboardNameInput)).sendKeys(dashboardName);
        wait.until(elementToBeClickable(createDashboardButton)).click();

        return this;
    }

    public String getCurrentDashboardName() {
        return wait.until(visibilityOf(dashboardsListButton)).findElement(By.xpath("//span")).getText();
    }

    public List<String> getDashboardNames() {
        wait.until(elementToBeClickable(dashboardsListButton)).click();
        return wait.until(visibilityOfAllElements(dashboardsListButton))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
