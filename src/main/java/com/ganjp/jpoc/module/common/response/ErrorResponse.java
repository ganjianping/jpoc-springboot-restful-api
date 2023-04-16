package com.ganjp.jpoc.module.common.response;

import java.util.Map;

public class ErrorResponse extends ApiResponse<Void> {

    public ErrorResponse(ResultCode errorCode) {
        super(null, errorCode.getMessage(), errorCode.getCode());
    }

    public ErrorResponse(ResultCode errorCode, String errorMessage) {
        super(null, errorMessage, errorCode.getCode());
    }

    public ErrorResponse(ResultCode errorCode, Map<String, String> errors) {
        super(null, errorCode.getMessage(), errorCode.getCode(), errors);
    }
}

