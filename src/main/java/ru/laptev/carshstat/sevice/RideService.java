package ru.laptev.carshstat.sevice;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import org.springframework.stereotype.Service;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.repository.RidesRepository;

import java.util.List;

@Service
public class RideService {

    private final RidesRepository ridesRepository;
    private final ConnectionService connectionService;

    public RideService(RidesRepository ridesRepository, ConnectionService connectionService) {
        this.ridesRepository = ridesRepository;
        this.connectionService = connectionService;
    }

    public List<Ride> getAllRides() {
        return ridesRepository.findAll();
    }

    public List<Ride> uploadAndSaveRides(String code) throws Exception {
        List<Ride> rides = connectionService.getAllRides(code);
        return ridesRepository.saveAll(rides);
    }

    public Ride getRideById(Long id) {
        return ridesRepository.findById(id).get();
    }

    public Ride saveRide(Ride ride) {
        return ridesRepository.save(ride);
    }

    public void deleteRide(Long id) {
        ridesRepository.deleteById(id);
    }

    public Ride updateRide(Ride ride, Long id) {
        Ride one = ridesRepository.findById(id).get();
        one.setCarName(ride.getCarName());
        one.setCarNumber(ride.getCarNumber());
        one.setSum(ride.getSum());
        ridesRepository.save(one);
        return one;
    }
}
