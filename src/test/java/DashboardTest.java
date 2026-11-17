import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.DashboardPage;
import page.LoginPage;

import java.util.List;

import static conf.YouTrackConfig.*;

public class DashboardTest extends BaseTest{
    public static final String CREATE_DASHBOARD_DATA_PATH = "test/data/success-create-dashboard-data.csv";

    private DashboardPage dashboardPage;

    @BeforeEach
    public void openDashboardPage() {
        dashboardPage = new LoginPage()
                .successLogin(adminLogin, adminPassword);
    }

    @ParameterizedTest
    @CsvFileSource(resources = CREATE_DASHBOARD_DATA_PATH, numLinesToSkip = 1)
    public void createDashboard(String dashboardName) {
        dashboardPage
                .createNewDashboard(dashboardName);

        List<String> dashboards = dashboardPage.getDashboardNames();
        Assertions.assertTrue(dashboards.contains(dashboardName));
    }
}
