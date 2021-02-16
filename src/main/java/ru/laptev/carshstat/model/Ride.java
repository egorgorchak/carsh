package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride")
public class Ride {

    @Id
    @Column(name = "ride_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideId;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "ride_date")
    private LocalDateTime rideDate;

    @Column(name = "ride_cost")
    private double rideCost;

    public Long getRideId() {
        return rideId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getRideDate() {
        return rideDate;
    }

    public void setRideDate(LocalDateTime rideDate) {
        this.rideDate = rideDate;
    }

    public double getRideCost() {
        return rideCost;
    }

    public void setRideCost(double rideCost) {
        this.rideCost = rideCost;
    }

    //    @JsonProperty("check")
//    private void setSum(Map<String, Object> check) {
//        int sumWithoutDiscount = (int) check.get("total_cost_with_discount");
//        this.sum = sumWithoutDiscount / 100d;
//    }
//    @JsonProperty("created_at")
//    public void setDate(String dateString) {
//        Instant instant = Instant.parse(dateString);
//        this.date = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
//    }
}
