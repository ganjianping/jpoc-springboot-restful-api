package com.ganjp.jpoc.module.common.response;

import lombok.Getter;

import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // Success codes
    SUCCESS(200, "Operation successful"),
    CREATED(201, "Resource created"),
    UPDATED(204, "Resource updated"),
    DELETED(204, "Resource deleted"),

    // Error codes
    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Resource not found"),
    METHOD_NOT_ALLOWED(405, "Method not allowed"),
    CONFLICT(409, "Resource conflict"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    DATA_INTEGRITY_ERROR(1001, "DB constraint violation during insert, update or delete, e.g. primary key, foreign key or unique constraint."),
    DUPLICATE_RECORD(1002, "Duplicate record insertion due to unique key value already existing in table, violating database constraint or index."),
    INVALID_ARGUMENT(1003, "Invalid input parameter due to data format or value mismatches, invalid input or missing fields."),


    // User success code and error code
    USER_CREATED(100101, "User created successfully"),
    USER_FOUND(100102, "User found successfully"),
    USERS_FOUND(100103, "Users found successfully"),
    NO_USERS_FOUND(100104, "No users found"),
    USER_UPDATED(100105, "User updated successfully"),
    USER_DELETED(100106, "User deleted successfully"),
    USER_NOT_FOUND(100199, "User not found"),
    USER_ALREADY_EXISTS(100198, "User already exists"),
    USER_CREATION_FAILED(100197, "User creation failed"),
    USER_UPDATE_FAILED(100196, "User update failed"),
    USER_DELETE_FAILED(100195, "User delete failed"),
    INVALID_REQUEST(100194, "Invalid request"),
    VALIDATION_ERROR(100193, "Validation error");

    private final int code;
    private final String message;
}


