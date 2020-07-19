package com.example.copsboot.security.oauth2;

import com.example.copsboot.domain.user.User;
import com.example.copsboot.domain.user.UserId;
import com.example.copsboot.domain.user.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationUserDetails extends org.springframework.security.core.userdetails.User {
    private static final String ROLE_PREFIX = "ROLE_";
    private final UserId userId;

    public ApplicationUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), createAuthorities(user.getRoles()));
        this.userId = user.getId();
    }

    public UserId getUserId() {
        return this.userId;
    }

    private static Collection<SimpleGrantedAuthority> createAuthorities(Set<UserRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.name()))
                .collect(Collectors.toSet());
    }
}
