package com.otlp.receiver.utils;

import com.google.gson.Gson;
import io.opentelemetry.proto.common.v1.AnyValue;
import io.opentelemetry.proto.common.v1.ArrayValue;
import io.opentelemetry.proto.common.v1.KeyValue;
import io.opentelemetry.proto.common.v1.KeyValueList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jsonizer {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return "0x" + new String(hexChars);
    }

    private static List<Object> getSerializable(ArrayValue arrayValue) {
        List<Object> res = new ArrayList<>();
        for (AnyValue value : arrayValue.getValuesList()) {
            res.add(getSerializable(value));
        }
        return res;
    }

    private static Map<String, Object> getSerializable(KeyValueList keyValueList) {
        Map<String, Object> res = new HashMap<>();
        for (KeyValue keyValue : keyValueList.getValuesList()) {
            res.put(keyValue.getKey(), getSerializable(keyValue.getValue()));
        }
        return res;
    }

    private static Object getSerializable(AnyValue value) {
        switch (value.getValueCase()) {
            case INT_VALUE -> {
                return value.getIntValue();
            }
            case BOOL_VALUE -> {
                return value.getBoolValue();
            }
            case DOUBLE_VALUE -> {
                return value.getDoubleValue();
            }
            case STRING_VALUE -> {
                return value.getStringValue();
            }
            case BYTES_VALUE -> {
                return bytesToHex(value.getBytesValue().toByteArray());
            }
            case ARRAY_VALUE -> {
                return getSerializable(value.getArrayValue());
            }
            case KVLIST_VALUE -> {
                return getSerializable(value.getKvlistValue());
            }
            default -> {
                return null;
            }
        }
    }

    public static String jsonize(AnyValue anyValue) {
        return new Gson().toJson(getSerializable(anyValue));
    }
}
