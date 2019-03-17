package ru.ivmiit.dao;

import ru.ivmiit.models.User;

public interface UsersDao extends CrudDao<User> {
    boolean exist(String login, String password);
    User findById(int id);
}
