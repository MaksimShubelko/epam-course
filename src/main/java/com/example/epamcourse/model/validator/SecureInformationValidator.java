package com.example.epamcourse.model.validator;

public interface SecureInformationValidator {

    /**
     * The validation of name
     *
     * @param name the name
     * @return true if name is valid
     */
    boolean isNameValid(String name);


    /**
     * The validation of surname
     *
     * @param surname the surname
     * @return true if surname is valid
     */
    boolean isSurnameValid(String surname);


    /**
     * The validation of lastname
     *
     * @param lastname the lastname
     * @return true if lastname is valid
     */
    boolean isLastnameValid(String lastname);
}
