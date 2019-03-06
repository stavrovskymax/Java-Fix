package ru.ivmiit.dao;

public interface UsersDao extends CrudDao {
    boolean exist(String login, String password);
}
