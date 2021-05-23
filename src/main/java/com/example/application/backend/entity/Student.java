package com.example.application.backend.entity;

import com.example.application.backend.entity.base.VersionedEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENT",
        indexes = {
                @Index(name = "byFullName", columnList = "fullName")
        })
public class Student extends VersionedEntity {
    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String patronomic;

    @Getter @Setter
    private String fullName;

    @Getter @Setter
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
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
