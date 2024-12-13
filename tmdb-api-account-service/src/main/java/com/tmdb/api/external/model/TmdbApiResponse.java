package com.tmdb.api.external.model;

import java.util.Objects;

public class TmdbApiResponse {
    private boolean success;
    private int status_code;
    private String status_message;

    public TmdbApiResponse() {
    }

    public TmdbApiResponse(boolean success, int status_code, String status_message) {
        this.success = success;
        this.status_code = status_code;
        this.status_message = status_message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TmdbApiResponse that)) return false;
        return success == that.success && status_code == that.status_code && Objects.equals(status_message, that.status_message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, status_code, status_message);
    }
}
