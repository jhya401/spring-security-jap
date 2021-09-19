package com.study.springsecurityjap;

import com.study.springsecurityjap.model.MyUserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class MyUserDetailServiceTest {

    @InjectMocks
    MyUserDetailService myUserDetailService;

    @Mock
    UserRepository userRepository;

    @DisplayName("사용자 권한 유저를 조회한다.")
    @Test
    void loadUserByUsername() {

        given(userRepository.findByUserName("userLoginId"))
                .willReturn(Optional.of(MockBeans.ofUser("userLoginId")));

        UserDetails userDetails = myUserDetailService.loadUserByUsername("userLoginId");
        MyUserDetails myUser = (MyUserDetails) userDetails;

        // then
        String role = myUser.getAuthorities().stream().findAny().get().toString();
        Assertions.assertEquals("ROLE_USER", role);
    }

    @DisplayName("로그인 사용자를 찾을 수 없을 때 예외를 발생시킨다.")
    @Test
    void name() {
        // given
        given(userRepository.findByUserName("userLoginId"))
                .willReturn(Optional.ofNullable(null));

        // when
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {  // 이거
            UserDetails userDetails = myUserDetailService.loadUserByUsername("userLoginId");
            MyUserDetails myUser = (MyUserDetails) userDetails;
        }, "조회되는 사용자가 없으므로 Not Fonwd 예외가 발생해야 한다.");

        // then
    }
}