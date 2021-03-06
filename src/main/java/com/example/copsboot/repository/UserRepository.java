package com.example.copsboot.repository;

import com.example.copsboot.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> , UserRepositoryCustom {
    Optional<User> findByEmailIgnoreCase(String email);
}
