package com.tmdb.api.exception;

public class AccountServiceException extends RuntimeException {
    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
