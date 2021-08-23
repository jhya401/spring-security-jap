package com.study.springsecurityjap.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class RegistrationForm {
    private String userName;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
