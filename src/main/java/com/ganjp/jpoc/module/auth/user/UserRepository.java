package com.ganjp.jpoc.module.auth.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String userName);
    Page<UserEntity> findAllByIsDeletedFalse(Pageable pageable);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByMobileCountryCodeAndMobileNumber(String mobileCountryCode, String mobileNumber);
    List<UserEntity> findAllByIsDeletedFalse();
}

