package ru.oxelen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.oxelen.page.DashboardPage;
import ru.oxelen.page.LoginPage;

import java.util.List;

import static ru.oxelen.conf.YouTrackConfig.adminLogin;
import static ru.oxelen.conf.YouTrackConfig.adminPassword;

@DisplayName("Тесты вкладки Dashboard")
public class DashboardTest extends BaseTest {
    public static final String CREATE_DASHBOARD_DATA_PATH = "/test/data/success-create-dashboard-data.csv";

    private DashboardPage dashboardPage;

    @BeforeEach
    public void openDashboardPage() {
        dashboardPage = new LoginPage(getDriver())
                .successLogin(adminLogin, adminPassword);
    }

    @DisplayName("Успешно создать дашборд")
    @ParameterizedTest(name = "{index}. dashboardName: {0}")
    @CsvFileSource(resources = CREATE_DASHBOARD_DATA_PATH, numLinesToSkip = 1)
    public void createDashboard(String dashboardName) {
        dashboardPage
                .createNewDashboard(dashboardName);

        List<String> dashboards = dashboardPage.getDashboardNames();
        Assertions.assertTrue(
                dashboards.contains(dashboardName),
                "Должен появиться дашборд с именем " + dashboardName
        );
    }
}
