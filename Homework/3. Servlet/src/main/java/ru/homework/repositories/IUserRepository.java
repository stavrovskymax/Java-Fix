package ru.homework.repositories;

import ru.homework.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    void save(User user);
    boolean exist(String name, String password);
}
