package com.ganjp.jpoc.module.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(id.trim());
    }

    @Override
    public Page<UserEntity> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity createUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
                .userName(userRequest.getUserName())
                .nickName(userRequest.getNickName())
                .mobileCountryCode(userRequest.getMobileCountryCode())
                .mobileNumber(userRequest.getMobileNumber())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> updateUser(String id, UserRequest userRequest) {
        return userRepository.findById(id.trim())
                .flatMap(userEntity -> {
                    userEntity.setUserName(userRequest.getUserName());
                    userEntity.setNickName(userRequest.getNickName());
                    userEntity.setMobileCountryCode(userRequest.getMobileCountryCode());
                    userEntity.setMobileNumber(userRequest.getMobileNumber());
                    userEntity.setPassword(userRequest.getPassword());
                    userEntity.setEmail(userRequest.getEmail());
                    userEntity.setStatus(userRequest.getStatus());
                    return Optional.of(userRepository.save(userEntity));
                });
    }

    @Override
    public boolean deleteUser(String id) {
        return userRepository.findById(id.trim())
                .map(user -> {
                    user.setDeleted(true);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

}

