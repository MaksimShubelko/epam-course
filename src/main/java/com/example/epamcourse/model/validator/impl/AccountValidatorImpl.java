package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.AccountValidator;

import java.util.Objects;

/**
 * class AccountValidatorImpl
 *
 * @author M.Shubelko
 */
public class AccountValidatorImpl implements AccountValidator {

    /**
     * The constant LOGIN_REGEX
     **/
    private static final String LOGIN_REGEX = "^[a-zA-Z][a-zA-Z0-9-_]{3,10}$";

    /**
     * The constant PASSWORD_REGEX
     **/
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*\\p{Lower})(?=.*\\p{Upper})[\\d\\p{Alpha}]{6,15}$";

    /**
     * The constant EMAIL_REGEX
     **/
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    /**
     * The instance
     */
    private static final AccountValidator instance = new AccountValidatorImpl();

    /**
     * The private constructor
     */
    private AccountValidatorImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static AccountValidator getInstance() {
        return instance;
    }

    /**
     * The validation of login
     *
     * @param login the login
     * @return true if login is valid
     */
    public boolean isLoginValid(String login) {

        return login != null && login.matches(LOGIN_REGEX);
    }

    /**
     * The validation of password
     *
     * @param password the password
     * @return true if password is valid
     */
    public boolean isPasswordValid(String password) {
        return password != null && !password.isBlank() && password.matches(PASSWORD_REGEX);
    }

    /**
     * The comparison of password and confirmingPassword
     *
     * @param password the password
     * @param confirmingPassword the confirming password
     * @return true if password and confirmingPassword the same
     */
    public boolean passwordCheck(String password, String confirmingPassword) {
        return password.equals(confirmingPassword);
    }

    public boolean isEmailValid(String email) {
        return email != null && !email.isBlank() && email.matches(EMAIL_REGEX);
    }

}
