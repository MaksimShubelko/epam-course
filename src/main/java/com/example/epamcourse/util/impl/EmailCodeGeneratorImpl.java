package com.example.epamcourse.util.impl;

import com.example.epamcourse.util.EmailCodeGenerator;

import java.util.Random;

/**
 * class EmailCodeGeneratorImpl
 *
 * @author M.Shubelko
 */
public class EmailCodeGeneratorImpl implements EmailCodeGenerator {

    /**
     * The constant MIN_RANGE
     **/
    private static final int MIN_RANGE = 100000;

    /**
     * The constant BOUND
     **/
    private static final int BOUND = 900000;

    /**
     * The instance
     */
    private static EmailCodeGenerator instance = new EmailCodeGeneratorImpl();

    /**
     * The private constructor
     */
    private EmailCodeGeneratorImpl() {

    }

    /**
     * The getting of instance
     *
     * @return instance the instance
     */
    public static EmailCodeGenerator getInstance() {
        return instance;
    }

    /**
     * The code generation
     *
     * @return the random int [100000; 999999]
     */
    @Override
    public int generateCode() {
        Random random = new Random();
        return random.nextInt(BOUND) + MIN_RANGE;
    }
}
