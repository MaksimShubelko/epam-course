package com.example.epamcourse.model.validator;

public interface SecureInformationValidator {
    boolean isNameValid(String name);

    boolean isSurnameValid(String surname);

    boolean isLastnameValid(String lastname);
}
