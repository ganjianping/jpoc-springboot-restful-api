package com.ganjp.jpoc.module.auth.user;

public enum UserStatus {
    ACTIVE("A"),
    DISABLED("D"),
    FROZEN("F"),
    CANCELLED("C"),
    BLOCKED("B");

    private final String code;

    UserStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static UserStatus fromCode(String code) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }

}
