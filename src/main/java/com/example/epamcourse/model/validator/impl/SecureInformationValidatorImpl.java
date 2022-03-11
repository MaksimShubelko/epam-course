package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.SecureInformationValidator;

/**
 * class SecureInformationValidatorImpl
 *
 * @author M.Shubelko
 */
public class SecureInformationValidatorImpl implements SecureInformationValidator {

    /**
     * The constant NAME_REGEX
     **/
    private static final String NAME_REGEX = "^[А-ЯЁ][а-яё]{2,15}$";

    /**
     * The constant SURNAME_REGEX
     **/
    private static final String SURNAME_REGEX = "^[А-ЯЁ][а-яё]{5,15}(-[А-ЯЁ][а-яё]{5,15})?$";

    /**
     * The constant LASTNAME_REGEX
     **/
    private static final String LASTNAME_REGEX = "^[А-ЯЁ][а-яё]{5,25}$";

    /**
     * The instance
     */
    private static SecureInformationValidator instance = new SecureInformationValidatorImpl();

    /**
     * The private constructor
     */
    private SecureInformationValidatorImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static SecureInformationValidator getInstance() {
        return instance;
    }

    /**
     * The validation of name
     *
     * @param name the name
     * @return true if name is valid
     */
    public boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    /**
     * The validation of surname
     *
     * @param surname the surname
     * @return true if surname is valid
     */
    public boolean isSurnameValid(String surname) {
        return surname != null && !surname.isBlank() && surname.matches(SURNAME_REGEX);
    }

    /**
     * The validation of lastname
     *
     * @param lastname the lastname
     * @return true if lastname is valid
     */
    public boolean isLastnameValid(String lastname) {
        return lastname != null && !lastname.isBlank() && lastname.matches(LASTNAME_REGEX);
    }
}
