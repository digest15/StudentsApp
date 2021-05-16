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
                @Index(name = "byNumber", columnList = "number"),
                @Index(name = "bySpecialtyName", columnList = "specialtyName")
        })
@Data
public class Group extends VersionedEntity {
    private String number;

    private String specialtyName;
}
