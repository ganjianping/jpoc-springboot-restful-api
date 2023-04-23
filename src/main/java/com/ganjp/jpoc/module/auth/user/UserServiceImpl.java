package com.ganjp.jpoc.module.auth.user;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<UserEntity> getUsers(String userName, String nickName, String mobileNumber, String email, String status, Pageable pageable) {
        Specification<UserEntity> spec = Specification.where(null);
        if (!StringUtils.isEmpty(userName)) {
            spec = spec.and(UserSpecifications.userNameContains(userName));
        }
        if (!StringUtils.isEmpty(nickName)) {
            spec = spec.and(UserSpecifications.nickNameContains(nickName));
        }
        if (!StringUtils.isEmpty(mobileNumber)) {
            spec = spec.and(UserSpecifications.mobileNumberContains(mobileNumber));
        }
        if (!StringUtils.isEmpty(email)) {
            spec = spec.and(UserSpecifications.emailContains(email));
        }
        if (!StringUtils.isEmpty(status)) {
            spec = spec.and(UserSpecifications.statusIs(status));
        }
        spec = spec.and(UserSpecifications.isDeletedFalse());

        return userRepository.findAll(spec, pageable);
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

