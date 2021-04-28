package com.example.application.backend.entity.base;

import javax.persistence.*;

@MappedSuperclass
public class BaseLongIdEntity extends BaseIdEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    public BaseLongIdEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
