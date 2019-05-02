package ru.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.models.Car;
import ru.homework.models.User;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByOwner(User owner);
}
