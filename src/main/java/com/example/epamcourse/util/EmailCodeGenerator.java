package com.example.epamcourse.util;

/**
 * interface EmailCodeGenerator
 *
 * @author M.Shubelko
 */
public interface EmailCodeGenerator {

    /**
     * The code generation
     *
     * @return the random int [100000; 999999]
     */
    int generateCode();
}
