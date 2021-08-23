package com.study.springsecurityjap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserWebController.class) // @SpringBootTest 대신 @WebMvcTest를 사용한다.(스프링부트에서 제공하는 어노테이션)
class UserWebControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHome() throws Exception {
        mockMvc     // Http 요청을 mockMvc 객체로 수행한다.
                .perform(get("/"))
                .andExpect(status().isOk()) // HTTP result = 200이여야 한다.
                .andExpect(view().name("home")) // home 뷰 파일이 있어야 한다.
                .andExpect(content().string(containsString("Welcome to Spring Security"))); // 콘텐츠에 'Welcome to Spring Security'가 포함되어야 한다.
    }
}