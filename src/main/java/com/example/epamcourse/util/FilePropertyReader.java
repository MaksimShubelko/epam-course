package com.example.epamcourse.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * interface FilePropertyReader
 *
 * @author M.Shubelko
 */
public interface FilePropertyReader {

    /**
     * The reading of mail properties
     *
     * @return the file input stream
     * @throws FileNotFoundException the file not fount exception
     */
    FileInputStream fileReadProperty() throws FileNotFoundException;
}
