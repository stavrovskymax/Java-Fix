package ru.ivmiit.models;

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private List<Car> cars;
    private String login;
    private String password;

    public User(int id, String firstName, String lastName, List<Car> cars, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
        this.login = login;
        this.password = password;
    }

    public User(String firstName, String lastName, List<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }

    public User(String firstName, String lastName, List<Car> cars, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
        this.login = login;
        this.password = password;
    }

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int id, String firstName, String lastName, List<Car> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
