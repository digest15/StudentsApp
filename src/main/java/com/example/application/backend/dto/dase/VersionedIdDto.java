package com.example.application.backend.dto.dase;

import lombok.Data;

@Data
public abstract class VersionedIdDto<T> extends BaseIdDto<T> {
    private Integer version;
}
