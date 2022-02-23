package com.example.epamcourse.util.impl;

import com.example.epamcourse.util.FilePropertyReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * class FilePropertyReaderImpl
 *
 * @author M.Shubelko
 */
public class FilePropertyReaderImpl implements FilePropertyReader {

    /**
     * The constant MAIL_PROPERTIES_PATH
     **/
    private static final String MAIL_PROPERTIES_PATH = "mail.properties";

    /**
     * The reading of mail properties
     *
     * @return the file input stream
     * @throws FileNotFoundException the file not fount exception
     */
    public FileInputStream fileReadProperty() throws FileNotFoundException {

        return new FileInputStream(MAIL_PROPERTIES_PATH);
    }
}
