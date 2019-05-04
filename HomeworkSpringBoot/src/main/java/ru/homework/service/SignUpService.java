package ru.homework.service;

import ru.homework.forms.UserForm;

import javax.servlet.http.HttpServletRequest;

public interface SignUpService {
    void signUp(UserForm userForm);
    void authenticateUserAndSetSession(UserForm user, HttpServletRequest request);
}
