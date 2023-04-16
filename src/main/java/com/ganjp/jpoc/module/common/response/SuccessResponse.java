package com.ganjp.jpoc.module.common.response;

public class SuccessResponse<T> extends ApiResponse<T> {

    public SuccessResponse(T data, ResultCode resultCode) {
        super(data, resultCode.getMessage(), resultCode.getCode());
    }
}
