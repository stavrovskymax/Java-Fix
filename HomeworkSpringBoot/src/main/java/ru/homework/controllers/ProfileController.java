package ru.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.homework.forms.CarsForm;
import ru.homework.models.Car;
import ru.homework.repositories.CarsRepository;
import ru.homework.security.details.UserDetailsImpl;
import ru.homework.service.ProfileService;
import ru.homework.transfer.UserDto;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private ProfileService service;

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, ModelMap model) {
        if (authentication == null) {
            return "redirect:/login";
        }

        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        UserDto user = UserDto.form(details.getUser());

        List<Car> cars = carsRepository.findCarsByOwner(details.getUser());
        model.addAttribute("user", user);
        model.addAttribute("cars", cars);
        return "profile";
    }

    @PostMapping("/profile")
    public String addCar(CarsForm carsForm, Authentication authentication) {
        service.addCar(carsForm, authentication);
        return "redirect:/profile";
    }
}
