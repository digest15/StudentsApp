package com.example.application.backend.dto;

import java.time.LocalDate;

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
