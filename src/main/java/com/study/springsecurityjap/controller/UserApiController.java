package com.study.springsecurityjap.controller;

import com.study.springsecurityjap.UserRepository;
import com.study.springsecurityjap.model.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registUser(RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "OK";
    }
}
