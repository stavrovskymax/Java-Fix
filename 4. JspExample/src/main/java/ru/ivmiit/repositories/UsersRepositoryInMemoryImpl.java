package ru.ivmiit.repositories;

import ru.ivmiit.fake.FakeStorage;
import ru.ivmiit.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryInMemoryImpl implements IUsersRepository {
    @Override
    public List<User> findAll() {
        return FakeStorage.storage().user();
    }

    @Override
    public void save(final User user) {
        FakeStorage.storage().user().add(user);
    }

    @Override
    public boolean exist(final String name, final String password) {
        for (User user : FakeStorage.storage().user()) {
            if (name.equals(user.getName()) && password.equals(user.getPassword()))
                return true;
        }
        return false;
    }
}
