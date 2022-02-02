package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;

public class CertificateValidatorImpl implements CertificateValidator {
    private static final String MARK_REGEX = "^([3-9][.,][0-9]|10.0)$";
    private static CertificateValidatorImpl instance = new CertificateValidatorImpl();

    private CertificateValidatorImpl() {
    }

    public static CertificateValidatorImpl getInstance() {
        return instance;
    }

    public boolean isCertificateMarkValid(double subjectMark) {
        boolean isValid = Double.toString(subjectMark)
                .matches(MARK_REGEX);
        return isValid;
    }
}
