package com.example.application.backend.dto.dase;

import lombok.Data;

@Data
public abstract class BaseIdDto<ID> {
    private ID  id;
}
