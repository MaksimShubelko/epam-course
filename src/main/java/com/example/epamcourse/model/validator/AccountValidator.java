package com.example.epamcourse.model.validator;

/**
 * interface AccountValidator
 *
 * @author M.Shubelko
 */
public interface AccountValidator {

     /**
      * The validation of login
      *
      * @param login the login
      * @return true if login is valid
      */
     boolean isLoginValid(String login);

     /**
      * The validation of password
      *
      * @param password the password
      * @return true if password is valid
      */
     boolean isPasswordValid(String password);

     /**
      * The comparison of password and confirmingPassword
      *
      * @param password the password
      * @param confirmingPassword the confirming password
      * @return true if password and confirmingPassword the same
      */
     boolean passwordCheck(String password, String confirmingPassword);

     /**
      * The validation of email
      *
      * @param email the email
      * @return true if email is valid
      */
     boolean isEmailValid(String email);
}
