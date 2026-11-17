import conf.YouTrackConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.LoginPage;

import static conf.YouTrackConfig.*;

public class LoginTest extends BaseTest {
    private static final String LOGIN_DATA_PATH = "test/data/login-test-data.csv";

    @ParameterizedTest
    @CsvFileSource(resources = LOGIN_DATA_PATH, numLinesToSkip = 1)
    public void loginTest(String username, String password, boolean success) {
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals(success, loginPage.tryLogin(username, password));
    }
}
