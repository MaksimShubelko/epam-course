package com.example.epamcourse.model.validator;

import com.example.epamcourse.model.validator.impl.AccountValidatorImpl;

public interface AccountValidator {

     boolean isLoginValid(String login);

     boolean isPasswordValid(String password) ;

     boolean passwordCheck(String password, String passwordChecker) ;

     boolean isEmailValid(String email);
}
