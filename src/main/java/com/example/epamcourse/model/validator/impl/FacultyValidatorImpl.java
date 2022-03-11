package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.FacultyValidator;

/**
 * class FacultyValidatorImpl
 *
 * @author M.Shubelko
 */
public class FacultyValidatorImpl implements FacultyValidator {

    /**
     * The constant FACULTY_NAME_REGEX
     **/
    private static final String FACULTY_NAME_REGEX = "^[A-ЯЁ]([а-яё]+\\s?)+$";

    /**
     * The constant MIN_APPLICANTS
     **/
    private static final int MIN_APPLICANTS = 5;

    /**
     * The constant MAX_APPLICANTS
     **/
    private static final int MAX_APPLICANTS = 80;

    /**
     * The instance
     */
    private static FacultyValidator instance = new FacultyValidatorImpl();

    /**
     * The private constrictor
     */
    private FacultyValidatorImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static FacultyValidator getInstance() {
        return instance;
    }

    /**
     * The validation of faculty name
     *
     * @param facultyName the faculty name
     * @return true if faculty name is valid
     */
    public boolean isFacultyNameValid(String facultyName) {
        return facultyName != null && facultyName.matches(FACULTY_NAME_REGEX);
    }

    /**
     * The validation of recruitment plan
     *
     * @param recruitmentPlan the recruitment plan
     * @return true if recruitment plan is valid
     */
    public boolean isRecruitmentPlanValid(int recruitmentPlan) {
        return recruitmentPlan >= MIN_APPLICANTS && recruitmentPlan <= MAX_APPLICANTS;
    }

}
