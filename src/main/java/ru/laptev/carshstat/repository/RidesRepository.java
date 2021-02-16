package ru.laptev.carshstat.repository;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laptev.carshstat.model.Ride;

public interface RidesRepository extends JpaRepository<Ride, Long> {
}
