package ru.ivmiit.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.service.models.User;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);
}
