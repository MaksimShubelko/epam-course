package com.example.epamcourse.model.validator;

public class CertificateValidator {
    private static final String MARK_REGEX = "^([3-9][.,][0-9]|10.0)$";
    private static CertificateValidator instance = new CertificateValidator();

    private CertificateValidator() {
    }

    public static CertificateValidator getInstance() {
        return instance;
    }

    public boolean isSubjectMarkValid(double subjectMark) {
        boolean isValid = Double.toString(subjectMark)
                .matches(MARK_REGEX);
        return isValid;
    }
}
