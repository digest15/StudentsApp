package com.example.application.backend.dao.base;

import java.util.List;

public interface StandartEntityDao<T, G> {
    List<G> getItemsForGrid();

    List<G> getItemsForGrid(String pattern);

    List<T> getItems();

    List<T> getItems(String pattern);

    void save(T item);

    void delete(T item);
}
