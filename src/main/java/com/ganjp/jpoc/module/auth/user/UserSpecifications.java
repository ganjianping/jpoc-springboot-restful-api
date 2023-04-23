package com.ganjp.jpoc.module.auth.user;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<UserEntity> userNameContains(String userName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("userName"), "%" + userName + "%");
    }

    public static Specification<UserEntity> nickNameContains(String nickName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nickName"), "%" + nickName + "%");
    }

    public static Specification<UserEntity> mobileNumberContains(String mobileNumber) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("mobileNumber"), "%" + mobileNumber + "%");
    }

    public static Specification<UserEntity> emailContains(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

    public static Specification<UserEntity> statusIs(String status) {
        return StringUtils.isBlank(status) ? null : (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), UserStatus.valueOf(status));
    }

    public static Specification<UserEntity> isDeletedFalse() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("isDeleted"));
    }
}

