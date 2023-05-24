package com.example.crudstudentsmanagement.utils.exceptions;

public class EmptyUsernameException extends RuntimeException {

    public EmptyUsernameException(String message) {
        super(message);
    }

    public EmptyUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
