package com.example.epamcourse.model.exception;

public class IntroductoryException extends Exception {
    public IntroductoryException() {
        super();
    }

    public IntroductoryException(String message) {
        super(message);
    }

    public IntroductoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntroductoryException(Throwable cause) {
        super(cause);
    }

    protected IntroductoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
