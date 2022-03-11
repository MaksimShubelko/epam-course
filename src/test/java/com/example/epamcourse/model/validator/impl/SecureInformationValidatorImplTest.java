package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.CertificateValidator;
import com.example.epamcourse.model.validator.SecureInformationValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SecureInformationValidatorImplTest {
    private SecureInformationValidator validator;

    @BeforeClass
    void setUp() {
        validator = SecureInformationValidatorImpl.getInstance();
    }

    @DataProvider(name = "dataForFirstnameFieldCheck")
    private Object[][] dataForFirstnameNameField() {
        return new Object[][]{
                {"И", false},
                {"Слишкомдлинноеимя", false},
                {"Инокентий", true},
                {"инокентий", false},
                {"Инокентий1", false},
                {"Инокентий Инокентий", false},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @DataProvider(name = "dataForSurnameFieldCheck")
    private Object[][] dataForSurnameNameField() {
        return new Object[][]{
                {"К", false},
                {"Даннаяфамилияявляетсяслишкомдлинной", false},
                {"Куйбышев", true},
                {"куйбышев", false},
                {"Куйбышев1", false},
                {"Куйбышев Куйбышев", false},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @DataProvider(name = "dataForLastnameFieldCheck")
    private Object[][] dataForLastnameNameField() {
        return new Object[][]{
                {"П", false},
                {"Данноеотчествоявляетсяслишкомдлинным", false},
                {"Павлов", true},
                {"павлов", false},
                {"Павлов1", false},
                {"Куйбышев Куйбышев", false},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForFirstnameFieldCheck")
    public void testIsNameValid(String name, boolean expected) {
        boolean actual = validator.isNameValid(name);

        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataForSurnameFieldCheck")
    public void testIsSurnameValid(String surname, boolean expected) {
        boolean actual = validator.isNameValid(surname);

        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataForLastnameFieldCheck")
    public void testIsLastnameValid(String lastname, boolean expected) {
        boolean actual = validator.isNameValid(lastname);

        assertEquals(actual, expected);
    }
}