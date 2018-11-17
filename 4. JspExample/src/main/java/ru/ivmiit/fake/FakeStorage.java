package ru.ivmiit.fake;

import ru.ivmiit.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeStorage {
    private static final FakeStorage storage;

    static {
        storage = new FakeStorage();
    }

    private List<User> users;

    private FakeStorage(){
        this.users = new ArrayList<>();
        User user = new User("Max", "qwerty", LocalDate.parse("1996-05-10"));
        User user1 = new User("Max1", "qwerty", LocalDate.parse("1996-05-10"));
        User user2 = new User("Max2", "qwerty", LocalDate.parse("1996-05-10"));

        users.add(user);
        users.add(user1);
        users.add(user2);
    }

    public static FakeStorage storage() {
        return storage;
    }

    public List<User> user() {
        return users;
    }
}
