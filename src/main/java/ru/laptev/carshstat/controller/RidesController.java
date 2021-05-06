package ru.laptev.carshstat.controller;
/*
 * Created by Laptev Egor 1/14/2021
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laptev.carshstat.exception.RideNotFoundException;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.service.RideService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/rides")
public class RidesController {

    private RideService rideService;

    @Autowired
    public RidesController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ride>> getAll() {
        return new ResponseEntity<>(rideService.getAllRides(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable("id") long id) {
        return new ResponseEntity<>(rideService.getRideById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Ride> add(@RequestBody Ride ride) {
        return new ResponseEntity<>(rideService.saveRide(ride), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ride> update(@RequestBody Ride ride, @PathVariable("id") Long id) {
        return new ResponseEntity<>(rideService.updateRide(ride, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ride> delete(@PathVariable("id") Long id) {
        rideService.deleteRide(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(RideNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(RideNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
