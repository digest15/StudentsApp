package com.example.application.backend.entity;

import com.example.application.backend.entity.base.StandardEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Student extends StandardEntity {
    //TODO Добавтиь Lombook?
    private String lastName;

    private String firstName;

    private String patronomic;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    public Student() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronomic() {
        return patronomic;
    }

    public void setPatronomic(String patronomic) {
        this.patronomic = patronomic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
