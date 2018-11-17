package ru.ivmiit.repositories;

import ru.ivmiit.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryInMemoryImplOld implements IUsersRepository {
    private List<User> users;

    public UsersRepositoryInMemoryImplOld() {
        this.users = new ArrayList<>();
        User user = new User("Max", "qwerty", LocalDate.parse("1996-05-10"));
        User user1 = new User("Max1", "qwerty", LocalDate.parse("1996-05-10"));
        User user2 = new User("Max2", "qwerty", LocalDate.parse("1996-05-10"));

        users.add(user);
        users.add(user1);
        users.add(user2);
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public void save(final User user) {
        users.add(user);
    }

    @Override
    public boolean exist(String name, String password) {
        return false;
    }
}
