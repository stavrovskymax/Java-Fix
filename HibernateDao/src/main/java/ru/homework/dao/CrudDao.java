package ru.homework.dao;

import java.util.List;

public interface CrudDao<T> {
    public List<T> findAll();
}
