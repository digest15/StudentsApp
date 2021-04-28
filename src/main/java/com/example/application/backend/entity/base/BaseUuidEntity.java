package com.example.application.backend.entity.base;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseUuidEntity extends BaseIdEntity<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private UUID id;

    public BaseUuidEntity() {
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }
}
