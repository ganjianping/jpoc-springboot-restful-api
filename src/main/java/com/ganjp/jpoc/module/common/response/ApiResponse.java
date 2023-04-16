package com.ganjp.jpoc.module.common.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;

@Getter
@Setter
public abstract class ApiResponse<T> {

    private T data;
    private String message;
    private Integer code;
    private Timestamp timestamp;

    private Map<String, String> errors;

    public ApiResponse(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.errors = null;
    }

    public ApiResponse(T data, String message, Integer code, Map<String, String> errors) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.errors = errors;
    }
}