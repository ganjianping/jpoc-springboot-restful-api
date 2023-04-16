package com.ganjp.jpoc.module.common.exception;

import com.ganjp.jpoc.module.common.response.ErrorResponse;
import com.ganjp.jpoc.module.common.response.ResultCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Throwable cause = ex.getRootCause();
        Map<String, String> errors = new HashMap<>();
        errors.put("exception",cause.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ResultCode.DATA_INTEGRITY_ERROR, errors);

        if (cause instanceof SQLException) {
            SQLException sqlException = (SQLException) cause;
            if (sqlException.getErrorCode() == 1062) {
                errorResponse = new ErrorResponse(ResultCode.DUPLICATE_RECORD, errors);
            }
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(ResultCode.INVALID_ARGUMENT, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}