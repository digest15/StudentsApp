package com.example.application.backend.dto.group;

import com.example.application.backend.dto.dase.BaseIdDto;
import lombok.Data;

@Data
public class GroupDtoForGrid extends BaseIdDto<Long> {

    private String number;

    private String specialtyName;
}
