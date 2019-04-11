package ru.ivmiit.mvc.dao;

import ru.ivmiit.mvc.models.User;

import java.util.List;

public interface UsersDao extends CrudDao<User> {
    List<User> findAllByFirstName(String firstName);
}
