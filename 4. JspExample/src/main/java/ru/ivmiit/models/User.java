package ru.ivmiit.models;

import java.time.LocalDate;

public class User {
    private String name;
    private String password;
    private LocalDate birthDay;

    public User(String name, String password, LocalDate birthDay) {
        this.name = name;
        this.password = password;
        this.birthDay = birthDay;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
