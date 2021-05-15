package com.example.application.backend.dao.base;

public interface IdEntityDao<T, ID> {
    T findById(ID id);
}
