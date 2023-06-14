package com.otlp.receiver.utils;

public class BaseException extends Throwable {
    private String message;

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException() {
        this.message = null;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
