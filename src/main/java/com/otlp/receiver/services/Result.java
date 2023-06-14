package com.otlp.receiver.services;

public class Result {
    public enum Type {
        SUCCESS,
        FAILURE,
        WARNING
    }

    private Type type;
    private String message;

    public Result(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

