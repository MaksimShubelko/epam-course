package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;

/**
 * class CertificateValidatorImpl
 *
 * @author M.Shubelko
 */
public class CertificateValidatorImpl implements CertificateValidator {

    /** The constant MARK_REGEX **/
    private static final String MARK_REGEX = "^([3-9][.,][0-9]|10.0)$";

    /**
     * The instance
     */
    private static CertificateValidator instance = new CertificateValidatorImpl();

    /**
     * The private constructor
     */
    private CertificateValidatorImpl() {
    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static CertificateValidator getInstance() {
        return instance;
    }

    /**
     * The validation of certificate mark
     *
     * @param certificateMark the certificate mark
     * @return true if certificate's mark is valid
     */
    public boolean isCertificateMarkValid(double certificateMark) {
        boolean isValid = Double.toString(certificateMark)
                .matches(MARK_REGEX);
        return isValid;
    }
}
