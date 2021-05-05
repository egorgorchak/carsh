package ru.laptev.carshstat.sevice;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptev.carshstat.exception.RideNotFoundException;
import ru.laptev.carshstat.model.Car;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.repository.CarRepository;
import ru.laptev.carshstat.repository.RidesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    private final RidesRepository ridesRepository;
    private final CarRepository carRepository;

    @Autowired
    public RideService(RidesRepository ridesRepository, CarRepository carRepository) {
        this.ridesRepository = ridesRepository;
        this.carRepository = carRepository;
    }

    public List<Ride> getAllRides() {
        return ridesRepository.findAll();
    }

    public Ride getRideById(Long id) {
        Optional<Ride> byId = ridesRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RideNotFoundException("Get by id error: There is now a ride with given id!");
        }
    }

    public Ride saveRide(Ride ride) {
        Car carFromDB = isCarExistInDB(ride.getCar());
        if (carFromDB != null) {
            ride.setCar(carFromDB);
        }
        return ridesRepository.save(ride);
    }

    public void deleteRide(Long id) {
        ridesRepository.deleteById(id);
    }

    public Ride updateRide(Ride ride, Long id) {
        Optional<Ride> rideOptional = ridesRepository.findById(id);
        if (!rideOptional.isPresent()) {
            throw new RideNotFoundException("Updating error: There is now ride with given id!");
        }
        Ride one = rideOptional.get();

        Car carFromDB = isCarExistInDB(ride.getCar());

        if (carFromDB == null) {
            one.setCar(ride.getCar());
        } else {
            one.setCar(carFromDB);
        }

        one.setRideDate(ride.getRideDate());
        one.setRideCost(ride.getRideCost());
        one.setCompany(ride.getCompany());
        ridesRepository.save(one);
        return one;
    }

    private Car isCarExistInDB(Car car) {
        Optional<Car> byId = carRepository.findById(car.getPlateNumber());
        return byId.orElse(null);
    }
}
