package com.study.springsecurityjap;

import com.study.springsecurityjap.model.User;

public class MockBeans {

    public static User ofUser(String name) {
        return User.builder()
                .userName("userLoginId")
                .password("password")
                .build();
    }
}
