package com.example.epamcourse.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static com.example.epamcourse.model.pool.DatabasePropertyKey.*;

/**
 * class DatabasePropertyReader
 *
 * @author M.Shubelko
 */
class DatabasePropertyReader {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The DATABASE_PROPERTY_FILE
     */
    private static final String DATABASE_PROPERTY_FILE = "database";

    /**
     * The DATABASE_DRIVER
     */
    static final String DATABASE_DRIVER;

    /**
     * The DATABASE_USERNAME
     */
    static final String DATABASE_USERNAME;

    /**
     * The DATABASE_PASSWORD
     */
    static final String DATABASE_PASSWORD;

    /**
     * The CONNECTION_POOL_SIZE
     */
    static final int CONNECTION_POOL_SIZE;

    /**
     * The DATABASE_URL
     */
    static final String DATABASE_URL;

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTY_FILE);
            DATABASE_DRIVER = resourceBundle.getString(DATABASE_DRIVER_KEY);
            DATABASE_USERNAME = resourceBundle.getString(DATABASE_USER_KEY);
            DATABASE_PASSWORD = resourceBundle.getString(DATABASE_PASSWORD_KEY);
            CONNECTION_POOL_SIZE = Integer.parseInt(resourceBundle.getString(DATABASE_POOL_SIZE_KEY));
            DATABASE_URL = resourceBundle.getString(DATABASE_URL_KEY);
        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, "File {} or key {} is not found. {}",
                    DATABASE_PROPERTY_FILE, e.getKey(), e);
            throw new RuntimeException("File or key is not found", e);
        }
    }
}