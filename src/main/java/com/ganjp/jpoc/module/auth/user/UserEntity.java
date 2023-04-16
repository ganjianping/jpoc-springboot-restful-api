package com.ganjp.jpoc.module.auth.user;

import com.ganjp.jpoc.module.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "am_user", indexes = {@Index(columnList = "mobile_country_code, mobile_number", unique = true)})
public class UserEntity extends BaseEntity {

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "mobile_country_code")
    private String mobileCountryCode;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email", unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Override
    @PrePersist
    protected void onCreate() {
        super.onCreate();
        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }
}

