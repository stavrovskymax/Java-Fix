package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return usersRepository.findUsersWithCars();
    }
}
