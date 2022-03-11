package com.example.epamcourse.model.validator;

/**
 * interface CertificateValidator
 *
 * @author M.Shubelko
 */
public interface CertificateValidator {

    /**
     * The validation of certificate mark
     *
     * @param certificateMark the certificate mark
     * @return true if certificate's mark is valid
     */
    boolean isCertificateMarkValid(double certificateMark);
}
