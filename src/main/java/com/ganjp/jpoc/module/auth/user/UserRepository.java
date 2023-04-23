package com.ganjp.jpoc.module.auth.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUserName(String userName);
    Page<UserEntity> findAllByIsDeletedFalse(Pageable pageable);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByMobileCountryCodeAndMobileNumber(String mobileCountryCode, String mobileNumber);
    List<UserEntity> findAllByIsDeletedFalse();

//    @Query("SELECT u FROM UserEntity u WHERE u.userName LIKE %:userName% AND u.nickName LIKE %:nickName% AND CONCAT(u.mobileCountryCode, u.mobileNumber) LIKE %:mobileNumber% AND u.email LIKE %:email% AND u.status.code = :status AND u.isDeleted = false")
//    Page<UserEntity> findByUserNameAndNickNameAndMobileNumberAndEmailAndStatus(@Param("userName") String userName,
//                                                                               @Param("nickName") String nickName,
//                                                                               @Param("mobileNumber") String mobileNumber,
//                                                                               @Param("email") String email,
//                                                                               @Param("status") String status,
//                                                                               Pageable pageable);
}


