package ru.laptev.carshstat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laptev.carshstat.model.Car;

public interface CarRepository extends JpaRepository<Car, String> {
}
