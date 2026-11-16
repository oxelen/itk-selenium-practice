package page;

import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {
    public RegisterPage() {
        PageFactory.initElements(driver, this);
    }
}
