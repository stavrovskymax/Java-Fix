package ru.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ru.homework.forms.UserForm;
import ru.homework.models.Car;
import ru.homework.models.Role;
import ru.homework.models.State;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void signUp(UserForm userForm) {
        String passwordHash = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .login(userForm.getLogin())
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

    public void authenticateUserAndSetSession(UserForm user, HttpServletRequest request) {
        String login = user.getLogin();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
