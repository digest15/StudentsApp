package com.example.application.backend.entity;

import com.example.application.backend.entity.base.VersionedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUP_T",
        indexes = {
                @Index(name = "byNumber", columnList = "number"),
                @Index(name = "bySpecialtyName", columnList = "specialtyName")
        })
public class Group extends VersionedEntity {
    @Getter @Setter
    private String number;

    @Getter @Setter
    private String specialtyName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Student> students;
}
