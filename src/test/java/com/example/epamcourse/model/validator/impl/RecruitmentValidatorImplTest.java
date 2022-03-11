package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;
import com.example.epamcourse.model.validator.RecruitmentValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.*;

public class RecruitmentValidatorImplTest {
    private RecruitmentValidator validator;

    @DataProvider(name = "dataForRecruitmentFinishingDateFieldCheck")
    private Object[][] dataForPasswordField() {
        LocalDateTime beforeNow = LocalDateTime.MIN;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime afterNow = LocalDateTime.MAX;
        return new Object[][]{
                {beforeNow, false},
                {now, false},
                {afterNow, true},
        };
    }

    @BeforeClass
    void setUp() {
        validator = RecruitmentValidatorImpl.getInstance();
    }

    @Test(dataProvider = "dataForRecruitmentFinishingDateFieldCheck")
    public void testIsFinishRecruitmentValid(LocalDateTime finishDate, boolean expected) {
        boolean actual = validator.isFinishRecruitmentValid(finishDate);

        assertEquals(actual, expected);
    }
}