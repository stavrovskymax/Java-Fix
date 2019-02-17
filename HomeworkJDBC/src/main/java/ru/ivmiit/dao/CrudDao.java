package ru.ivmiit.dao;

import java.util.List;

public interface CrudDao<T> {
    T find(int id);
    void save(T model);
    void update(T model);
    void delete(T model);

    List<T> findAll();
}
