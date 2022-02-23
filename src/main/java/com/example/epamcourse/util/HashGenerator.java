package com.example.epamcourse.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class HashGenerator
 *
 * @author M.Shubelko
 */
public class HashGenerator {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The constant ALGORITHM_SHA256
     **/
    private static final String ALGORITHM_SHA256 = "SHA-256";

    /**
     * The hashing of password
     *
     * @param password the password
     * @return password the hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_SHA256);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytesEncoded = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, bytesEncoded);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "Encoding password isn't successful.", e);
        }
        return password;
    }
}
