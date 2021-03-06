package ru.homework.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.homework.models.User;
import ru.homework.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userCandidate = usersRepository.findUserByLogin(login);

        if (userCandidate.isPresent())
            return new UserDetailsImpl(userCandidate.get());
        else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
