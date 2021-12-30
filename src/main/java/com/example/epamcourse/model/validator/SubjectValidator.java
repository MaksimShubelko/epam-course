package com.example.epamcourse.model.validator;

public class SubjectValidator {
    private static final int minSubjectMark = 10;
    private static final int maxSubjectMark = 100;
    private static SubjectValidator instance = new SubjectValidator();

    private SubjectValidator() {
    }

    public static SubjectValidator getInstance() {
        return instance;
    }

    public boolean isSubjectMarkValid(int subjectMark) {
        return subjectMark >= minSubjectMark && subjectMark <= maxSubjectMark;
    }

}
