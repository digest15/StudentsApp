package com.example.application.backend.entity;

import com.example.application.backend.entity.base.StandardEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP_T")
@Data
public class Group extends StandardEntity {
    @Column(name = "NUMBER")
    private String number;

    @Column(name = "SPECIALTY_NAME")
    private String specialtyName;

    public Group() {
    }
}
