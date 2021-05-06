package ru.laptev.carshstat.controller;
/*
 * Created by Laptev Egor 2/15/2021
 * */

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/upload")
public class UploadController {

//    private final RideService rideService;
//    private final ConnectionService connectionService;
//
//    public UploadController(RideService rideService, ConnectionService connectionService) {
//        this.rideService = rideService;
//        this.connectionService = connectionService;
//    }
//
//    @GetMapping("/connect")
//    public ResponseEntity<String> connectToYouDrive(
//            @PathVariable("phoneCode") String phoneCode,
//            @PathVariable("phone") String phoneNumber) throws Exception {
//        String connect = connectionService.connect(phoneCode, phoneNumber);
//        return new ResponseEntity<>(connect, HttpStatus.OK);
//    }

//    @PostMapping("/youdrive")
//    public ResponseEntity<List<Ride>> uploadFromYouDrive(@PathVariable("code") String code) throws Exception {
//        return new ResponseEntity<>(rideService.uploadAndSaveRides(code), HttpStatus.OK);
//    }
}
