package com.example.application.backend.entity;

import com.example.application.backend.entity.base.VersionedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP_T",
        indexes = {
                @Index(name = "byNumber", columnList = "number")
        })
@Data
public class Group extends VersionedEntity {
    @Column(name = "NUMBER")
    private String number;

    @Column(name = "SPECIALTY_NAME")
    private String specialtyName;
}
