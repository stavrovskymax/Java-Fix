package ru.homework.mvc.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.homework.mvc.models.Car;
import ru.homework.mvc.models.User;
import ru.homework.mvc.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public ModelAndView getProducts() {
        List<User> users = usersRepository.findUsersWithCars();
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam(value = "firstName") String firstName,
                        @RequestParam(value = "lastName") String lastName,
                          @RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "model") String model) {
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
        ModelAndView modelAndView = new ModelAndView("products");
        if (!existUser.isPresent()) {
            usersRepository.save(user);
        } else {
            modelAndView.addObject("errorLoginExists", "Login already exists");
        }
        List<User> users = usersRepository.findUsersWithCars();
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("logout");
        return modelAndView;

    }
}
