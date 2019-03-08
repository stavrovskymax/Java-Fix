package ru.homework.service;

import ru.homework.dao.UserDaoImpl;
import ru.homework.models.User;

import java.util.List;

public class Service {
    private static UserDaoImpl userDao;

    public Service() {
        userDao = new UserDaoImpl();
    }

    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.closeCurrentSession();
        return users;
    }
}
