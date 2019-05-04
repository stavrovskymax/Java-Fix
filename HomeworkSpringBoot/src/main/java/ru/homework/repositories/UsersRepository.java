package ru.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.homework.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("select new ru.homework.transfer.UserDto(user.firstName, user.lastName, car.model) " +
            "from User user left join user.cars as car order by user.id")
    List<User> findUsersWithCars();

    Optional<User> findUserByLogin(String login);
}
