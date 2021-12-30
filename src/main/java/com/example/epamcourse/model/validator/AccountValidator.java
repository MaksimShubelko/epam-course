package com.example.epamcourse.model.validator;

public class AccountValidator {
    private final static String LOGIN_REGEX = "[\\d\\w-]{3,25}@\\w{2,10}\\.\\w{2,5}";
    private final static String PASSWORD_REGEX = "(?=.*\\d)(?=.*\\p{Lower})(?=.*\\p{Upper})[\\d\\p{Alpha}]{8,30}";
    private final static String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static AccountValidator instance = new AccountValidator();

    private AccountValidator() {
    }

    public static AccountValidator getInstance() {
        return instance;
    }

    public boolean isLoginValid(String login) {
        return login != null && !login.isBlank() && login.matches(LOGIN_REGEX);
    }

    public boolean isPasswordValid(String password) {
        return password != null && !password.isBlank() && password.matches(PASSWORD_REGEX);
    }

    public boolean passwordCheck(String password, String passwordChecker) {
        return password.equals(passwordChecker);
    }

    public boolean isEmailValid(String email) {
        return email != null && !email.isBlank() && email.matches(EMAIL_REGEX);
    }

}
