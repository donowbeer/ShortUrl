package com.donowbeer.shorturl.bean;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

    private final int code;
    private final String message;
    private final Map<String, Object> data = new HashMap<>();

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public static ResponseData createSuccess() {
        return new ResponseData(11, "Success");
    }

    public static ResponseData badLongUrl() {
        return new ResponseData(21, "Bad Long Url");
    }

    public static ResponseData badKeyword() {
        return new ResponseData(22, "Bad Short Url");
    }

    public static ResponseData badCustomKeyword() {
        return new ResponseData(23, "Bad Custom Keyword");
    }

    public static ResponseData badCustomLength() {
        return new ResponseData(24, "Bad Custom Length");
    }


}
