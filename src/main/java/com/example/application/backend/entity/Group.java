package com.example.application.backend.entity;

import com.example.application.backend.entity.base.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP_T")
public class Group extends StandardEntity {
    @Column(name = "NUMBER")
    private String number;

    @Column(name = "SPECIALTY_NAME")
    private String specialtyName;

    public Group() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
