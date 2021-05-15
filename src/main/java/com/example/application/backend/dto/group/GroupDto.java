package com.example.application.backend.dto.group;

import com.example.application.backend.dto.dase.BaseIdDto;
import com.example.application.backend.dto.dase.VersionedIdDto;
import lombok.Data;

@Data
public class GroupDto extends VersionedIdDto<Long> {

    private String number;

    private String specialtyName;
}
