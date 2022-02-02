package com.example.epamcourse.util.impl;

import com.example.epamcourse.util.FilePropertyReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FilePropertyReaderImpl implements FilePropertyReader {
    private static final String MAIL_PROPERTIES_PATH = "C:\\Users\\shube\\IdeaProjects\\epam-course\\src\\main\\resources\\mail.properties";

    public FileInputStream fileReadProperty() throws FileNotFoundException {

        return new FileInputStream(MAIL_PROPERTIES_PATH);
    }
}
