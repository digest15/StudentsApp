package com.example.application.backend.services.base;

import com.example.application.backend.entity.base.BaseIdEntity;

import java.util.List;

public interface StandartEntityService<T, DTO> {
    List<DTO> getItemsForGrid();

    List<DTO> getItemsForGrid(String pattern);

    List<T> getItems();

    List<T> getItems(String pattern);

    void save(T item);

    void delete(T item);
}
