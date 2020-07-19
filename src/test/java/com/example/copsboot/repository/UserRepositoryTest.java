package com.example.copsboot.repository;

import com.example.copsboot.domain.user.User;
import com.example.orm.jpa.InMemoryUniqueIdGenerator;
import com.example.orm.jpa.UniqueIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> uniqueIdGenerator() {
            return new InMemoryUniqueIdGenerator();
        }
    }

    @Autowired
    private UserRepository userRepository;


    @Test
    public void shouldStoreUser() {
        User officer = Users.officer();

        User savedUser = userRepository.save(officer);

        assertThat(savedUser).isNotNull();
        assertThat(userRepository.count()).isEqualTo(1L);
    }

    @Test
    public void shouldFindUserByEmail() {
        User officer = Users.officer();
        String email = officer.getEmail();

        userRepository.save(officer);

        assertThat(userRepository.findByEmailIgnoreCase(email)).isNotEmpty();
    }
}
