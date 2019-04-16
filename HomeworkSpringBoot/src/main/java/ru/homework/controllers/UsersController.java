package ru.homework.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.homework.models.Car;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/products")
    public String getProducts(ModelMap model) {
        List<User> users = usersRepository.findUsersWithCars();
        model.addAttribute("usersFromServer", users);
        return "products";
    }

    @PostMapping("/products")
    public String  addUser(@RequestParam(value = "firstName") String firstName,
                          @RequestParam(value = "lastName") String lastName,
                          @RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "model") String model, ModelMap modelMap) {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .login(login)
                .password(passwordHash)
                .cars(new ArrayList<Car>())
                .build();
        Car car = Car.builder()
                .owner_id(user)
                .model(model)
                .build();
        user.getCars().add(car);
        Optional<User> existUser = usersRepository.findUserByLogin(login);
        if (!existUser.isPresent()) {
            usersRepository.save(user);
        } else {
            modelMap.addAttribute("errorLoginExists", "Login already exists");
        }
        List<User> users = usersRepository.findUsersWithCars();
        modelMap.addAttribute("usersFromServer", users);
        return "products";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
