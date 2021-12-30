package com.example.epamcourse.model.validator;

public class SecureInformationValidator {
    private final static String NAME_REGEX = "^([А-ЯЁ][а-яё]+[\\-\\s]?){3,}$";
    private final static String SURNAME_REGEX = "^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?\\s[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*$/";
    private final static String LASTNAME_REGEX = "^([А-ЯЁ][а-яё]+[\\-\\s]?){4,}$";
    private static SecureInformationValidator instance = new SecureInformationValidator();

    private SecureInformationValidator() {
    }

    public static SecureInformationValidator getInstance() {
        return instance;
    }

    public boolean isNameValid(String name) {
        return name != null && !name.isBlank() && name.matches(NAME_REGEX);
    }

    public boolean isSurnameValid(String surname) {
        return surname != null && !surname.isBlank() && surname.matches(SURNAME_REGEX);
    }


    public boolean isLastnameValid(String lastname) {
        return lastname != null && !lastname.isBlank() && lastname.matches(LASTNAME_REGEX);
    }
}
