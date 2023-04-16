package com.ganjp.jpoc.module.auth.user;

import com.ganjp.jpoc.module.common.response.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserEntity userEntity = userService.createUser(userRequest);
        SuccessResponse<UserEntity> response = new SuccessResponse<>(userEntity, ResultCode.USER_CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String id) {
        Optional<UserEntity> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            SuccessResponse<UserEntity> response = new SuccessResponse<>(optionalUser.get(), ResultCode.USER_FOUND);
            return ResponseEntity.ok(response);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(ResultCode.USER_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserEntity>>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users = userService.getUsers(pageable);
        if (users.hasContent()) {
            SuccessResponse<Page<UserEntity>> response = new SuccessResponse<>(users, ResultCode.USERS_FOUND);
            return ResponseEntity.ok(response);
        } else {
            SuccessResponse<Page<UserEntity>> response = new SuccessResponse<>(users, ResultCode.NO_USERS_FOUND);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserRequest userRequest) {
        Optional<UserEntity> optionalUser = userService.updateUser(id, userRequest);
        if (optionalUser.isPresent()) {
            SuccessResponse<UserEntity> response = new SuccessResponse<>(optionalUser.get(), ResultCode.USER_UPDATED);
            return ResponseEntity.ok(response);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(ResultCode.USER_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            SuccessResponse<Void> response = new SuccessResponse<>(null, ResultCode.USER_DELETED);
            return ResponseEntity.ok(response);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(ResultCode.USER_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}







