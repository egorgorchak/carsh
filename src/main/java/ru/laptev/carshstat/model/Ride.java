package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rideId;

    @Column(name = "order_id")
    @JsonProperty("order_id")
    private String orderId;

    @Column(name = "ride_date")
    private LocalDateTime date;

    @Column(name = "ride_cost")
    private double sum;

    @Column(name = "car_name")
    @JsonProperty("car_name")
    private String carName;

    @Column(name = "car_number")
    @JsonProperty("car_number")
    private String carNumber;

    public Ride(String orderId, Double sum) {
        this.orderId = orderId;
        this.sum = sum;
    }

    public Ride(double sum, String carName, String carNumber) {
        this.sum = sum;
        this.carName = carName;
        this.carNumber = carNumber;
    }

    public Ride() {
    }

//    @JsonProperty("check")
//    private void setSum(Map<String, Object> check) {
//        int sumWithoutDiscount = (int) check.get("total_cost_with_discount");
//        this.sum = sumWithoutDiscount / 100d;
//    }



    public Double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @JsonProperty("created_at")
    public void setDate(String dateString) {
        Instant instant = Instant.parse(dateString);
        this.date = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return rideId == ride.rideId && Double.compare(ride.sum, sum) == 0 && orderId.equals(ride.orderId) && Objects.equals(date, ride.date) && carName.equals(ride.carName) && carNumber.equals(ride.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, orderId, date, sum, carName, carNumber);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", orderId='" + orderId + '\'' +
                ", date=" + date +
                ", sum=" + sum +
                ", carName='" + carName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}
