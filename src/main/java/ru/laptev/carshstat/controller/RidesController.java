package ru.laptev.carshstat.controller;
/*
 * Created by Laptev Egor 1/14/2021
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.sevice.RideService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/rides")
public class RidesController {

    private final RideService rideService;

    public RidesController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ride>> getAll() {
        return new ResponseEntity<List<Ride>>(rideService.getAllRides(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable("id") long id) {
        return new ResponseEntity<Ride>(rideService.getRideById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Ride> add(@RequestBody Ride ride) {
        return new ResponseEntity<Ride>(rideService.saveRide(ride), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ride> update(@RequestBody Ride ride, @PathVariable("id") Long id) {
        return new ResponseEntity<Ride>(rideService.updateRide(ride, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity add(@PathVariable("id") Long id) {
        rideService.deleteRide(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
