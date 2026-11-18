import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.LoginPage;

public class LoginTest extends BaseTest {
    private static final String LOGIN_DATA_PATH = "test/data/login-test-data.csv";

    @DisplayName("Тест авторизации.")
    @ParameterizedTest(name = "{index}. Login: {0}. Password: {1}")
    @CsvFileSource(resources = LOGIN_DATA_PATH, numLinesToSkip = 1)
    public void loginTest(String username, String password, boolean success) {
        LoginPage loginPage = new LoginPage(driver);
        Assertions.assertEquals(
                success,
                loginPage.tryLogin(username, password),
                success ? "Авторизация должна пройти успешно" : "Авторизация не должна пройти успешно"
        );
    }
}
