package ru.ivmiit.service.services;

import ru.ivmiit.service.forms.UserForm;
import ru.ivmiit.service.models.User;

import java.util.List;

public interface UsersService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
