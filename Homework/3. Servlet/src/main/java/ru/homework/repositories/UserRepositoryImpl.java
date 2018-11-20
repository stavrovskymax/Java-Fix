package ru.homework.repositories;

import ru.homework.fake.FakeStorage;
import ru.homework.model.User;

import java.util.List;

public class UserRepositoryImpl implements IUserRepository {
    @Override
    public List<User> findAll() {
        return FakeStorage.storage().users();
    }

    @Override
    public void save(User user) {
        FakeStorage.storage().users().add(user);
    }

    @Override
    public boolean exist(String name, String password) {
        for (User user : FakeStorage.storage().users()) {
            if (name.equals(user.getName()) & password.equals(user.getPassword()))
                return true;
        }
        return false;
    }
}
