package com.example.application.backend.services.base;

public interface IdEntityService<T, ID> {
    T findById(ID id);
}
