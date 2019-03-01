package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import java.util.List;

public class UserDaoImplWithSpringJdbcTemplate implements UsersDao {
    public List<User> findAllByFirstName(String fistName) {
        return null;
    }

    public boolean exist(String login, String password) {
        return false;
    }

    public User find(int id) {
        return null;
    }

    public void save(User model) {

    }

    public void update(User model) {

    }

    public void delete(User model) {

    }

    public List<User> findAll() {
        return null;
    }
}
