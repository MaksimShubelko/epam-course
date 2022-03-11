package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;
import com.example.epamcourse.model.validator.FacultyValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FacultyValidatorImplTest {
    private FacultyValidator validator;

    @BeforeClass
    void setUp() {
        validator = FacultyValidatorImpl.getInstance();
    }

    @DataProvider(name = "dataForFacultyNameFieldCheck")
    private Object[][] dataForFacultyNameField() {
        return new Object[][]{
                {"Факультет проектирования роботов", true},
                {"Факультет", true},
                {"факультет проектирования роботов", false},
                {"Факультет проектирования роботов1", false},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @DataProvider(name = "dataForRecruitmentPlanFieldCheck")
    private Object[][] dataForRecruitmentPlanField() {
        return new Object[][]{
                {3, false},
                {5, true},
                {30, true},
                {80, true},
                {81, false},
        };
    }

    @Test(dataProvider = "dataForFacultyNameFieldCheck")
    public void testIsFacultyNameValid(String facultyName, boolean expected) {
        boolean actual = validator.isFacultyNameValid(facultyName);

        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataForRecruitmentPlanFieldCheck")
    public void testIsRecruitmentPlanValid(int recruitmentPlan, boolean expected) {
        boolean actual = validator.isRecruitmentPlanValid(recruitmentPlan);

        assertEquals(actual, expected);
    }
}