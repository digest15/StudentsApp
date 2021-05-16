package com.example.application.backend.dto.student;

import com.example.application.backend.dto.dase.BaseIdDto;
import com.example.application.backend.dto.dase.VersionedIdDto;
import com.example.application.backend.dto.group.GroupDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto extends VersionedIdDto<Long> {
    private String lastName;

    private String firstName;

    private String patronomic;

    private LocalDate birthday;

    private GroupDto group;
}
