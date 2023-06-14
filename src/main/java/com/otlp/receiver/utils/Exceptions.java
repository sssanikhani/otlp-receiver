package com.otlp.receiver.utils;

public class Exceptions {
    public static class InvalidArguments extends BaseException {
        public InvalidArguments() {
            super();
        }

        public InvalidArguments(String message) {
            super(message);
        }
    }

    public static class ValidationError extends BaseException {
        public ValidationError() {
            super();
        }

        public ValidationError(String message) {
            super(message);
        }
    }

    public static class ValidationWarning extends BaseException {
        public ValidationWarning(String message) {
            super(message);
        }

        public ValidationWarning() {
            super();
        }
    }
}
