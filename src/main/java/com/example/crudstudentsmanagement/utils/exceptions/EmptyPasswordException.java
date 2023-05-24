package com.example.crudstudentsmanagement.utils.exceptions;

public class EmptyPasswordException extends RuntimeException{
    public EmptyPasswordException(String message){
        super(message);
    }

    public EmptyPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
