import model.RegisterUserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import page.LoginPage;
import page.RegisterPage;

public class RegisterTest extends BaseTest{
    public static final String VALIDATION_REG_DATA_PATH = "test/data/validation-register-fields-data.csv";

    private RegisterPage registerPage;

    @BeforeEach
    public void openRegisterPage() {
        registerPage = new LoginPage()
                .openRegisterPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = VALIDATION_REG_DATA_PATH, numLinesToSkip = 1)
    public void validationFieldsTest(
            String username,
            String login,
            String email,
            String password,
            String confirmation,
            boolean success
    ) {
        RegisterUserModel regUser = new  RegisterUserModel(username, login, email, password, confirmation);
        registerPage.fillFields(regUser);

        Assertions.assertEquals(success, registerPage.isRegisterButtonClickable());
    }

}
