package com.otlp.receiver.utils;

import com.google.gson.Gson;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.util.*;

public class DataNormalizer {
    private final static List<String> BYTES_TYPE_KEYS = Arrays.asList("traceId", "spanId", "parentSpanId");
    private static HashMap<String, Object> deepReplaceTraceIDAndSpanIDs(HashMap<String, Object> data) throws DecoderException {
        for (String key: data.keySet()) {
            if (data.get(key) instanceof Map<?,?>) {
                data.put(key, deepReplaceTraceIDAndSpanIDs((HashMap<String, Object>) data.get(key)));
            }
            if (data.get(key) instanceof List<?> && ((List<?>) data.get(key)).size() > 0 && ((List<?>) data.get(key)).get(0) instanceof Map) {
                List<HashMap<String, Object>> list = new ArrayList<>();
                for (HashMap<String, Object> member : (List<HashMap<String, Object>>) data.get(key)) {
                    list.add(deepReplaceTraceIDAndSpanIDs(member));
                }
                data.put(key, list);
            }
            if (BYTES_TYPE_KEYS.contains(key)) {
                data.put(key, convertHexToBase64((String) data.get(key)));
            }
        }
        return data;
    }

    public static String normalizeRequestBody(HashMap<String, Object> body) throws DecoderException {
        HashMap<String, Object> normalizedData = deepReplaceTraceIDAndSpanIDs(body);
        return new Gson().toJson(normalizedData);
    }

    private static String convertHexToBase64(String hexString) throws DecoderException {
        byte[] decodedHex = Hex.decodeHex(hexString);
        return Base64.encodeBase64String(decodedHex);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String convertBytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
