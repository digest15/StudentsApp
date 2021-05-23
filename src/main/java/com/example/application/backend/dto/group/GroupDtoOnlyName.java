package com.example.application.backend.dto.group;

import com.example.application.backend.dto.dase.BaseIdDto;
import lombok.Data;

@Data
public class GroupDtoOnlyName extends BaseIdDto<Long> {
    private String specialtyName;
}
