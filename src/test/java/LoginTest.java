import conf.YouTrackConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.LoginPage;


public class LoginTest extends BaseTest {
    private static final String FAIL_VALIDATION_DATA_PATH = "test/data/login-fail-validation-data.csv";

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
    @CsvFileSource(resources = FAIL_VALIDATION_DATA_PATH, numLinesToSkip = 1)
    public void failValidationLoginTest(String email, String password, boolean isLoginError, boolean isPasswordError) {
        LoginPage loginPage = new LoginPage()
                .fillFields(email, password)
                .unsuccessLogin();

        Assertions.assertEquals(isLoginError, loginPage.getUsernameBorderColor().equals("rgba"));

        int a = 0;
    }
}
