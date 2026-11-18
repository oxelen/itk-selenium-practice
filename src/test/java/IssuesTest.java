import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.TimeoutException;
import page.IssuesPage;
import page.LoginPage;

import static conf.YouTrackConfig.adminLogin;
import static conf.YouTrackConfig.adminPassword;

@DisplayName("Тесты вкладки Issues")
public class IssuesTest extends BaseTest {
    private static final String CREATE_ISSUE_EMPTY_FIELDS_DATA = "/test/data/empty-fields-create-issue-data.csv";
    public static final String SUCCESS_CREATE_ISSUE_DATA = "/test/data/success-create-issue-data.csv";

    private IssuesPage issuesPage;

    @BeforeEach
    public void openIssuesPage() {
        issuesPage = new LoginPage(driver)
                .successLogin(adminLogin, adminPassword)
                .openIssues();
    }

    @DisplayName("Возможность создания Issue.")
    @ParameterizedTest(name = "{index}. Заголовок: {0} " +
            "Описание: {1} " +
            "Ожидаемый результат: {2}")
    @CsvFileSource(resources = CREATE_ISSUE_EMPTY_FIELDS_DATA, numLinesToSkip = 1)
    public void inputFieldToNewIssueTest(String summary, String description, boolean success) {
        Assertions.assertEquals(
                success,
                issuesPage.clickCreateIssueButton()
                        .inputSummary(summary)
                        .inputDescription(description)
                        .isSubmitClickable(),
                success ? "Кнопка подтверждения должна быть кликабельна"
                        : "Кнопка подтверждения должна быть не кликабельна"
        );
    }

    @DisplayName("Успешное создание Issue.")
    @ParameterizedTest(name = "{index}. Заголовок: {0} " +
            "Описание: {1} ")
    @CsvFileSource(resources = SUCCESS_CREATE_ISSUE_DATA, numLinesToSkip = 1)
    public void successCreateIssueTest(String summary, String description) {
        IssuesPage.CreatedIssueWindow createdIssueWindow = issuesPage
                .clickCreateIssueButton()
                .inputSummary(summary)
                .inputDescription(description)
                .clickSubmitButton();

        Assertions.assertEquals(
                summary,
                createdIssueWindow.getTicketSummaryValue(),
                "Заголовок в окне созданного Issue должен соответствовать заголовку при создании"
        );
        Assertions.assertEquals(
                description,
                createdIssueWindow.getFilledDescriptionValue(),
                description != null && description.isBlank()
                        ? "Должно появиться специальное сообщение, с пустым description"
                        : "Описание в окне созданного Issue должно соответствовать описанию при создании"
        );
    }

    @AfterEach
    public void deleteAllIssues() {
        try {
            new LoginPage(driver)
                    .successLogin(adminLogin, adminPassword)
                    .openIssues()
                    .deleteAllIssues();
        } catch (TimeoutException e) {

        }
    }
}
