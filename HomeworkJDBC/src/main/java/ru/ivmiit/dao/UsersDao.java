package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import java.util.List;

public interface UsersDao extends CrudDao<User> {
    List<User> findAllByFirstName(String fistName);
}
