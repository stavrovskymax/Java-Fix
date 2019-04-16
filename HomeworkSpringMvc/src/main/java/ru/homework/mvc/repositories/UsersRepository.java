package ru.homework.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.homework.mvc.models.Car;
import ru.homework.mvc.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    /*@Query("select user.firstName as firstName, user.lastName as lastName, " +
            "car.model as model from User user left join user.cars as car")*/
    @Query("select new ru.homework.mvc.dto.UserCarDto(user.firstName, user.lastName, car.model) " +
            "from User user left join user.cars as car")
    List<User> findUsersWithCars();
    Optional<User> findUserByLogin(String login);
}
