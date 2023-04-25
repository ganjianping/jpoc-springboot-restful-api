package com.ganjp.jpoc.module.auth.user;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;


@Getter
@Setter
public class UserRequest {

    @Pattern(regexp = "$|[a-zA-Z0-9_-]{5,20}", message = "User name must contain 5-20 characters, including letters, digits, underscores, and hyphens")
    private String userName;

    private String nickName;

    @Pattern(regexp = "$|[0-9]{2,4}", message = "Mobile country code must be between 1 and 4 digits")
    private String mobileCountryCode;

    @Pattern(regexp = "$|[0-9]{6,15}", message = "Mobile number must contain 6-15 digits")
    private String mobileNumber;

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
    private String password;

    private UserStatus status;

    public UserEntity toUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setNickName(nickName);
        userEntity.setMobileCountryCode(mobileCountryCode);
        userEntity.setMobileNumber(mobileNumber);
        userEntity.setEmail(email);
        userEntity.setPassword(password); // Note: You should hash the password before setting it.
        userEntity.setStatus(status);
        return userEntity;
    }

    public UserEntity toUser(UserEntity existingUserEntity) {
        if (StringUtils.hasText(userName)) existingUserEntity.setUserName(userName);
        if (StringUtils.hasText(nickName)) existingUserEntity.setNickName(nickName);
        if (StringUtils.hasText(mobileCountryCode)) existingUserEntity.setMobileCountryCode(mobileCountryCode);
        if (StringUtils.hasText(mobileNumber)) existingUserEntity.setMobileNumber(mobileNumber);
        if (StringUtils.hasText(email)) existingUserEntity.setEmail(email);
        if (StringUtils.hasText(password)) existingUserEntity.setPassword(password); // Note: You should hash the password before setting it.
        if (status != null) existingUserEntity.setStatus(status);
        return existingUserEntity;
    }

    @AssertTrue(message = "Mobile country code and mobile number must be null or not null at the same time")
    public boolean isMobileComplete() {
        return (StringUtils.hasText(mobileCountryCode) && StringUtils.hasText(mobileNumber)) ||
                (!StringUtils.hasText(mobileCountryCode) && !StringUtils.hasText(mobileNumber));
    }

    @AssertTrue(message = "User name, mobile number and email cannot be null at the same time")
    public boolean isNameOrMobileOrEmailPresent() {
        return StringUtils.hasText(userName) || StringUtils.hasText(mobileNumber) || StringUtils.hasText(email);
    }
}

