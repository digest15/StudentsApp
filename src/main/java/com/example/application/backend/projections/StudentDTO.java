package com.example.application.backend.projections;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.Date;

public interface StudentDTO extends BaseIdDTO<Long> {
    String getLastName();

    String getFirstName();

    String getPatronomic();

    LocalDate getBirthday();

    String getSpecialtyName();

    String getGroupNumber();

    default String getGroupName(){
        return getSpecialtyName() + " (" + getGroupNumber() + ")";
    }

}
