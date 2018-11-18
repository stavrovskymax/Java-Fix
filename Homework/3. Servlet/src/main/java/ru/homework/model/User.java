package ru.homework.model;

import java.time.LocalDate;

public class User {
    private String name;
    private String password;
    private LocalDate birthday;

    public User(String name, String password, LocalDate birthday) {
        this.name = name;
        this.password = password;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
