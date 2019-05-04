package ru.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.homework.forms.CarsForm;
import ru.homework.models.Car;
import ru.homework.models.User;
import ru.homework.repositories.CarsRepository;
import ru.homework.repositories.UsersRepository;
import ru.homework.security.details.UserDetailsImpl;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Override
    public void addCar(CarsForm carsForm, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();

        String model = carsForm.getModel();
        User user = details.getUser();

        List<Car> cars = carsRepository.findCarsByOwner(user);
        Car car = Car.builder()
                .owner(user)
                .model(model)
                .build();
        cars.add(car);

        user.setCars(cars);
        usersRepository.save(user);
    }
}
