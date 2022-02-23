package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.SubjectValidator;

/**
 * class SubjectValidatorImpl
 *
 * @author M.Shubelko
 */
public class SubjectValidatorImpl implements SubjectValidator {

    /**
     * The constant MIN_SUBJECT_MARK
     **/
    private static final int MIN_SUBJECT_MARK = 10;

    /**
     * The constant MAX_SUBJECT_MARK
     **/
    private static final int MAX_SUBJECT_MARK = 100;


    /**
     * The instance
     */
    private static SubjectValidator instance = new SubjectValidatorImpl();

    /**
     * The private constructor
     */
    private SubjectValidatorImpl() {
    }

    /**
     * the getting of instance
     *
     * @return instance the instance
     */
    public static SubjectValidator getInstance() {
        return instance;
    }

    /**
     * The validation of subject mark
     *
     * @param subjectMark the subject mark
     * @return true if subject mark is valid
     */
    public boolean isSubjectMarkValid(int subjectMark) {
        return subjectMark >= MIN_SUBJECT_MARK && subjectMark <= MAX_SUBJECT_MARK;
    }

}
