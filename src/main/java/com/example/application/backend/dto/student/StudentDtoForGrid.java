package com.example.application.backend.dto.student;

import com.example.application.backend.dto.dase.BaseIdDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDtoForGrid extends BaseIdDto<Long> {
    private String lastName;

    private String firstName;

    private String patronomic;

    private LocalDate birthday;

    private String specialtyName;

    private String groupNumber;
}
