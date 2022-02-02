package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.SubjectValidator;

public class SubjectValidatorImpl implements SubjectValidator {
    private static final int minSubjectMark = 10;
    private static final int maxSubjectMark = 100;
    private static SubjectValidatorImpl instance = new SubjectValidatorImpl();

    private SubjectValidatorImpl() {
    }

    public static SubjectValidatorImpl getInstance() {
        return instance;
    }

    public boolean isSubjectMarkValid(int subjectMark) {
        return subjectMark >= minSubjectMark && subjectMark <= maxSubjectMark;
    }

}
