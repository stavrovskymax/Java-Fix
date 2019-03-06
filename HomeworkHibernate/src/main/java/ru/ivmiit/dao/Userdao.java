package ru.ivmiit.dao;

public interface Userdao extends Cruddao {
    boolean exist(String login, String password);
}
