package com.example.copsboot.repository;

import com.example.copsboot.domain.user.UserId;
import com.example.orm.jpa.UniqueIdGenerator;

import java.util.UUID;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final UniqueIdGenerator<UUID> uniqueIdGenerator;

    public UserRepositoryImpl(UniqueIdGenerator<UUID> uniqueIdGenerator) {
        this.uniqueIdGenerator = uniqueIdGenerator;
    }

    @Override
    public UserId nextId() {
        return new UserId(uniqueIdGenerator.getNextUniqueId());
    }
}
