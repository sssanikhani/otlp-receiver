package com.otlp.receiver.utils;

import java.util.HashMap;

public class Responses {
    private static class BaseResponse {
        private String message;

        public BaseResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public HashMap<String, Object> toHashMap() {
            HashMap<String, Object> res = new HashMap<>();
            res.put("message", this.getMessage());
            return res;
        }
    }

    public static class BadRequest extends BaseResponse {
        public BadRequest(String message) {
            super(message);
        }
    }
}
