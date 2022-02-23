package com.example.epamcourse.model.validator;

/**
 * interface CertificateValidator
 *
 * @author M.Shubelko
 */
public interface CertificateValidator {

    /**
     * The validation of subject mark
     *
     * @param subjectMark the subject mark
     * @return true if subject's mark is valid
     */
    boolean isCertificateMarkValid(double subjectMark);
}
