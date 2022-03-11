package com.example.epamcourse.util;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashGeneratorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hashPassword() {
        String password = "zxcASD123";
        String expectedHashPassword = "3955a0d3d64a9357e12a4e9d369522a053aab4a248a07c93c78e22d0519cd1d0";

        String actualHashPassword = HashGenerator.hashPassword(password);
        assertEquals(actualHashPassword, expectedHashPassword);
    }
}