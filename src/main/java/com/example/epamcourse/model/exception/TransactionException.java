package com.example.epamcourse.model.exception;

/**
 * class TransactionException
 *
 * @author M.Shubelko
 */
public class TransactionException extends Exception {

    /**
     * Instantiates a new transaction exception.
     */
    public TransactionException() {
        super();
    }


    /**
     * Instantiates a new transaction exception.
     *
     * @param message the message
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new transaction exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Instantiates a new transaction exception.
     *
     * @param cause the cause
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }

}
