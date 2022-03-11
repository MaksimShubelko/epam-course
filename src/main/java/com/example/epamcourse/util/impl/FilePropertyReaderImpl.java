package com.example.epamcourse.util.impl;

import com.example.epamcourse.util.FilePropertyReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * class FilePropertyReaderImpl
 *
 * @author M.Shubelko
 */
public class FilePropertyReaderImpl implements FilePropertyReader {

    /**
     * The logger
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The constant MAIL_PROPERTIES_PATH
     **/
    private static final String MAIL_PROPERTIES_PATH = "mail.properties";

    /**
     * The reading of mail properties
     *
     * @return the file input stream
     */
    public FileInputStream fileReadProperty() {
        FileInputStream mailPropertiesFileInputStream = null;
        File file = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource(MAIL_PROPERTIES_PATH)).getFile()
        );
        try {
            mailPropertiesFileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "Error when reading file", e);
        }
        return mailPropertiesFileInputStream;
    }
}
