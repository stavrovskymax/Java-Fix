package ru.ivmiit.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.mvc.models.User;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);
}
