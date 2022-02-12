package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.AccountValidator;

import java.util.Objects;

public class AccountValidatorImpl implements AccountValidator {
    private final static String LOGIN_REGEX = "^[a-z]+([-_]?[a-z0-9]+){0,2}$";
    private final static String PASSWORD_REGEX = "(?=.*\\d)(?=.*\\p{Lower})(?=.*\\p{Upper})[\\d\\p{Alpha}]{8,30}";
    private final static String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static AccountValidatorImpl instance = new AccountValidatorImpl();

    private AccountValidatorImpl() {
    }

    public static AccountValidatorImpl getInstance() {
        return instance;
    }

    public boolean isLoginValid(String login) {
        System.out.println(login);
        System.out.println(Objects.equals(login, null));
        System.out.println(login != null && !login.isBlank() && login.matches(LOGIN_REGEX));
        return login != null && !login.isBlank() && login.matches(LOGIN_REGEX);
    }

    public boolean isPasswordValid(String password) {
        System.out.println(password != null && !password.isBlank() && password.matches(PASSWORD_REGEX));
        return password != null && !password.isBlank() && password.matches(PASSWORD_REGEX);
    }

    public boolean passwordCheck(String password, String passwordChecker) {
        return password.equals(passwordChecker);
    }

    public boolean isEmailValid(String email) {
        return email != null && !email.isBlank() && email.matches(EMAIL_REGEX);
    }

}
