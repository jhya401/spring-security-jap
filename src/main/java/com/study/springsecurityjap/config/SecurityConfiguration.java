package com.study.springsecurityjap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user", "/successlogin").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/test", "register").permitAll()
            .and()  // 인증구성은 끝났고, 추가적인 Http구성을 할거라는 의미
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/successlogin", true) // 로그인 성공 후 이동할 화면(default=로그인 전에 머물던 화면 / true=무조건 여기에 적힌 화면)
                .usernameParameter("login-id")
                .passwordParameter("pwd")
            .and()
            .logout()   // 로그아웃 관련 설정 더 많음(세션제거, 쿠키제거 등)
                //.logoutUrl("/doLogout") // 로그아웃 URL 변경가능 (default=/logout)
                .logoutSuccessUrl("/")
            .and()
            .csrf()    // csrf 보안 활성화
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(getPasswordEncoder())  // 안해도 되던데?
        ;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
