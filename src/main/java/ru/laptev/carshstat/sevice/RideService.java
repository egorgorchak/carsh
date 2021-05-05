package ru.laptev.carshstat.sevice;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.repository.RidesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    private RidesRepository ridesRepository;

    @Autowired
    public RideService(RidesRepository ridesRepository) {
        this.ridesRepository = ridesRepository;
    }

    public List<Ride> getAllRides() {
        return ridesRepository.findAll();
    }

    public Ride getRideById(Long id) {
        Optional<Ride> byId = ridesRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("There is now a ride with given id!");
        }
    }

    public Ride saveRide(Ride ride) {
        return ridesRepository.save(ride);
    }

    public void deleteRide(Long id) {
        ridesRepository.deleteById(id);
    }

    public Ride updateRide(Ride ride, Long id) {
        Ride one = ridesRepository.findById(id).get();
        one.setCar(ride.getCar());
        one.setRideDate(ride.getRideDate());
        one.setRideDate(ride.getRideDate());
        one.setRideCost(ride.getRideCost());
        ridesRepository.save(one);
        return one;
    }
}
