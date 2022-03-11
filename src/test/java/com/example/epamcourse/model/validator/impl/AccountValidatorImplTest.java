package com.example.epamcourse.model.validator.impl;

import com.example.epamcourse.model.validator.AccountValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;

public class AccountValidatorImplTest {
    private AccountValidator validator;

    @BeforeClass
    void setUp() {
        validator = AccountValidatorImpl.getInstance();
    }

    @DataProvider(name = "dataForPasswordFieldCheck")
    private Object[][] dataForPasswordField() {
        return new Object[][]{
                {"1", false},
                {"m", false},
                {"123456789", false},
                {"12345mfhdg111", false},
                {"1234567891fdfd2345mZ", false},
                {"123123123mM", true},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @DataProvider(name = "dataForLoginFieldCheck")
    private Object[][] dataForLoginField() {
        return new Object[][]{
                {"1", false},
                {"m", false},
                {"lemon-leo", true},
                {"Lemon-leopard1", false},
                {"maxim123", true},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @DataProvider(name = "dataForEmailFieldCheck")
    private Object[][] dataForEmailField() {
        return new Object[][]{
                {"bogonolov@gmail.com", true},
                {"bogonolov", false},
                {"bogonolov@gmail", false},
                {"<script>alert(123)</script>", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForPasswordFieldCheck")
    public void testIsPasswordValid(String password, boolean isValid) {
        boolean actual = validator.isPasswordValid(password);

        assertEquals(actual, isValid);
    }

    @Test(dataProvider = "dataForLoginFieldCheck")
    public void testIsLoginValid(String login, boolean isValid) {
        boolean actual = validator.isLoginValid(login);

        assertEquals(actual, isValid);
    }

    @Test(dataProvider = "dataForEmailFieldCheck")
    public void testIsEmailValid(String email, boolean isValid) {
        boolean actual = validator.isEmailValid(email);

        assertEquals(actual, isValid);
    }

}