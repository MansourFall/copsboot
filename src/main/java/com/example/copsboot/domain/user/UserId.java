package com.example.copsboot.domain.user;

import com.example.orm.jpa.AbstractEntityId;
import com.example.orm.jpa.ArtifactForFramework;

import java.util.UUID;

public class UserId extends AbstractEntityId<UUID> {
    @ArtifactForFramework
    protected UserId() {

    }

    public UserId(UUID id) {
        super(id);
    }

}
