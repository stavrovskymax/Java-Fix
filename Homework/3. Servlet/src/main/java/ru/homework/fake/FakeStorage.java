package ru.homework.fake;

import ru.homework.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeStorage {
    private static FakeStorage storage;

    private List<User> users;

    private FakeStorage() {
        this.users = new ArrayList<>();
        User user = new User("User", "qwerty", LocalDate.parse("1996-05-10"));
        User user1 = new User("User1", "qwerty01", LocalDate.parse("1997-05-10"));
        User user2 = new User("User2", "qwerty02", LocalDate.parse("1998-05-10"));

        users.add(user);
        users.add(user2);
        users.add(user1);
    }

    public static FakeStorage storage() {
        if (storage == null) {
            storage = new FakeStorage();
        }
        return storage;
    }

    public List<User> users() {
        return this.users;
    }
}
