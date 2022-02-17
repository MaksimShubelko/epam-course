package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.FileNameValidator;

public class FileNameValidatorImpl implements FileNameValidator {
    private static final String FILE_NAME_REGEX = "[^s]+(.(?i)(jpg|png|jpeg|bmp))$";

    public boolean isFileNameValid(String fileName) {
        return fileName.matches(FILE_NAME_REGEX);
    }

}
