package com.study.springsecurityjap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWebController {
    @GetMapping("/")
    public String testHome() {
        return "home";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registration";
    }

    @GetMapping("/successlogin")
    public String successLogin() {
        return "successlogin";
    }
}
