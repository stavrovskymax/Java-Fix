package ru.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.homework.forms.UserForm;
import ru.homework.models.Car;
import ru.homework.models.Role;
import ru.homework.models.State;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;

import java.util.ArrayList;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String passwordHash = passwordEncoder.encode(userForm.getPassword());

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
                .owner(user)
                .model(userForm.getModel())
                .build();

        user.getCars().add(car);
        usersRepository.save(user);
    }
}
