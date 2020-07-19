package com.example.copsboot.repository;

import com.example.copsboot.domain.user.User;
import com.example.copsboot.domain.user.UserId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class Users {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final String OFFICER_EMAIL = "officer@example.com";
    private static final String OFFICER_PASSWORD = "officer";
    private static final String CAPTAIN_EMAIL = "captain@example.com";
    private static final String CAPTAIN_PASSWORD = "captain";

    private static User OFFICER = User.createOfficer(newRandomId(), OFFICER_EMAIL, PASSWORD_ENCODER.encode(OFFICER_PASSWORD));
    private static User CAPTAIN = User.createCaptain(newRandomId(), CAPTAIN_EMAIL, PASSWORD_ENCODER.encode(CAPTAIN_PASSWORD));

    public static UserId newRandomId() {
        return new UserId(UUID.randomUUID());
    }

    public static User officer() {
        return OFFICER;
    }

    public static User captain() {
        return CAPTAIN;
    }

    public static User newRandomOfficer() {
        return newRandomOfficer(newRandomId());
    }

    public static User newRandomOfficer(UserId userId) {
        String uniqueId = userId.asString().substring(0,5);
        return User.createOfficer(userId,"user-"+ uniqueId + "@example.com",PASSWORD_ENCODER.encode("user"));
    }
}