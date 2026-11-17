package model;

public record RegisterUserModel(
        String username,
        String login,
        String email,
        String password,
        String confirmPassword
) {
}
