package com.ganjp.jpoc.module.auth.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> getUserById(String id);

    Page<UserEntity> getUsers(Pageable pageable);

    Page<UserEntity> getUsers(String userName, String nickName, String mobileNumber, String email, String status, Pageable pageable);

    UserEntity createUser(UserRequest userRequest);

    Optional<UserEntity> updateUser(String id, UserRequest userRequest);

    boolean deleteUser(String id);
}
