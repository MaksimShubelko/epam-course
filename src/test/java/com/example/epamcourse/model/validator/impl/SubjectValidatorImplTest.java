package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;
import com.example.epamcourse.model.validator.SubjectValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SubjectValidatorImplTest {
    private SubjectValidator validator;

    @BeforeClass
    void setUp() {
        validator = SubjectValidatorImpl.getInstance();
    }

    @DataProvider(name = "dataForSubjectMarkFieldCheck")
    private Object[][] dataForPasswordField() {
        return new Object[][]{
                {-5, false},
                {6, false},
                {15, true},
                {75, true},
                {100, true},
                {101, false},
        };
    }

    @Test(dataProvider = "dataForSubjectMarkFieldCheck")
    public void testIsSubjectMarkValid(int subjectMark, boolean expected) {
        boolean actual = validator.isSubjectMarkValid(subjectMark);

        assertEquals(actual, expected);
    }
}