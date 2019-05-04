package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.homework.forms.UserForm;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;
import ru.homework.service.SignUpService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService service;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(UserForm userForm, ModelMap model, HttpServletRequest request) {
        Optional<User> user = usersRepository.findUserByLogin(userForm.getLogin());
        if (!user.isPresent()) {
            service.signUp(userForm);
            service.authenticateUserAndSetSession(userForm, request);
            return "redirect:/profile";
        } else {
            model.addAttribute("errorLoginExists", true);
            return "signup";
        }
    }
}
