package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class SideBar extends BasePage{
    @FindBy(xpath = "//a[@href='issues']")
    protected WebElement issuesHref;

    public IssuesPage openIssues() {
        wait.until(ExpectedConditions.elementToBeClickable(issuesHref)).click();
        return new IssuesPage();
    }
}
