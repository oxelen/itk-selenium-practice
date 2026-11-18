import model.RegisterUserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.LoginPage;
import page.RegisterPage;

public class RegisterTest extends BaseTest {
    public static final String VALIDATION_REG_DATA_PATH = "test/data/validation-register-fields-data.csv";

    private RegisterPage registerPage;

    @BeforeEach
    public void openRegisterPage() {
        registerPage = new LoginPage(driver)
                .openRegisterPage();
    }

    @ParameterizedTest(name = "{index}. Тест возможности регистрации с полями: " +
            "Username: {0} " +
            "Login: {1} " +
            "Email: {2} " +
            "Password: {3} " +
            "Confirmation: {4}")
    @CsvFileSource(resources = VALIDATION_REG_DATA_PATH, numLinesToSkip = 1)
    public void validationFieldsTest(
            String username,
            String login,
            String email,
            String password,
            String confirmation,
            boolean success
    ) {
        RegisterUserModel regUser = new RegisterUserModel(username, login, email, password, confirmation);
        registerPage.fillFields(regUser);

        Assertions.assertEquals(
                success,
                registerPage.isRegisterButtonClickable(),
                success ? "Поля верные, регистрация должна быть возможна"
                        : "Одно или несколько полей невалидны, регистрация не должна быть возможна"
        );
    }

}
