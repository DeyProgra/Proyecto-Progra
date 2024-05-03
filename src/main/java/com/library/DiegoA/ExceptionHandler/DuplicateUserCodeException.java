package com.library.DiegoA.ExceptionHandler;

public class DuplicateUserCodeException extends RuntimeException {
    public DuplicateUserCodeException(String message) {
        super(message);
    }
}
