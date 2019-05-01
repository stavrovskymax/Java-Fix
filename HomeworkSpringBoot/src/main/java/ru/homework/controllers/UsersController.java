package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.homework.forms.UserForm;
import ru.homework.models.Car;
import ru.homework.models.Role;
import ru.homework.models.State;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public String getProducts(ModelMap model) {
        List<User> users = usersRepository.findUsersWithCars();
        model.addAttribute("usersFromServer", users);
        return "users";
    }

    /*@PostMapping("/users")
    public String  addUser(UserForm userForm, ModelMap modelMap) {
        String passwordHash = BCrypt.hashpw(userForm.getPasswordHash(), BCrypt.gensalt(10));
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .login(userForm.getLastName())
                .passwordHash(passwordHash)
                .cars(new ArrayList<Car>())
                .state(State.ACTIVE)
                .role(Role.USER)
                .build();
        Car car = Car.builder()
                .owner_id(user)
                .model(userForm.getModel())
                .build();
        user.getCars().add(car);
        Optional<User> existUser = usersRepository.findUserByLogin(userForm.getLogin());
        if (!existUser.isPresent()) {
            usersRepository.save(user);
        } else {
            modelMap.addAttribute("errorLoginExists", "Login already exists");
        }
        List<User> users = usersRepository.findUsersWithCars();
        modelMap.addAttribute("usersFromServer", users);
        return "products";
    }*/
}
