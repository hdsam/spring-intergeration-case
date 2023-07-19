package com.hdsam.springclouddemo.springsecuritydemo.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * ApplicationUserServiceTest
 *
 * @author Yeo
 * @date 2023/7/18
 */
@ExtendWith(MockitoExtension.class)
class ApplicationUserServiceTest {

    private ApplicationUserService underTest;

    @Mock
    private ApplicationUserDao applicationUserDao;

    @BeforeEach
    void setUp() {
        underTest = new ApplicationUserService(applicationUserDao);
    }

    @Test
    void canLoadUserByUsername() {
        // given
        String username = "annasmith";
        given(applicationUserDao.selectApplicationUserByUsername(username))
                .willReturn(Optional.of(new ApplicationUser(username,
                        "password",
                        null,
                        true,
                        true,
                        true,
                        true)));

        // when
        UserDetails actual = underTest.loadUserByUsername(username);

        // then
        assertThat(actual.getUsername()).isEqualTo(username);

    }
}