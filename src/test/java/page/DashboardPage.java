package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//div[@id='menu-react-root']//a[@href='/dashboard']")
    private WebElement dashboardLink;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.elementToBeClickable(dashboardLink));
    }
}
