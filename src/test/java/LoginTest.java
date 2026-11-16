import conf.YouTrackConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import page.LoginPage;
import util.data.DataFileReader;

import java.util.stream.Stream;

public class LoginTest extends BaseTest {
    private static final String FAIL_VALIDATION_DATA_PATH = YouTrackConfig.dataPath + "/login-fail-validation-data.csv";

    @Test
    public void successLoginTest() {
        String adminLogin = YouTrackConfig.adminLogin;
        String adminPassword = YouTrackConfig.adminPassword;

        LoginPage loginPage = new LoginPage();
        loginPage.successLogin(adminLogin, adminPassword);

        String currUrl = driver.getCurrentUrl();
        Assertions.assertNotNull(currUrl);
        Assertions.assertTrue(currUrl.startsWith(YouTrackConfig.baseUrl + "/dashboard"));
    }

    @ParameterizedTest
    @MethodSource("failValidationLoginTestData")
    public void failValidationLoginTest(String email, String password) {
        LoginPage loginPage = new LoginPage();

        int a = 0;
    }

    private static Stream<Arguments> failValidationLoginTestData() {
        return DataFileReader.readCSV(FAIL_VALIDATION_DATA_PATH, "login", "password");
    }
}
