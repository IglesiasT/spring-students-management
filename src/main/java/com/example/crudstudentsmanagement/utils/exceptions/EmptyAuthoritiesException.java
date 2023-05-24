package com.example.crudstudentsmanagement.utils.exceptions;

public class EmptyAuthoritiesException extends RuntimeException{
    public EmptyAuthoritiesException(String message){
        super(message);
    }

    public EmptyAuthoritiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
