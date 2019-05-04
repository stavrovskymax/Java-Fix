package ru.homework.service;

import org.springframework.security.core.Authentication;
import ru.homework.forms.CarsForm;

public interface ProfileService {
    void addCar(CarsForm carsForm, Authentication authentication);
}
