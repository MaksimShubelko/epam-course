package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CertificateValidatorImplTest {
    private CertificateValidator validator;

    @BeforeClass
    void setUp() {
        validator = CertificateValidatorImpl.getInstance();
    }

    @DataProvider(name = "dataForSubjectMarkFieldCheck")
    private Object[][] dataForPasswordField() {
        return new Object[][]{
                {0, false},
                //{-1.7, false},
                {3.0, true},
                {5.0, true},
                //{4.09, false},
                {10.0, true},
                {11.0, false},
                //{12, false}
        };
    }

    @Test(dataProvider = "dataForSubjectMarkFieldCheck")
    public void testIsCertificateMarkValid(double certificateMark, boolean isValid) {
        boolean actual = validator.isCertificateMarkValid(certificateMark);

        assertEquals(actual, isValid);
    }

}