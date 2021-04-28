package com.example.application.backend.entity.base;

import com.example.application.backend.entity.base.BaseLongIdEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class StandardEntity extends BaseLongIdEntity {
    @Version
    @Column(name = "VERSION")
    private Integer version;

    public StandardEntity() {
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
