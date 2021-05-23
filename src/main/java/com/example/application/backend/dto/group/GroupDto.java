package com.example.application.backend.dto.group;

import com.example.application.backend.dto.dase.VersionedIdDto;
import com.example.application.backend.dto.student.StudentDto;
import lombok.Data;

import java.util.List;

@Data
public class GroupDto extends VersionedIdDto<Long> {

    private String number;

    private String specialtyName;

    private List<StudentDto> students;
}
