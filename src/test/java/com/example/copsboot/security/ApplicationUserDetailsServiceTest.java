package com.example.copsboot.security;

import com.example.copsboot.domain.user.User;
import com.example.copsboot.repository.UserRepository;
import com.example.copsboot.repository.Users;
import com.example.copsboot.security.oauth2.ApplicationUserDetails;
import com.example.copsboot.security.oauth2.ApplicationUserDetailsService;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationUserDetailsServiceTest {
    @Test
    public void giveExistingUsername_whenLoadingUser_userIsReturned() {
        UserRepository userRepository = mock(UserRepository.class);
        ApplicationUserDetailsService applicationUserDetailsService = new ApplicationUserDetailsService(userRepository);

        when(userRepository.findByEmailIgnoreCase(Users.OFFICER_EMAIL)).thenReturn(Optional.of(Users.officer()));

        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(Users.OFFICER_EMAIL);

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(Users.OFFICER_EMAIL);
        assertThat(userDetails.getAuthorities()).extracting(GrantedAuthority::getAuthority).contains("ROLE_OFFICER");
        assertThat(userDetails).isInstanceOfSatisfying(ApplicationUserDetails.class, applicationUserDetails -> {
            assertThat(applicationUserDetails.getUserId()).isEqualTo(Users.officer().getId());
        });

    }

    @Test
    public void givenNotExistingUsername_whenLoadingUser_exceptionThrown() {
        UserRepository userRepository = mock(UserRepository.class);
        ApplicationUserDetailsService applicationUserDetailsService = new ApplicationUserDetailsService(userRepository);
        when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() ->  applicationUserDetailsService.loadUserByUsername("i@donotexist.com"))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
