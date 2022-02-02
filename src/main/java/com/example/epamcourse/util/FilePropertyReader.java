package com.example.epamcourse.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface FilePropertyReader {

    FileInputStream fileReadProperty() throws FileNotFoundException;
}
