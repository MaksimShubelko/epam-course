package com.example.epamcourse.util.impl;

import com.example.epamcourse.util.EmailCodeGenerator;

import java.util.Random;

public class EmailCodeGeneratorImpl implements EmailCodeGenerator {
    private static final int MIN_RANGE = 100000;
    private static final int BOUND = 900000;
    private static EmailCodeGeneratorImpl instance = new EmailCodeGeneratorImpl();

    private EmailCodeGeneratorImpl() {

    }

    public static EmailCodeGeneratorImpl getInstance() {
        return instance;
    }

    @Override
    public int generateCode() {
        Random random = new Random();
        return random.nextInt(BOUND) + MIN_RANGE;
    }
}
