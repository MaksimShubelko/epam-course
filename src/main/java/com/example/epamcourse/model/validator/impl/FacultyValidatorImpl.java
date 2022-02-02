package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.FacultyValidator;

public class FacultyValidatorImpl implements FacultyValidator {
    private final static String FACULTY_NAME_REGEX = "^[A-ЯЁ]([а-яё]+\\s?)+$";
    private final static int minApplicants = 5;
    private final static int maxApplicants = 80;
    private static FacultyValidatorImpl instance = new FacultyValidatorImpl();

    private FacultyValidatorImpl() {
    }

    public static FacultyValidatorImpl getInstance() {
        return instance;
    }

    public boolean isFacultyNameValid(String facultyName) {
        return facultyName.matches(FACULTY_NAME_REGEX);
    }

    public boolean isRecruitmentPlanValid(int recruitmentPlan) {
        return recruitmentPlan >= 5 && recruitmentPlan <= 80;
    }

}
