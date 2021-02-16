package ru.laptev.carshstat.model;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Rides {
    @JsonProperty("orders")
    private List<Ride> listOfRides;

    public List<Ride> getListOfRides() {
        return listOfRides;
    }

    public void setListOfRides(List<Ride> listOfRides) {
        this.listOfRides = listOfRides;
    }
}
