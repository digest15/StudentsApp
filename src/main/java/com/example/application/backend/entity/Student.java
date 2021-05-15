package com.example.application.backend.entity;

import com.example.application.backend.entity.base.VersionedEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENT",
        indexes = {
                @Index(name = "byfullName", columnList = "fullName")
        })
@Data
public class Student extends VersionedEntity {
    private String lastName;

    private String firstName;

    private String patronomic;

    private String fullName;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    @PrePersist
    public void prePersist() {
        updateFullName();
    }

    @PreUpdate
    public void preUpdate() {
        updateFullName();
    }

    private void updateFullName() {
        fullName = String.format("%s %s %s", firstName, lastName, patronomic).trim();
    }
}
