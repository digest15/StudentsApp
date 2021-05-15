package com.example.application.backend.entity;

import com.example.application.backend.entity.base.StandardEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Student extends StandardEntity {
    private String lastName;

    private String firstName;

    private String patronomic;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    public Student() {
    }
}
