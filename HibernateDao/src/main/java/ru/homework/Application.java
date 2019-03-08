package ru.homework;

import ru.homework.service.Service;
import ru.homework.models.User;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Service service = new Service();
        List<User> users = service.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
