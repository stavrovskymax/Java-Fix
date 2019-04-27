package ru.ivmiit.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.service.forms.UserForm;
import ru.ivmiit.service.models.User;
import ru.ivmiit.service.services.UsersService;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return usersService.findAll();
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return usersService.findOne(userId);
    }

    /*@PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }*/
}
