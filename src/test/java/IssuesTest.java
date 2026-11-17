import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.IssuesPage;
import page.LoginPage;

import static conf.YouTrackConfig.*;

public class IssuesTest extends BaseTest {
    private static final String CREATE_ISSUE_EMPTY_FIELDS_DATA = "/test/data/empty-fields-create-issue-data.csv";
    public static final String SUCCESS_CREATE_ISSUE_DATA = "/test/data/success-create-issue-data.csv";

    private IssuesPage issuesPage;

    @BeforeEach
    public void openIssuesPage() {
        issuesPage = new LoginPage()
                .successLogin(adminLogin, adminPassword)
                .openIssues();
    }

    @ParameterizedTest
    @CsvFileSource(resources = CREATE_ISSUE_EMPTY_FIELDS_DATA, numLinesToSkip = 1)
    public void inputFieldToNewIssueTest(String summary, String description, boolean success) {
        Assertions.assertEquals(
                success,
                issuesPage.clickCreateIssueButton()
                        .inputSummary(summary)
                        .inputDescription(description)
                        .isSubmitClickable()
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = SUCCESS_CREATE_ISSUE_DATA, numLinesToSkip = 1)
    public void successCreateIssueTest(String summary, String description) {
        IssuesPage.CreatedIssueWindow createdIssueWindow = issuesPage
                .clickCreateIssueButton()
                .inputSummary(summary)
                .inputDescription(description)
                .clickSubmitButton();

        Assertions.assertEquals(summary, createdIssueWindow.getTicketSummaryValue());
        Assertions.assertEquals(description, createdIssueWindow.getFilledDescriptionValue());

        issuesPage = createdIssueWindow
                .clickCloseButton()
                .refresh();

        Assertions.assertEquals(summary, issuesPage.getLastAddedIssueSummary());

        issuesPage.deleteAllIssues();
    }
}
