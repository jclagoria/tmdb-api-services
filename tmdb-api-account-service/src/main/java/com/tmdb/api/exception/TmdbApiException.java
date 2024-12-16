package com.tmdb.api.exception;

public class TmdbApiException extends RuntimeException {

    private final int statusCode;

    public TmdbApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
