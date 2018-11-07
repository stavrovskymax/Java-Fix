package ru.ivmiit.repositories;

import ru.ivmiit.models.User;

import java.util.List;

public interface IUsersRepository {
    List<User> findAll();
}
